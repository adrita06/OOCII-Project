import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SaladCounterPanel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SaladCounter saladCounter = new SaladCounter();
        Cart cart = new Cart();
        CustomerManager customerManager = new CustomerManager();
        Customer customer = null;
        String saladMenu = "salad_menu.csv";
        String ingredientList = "ingredients.csv";
        Loader.loadIngredientFromCSV(ingredientList);
        Loader.loadSaladsFromCSV(saladMenu);
        Loader.loadCustomerFromCSV("Customer.csv");
        while (true) {

            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice1 = getValidChoice(scanner, 1, 3); // Helper method for input validation

            if (choice1 == 1) {
                customerManager.registerCustomer();
                continue;
            } else if (choice1 == 2) {
                System.out.println("Enter phone number:");
                String number = scanner.next();
                System.out.println("Enter password:");
                String password = scanner.next();
                customer = customerManager.authenticateLogin(number, password, "Customer.csv");
            }
            else if (choice1 == 3){
                System.out.println("Exiting...");
                scanner.close();
                return;
            }

            if (customer != null) {
                loggedIn(scanner, saladCounter, cart, customer, customerManager);
            } else {
                System.out.println("Invalid number or password!");
            }
        }
    }

    public static void loggedIn(Scanner scanner, SaladCounter saladCounter, Cart cart, Customer customer, CustomerManager customerManager) {
        while (true) {
            System.out.println("\n1. Menu");
            System.out.println("2. Customize Salad");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = getValidChoice(scanner, 1, 4); // Helper method for input validation
            Salad salad = null;

            switch (choice) {
                case 1:
                    salad = viewMenu(saladCounter, scanner, cart);
                    break;
                case 2:
                    CustomizeOrder order = new CustomizeOrder();
                    Map<Ingredient, Integer> customIngredient = order.customizeSalad(saladCounter, order, 0);
                    salad = new Salad("Custom Salad", "", order.calculateTotalPrice(customIngredient), customIngredient);
                    break;
                case 3:
                    checkout(cart, scanner, customer, customerManager);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (salad != null) {
                cart.addToCart(salad);
                handleCartOptions(scanner, cart, salad, customer, customerManager);
            }
        }
    }

    private static void handleCartOptions(Scanner scanner, Cart cart, Salad salad, Customer customer, CustomerManager customerManager) {
        while (true) {
            System.out.println("Would you like to:");
            System.out.println("1. Add another salad");
            System.out.println("2. Checkout");
            System.out.println("3. Cancel Order");

            int choice = getValidChoice(scanner, 1, 3);

            if (choice == 1) {
                return;
            } else if (choice == 2) {
                checkout(cart, scanner, customer, customerManager);
            } else if (choice == 3) {
                cart.removeFromCart2(salad);
                System.out.println("Order canceled.");
                return;
            }
        }
    }

    public static void checkout(Cart cart, Scanner scanner, Customer customer, CustomerManager customerManager) {
        cart.viewCart();

        while (true) {
            System.out.println("\nWould you like to:");
            System.out.println("1. Add another salad");
            System.out.println("2. Remove from cart");
            System.out.println("3. Cancel Order");
            System.out.println("4. Confirm order");

            int choice = getValidChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    return;
                case 2:
                    System.out.println("Enter salad number to remove:");
                    int saladNum = getValidChoice(scanner, 1, Integer.MAX_VALUE); // Ensure valid salad number
                    cart.removeFromCart(saladNum);
                    break;
                case 3:
                    cart.emptyCart();
                    System.out.println("All orders canceled.");
                    return;
                case 4:
                    int totalPrice = cart.calculateTotalCartPrice();
                    List<Customer> customerList = customerManager.customerList;
                    OrderConfirm orderConfirm = new OrderConfirm(totalPrice, customer, customerList);
                    orderConfirm.printReceipt(cart);
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static Salad viewMenu(SaladCounter saladCounter, Scanner scanner, Cart cart) {
        saladCounter.displayMenu();

        while (true) {
            System.out.println("1. Add to cart");
            System.out.println("2. Exit");

            int choice = getValidChoice(scanner, 1, 2);

            if (choice == 1) {
                PredefinedMenuOrder predefinedMenuOrder = new PredefinedMenuOrder();
                return predefinedMenuOrder.placeOrder(saladCounter);
            } else if (choice == 2) {
                return null;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
            scanner.next(); // Consume the invalid input
        }
        int choice = scanner.nextInt();
        if (choice < min || choice > max) {
            System.out.println("Choice out of range. Please try again.");
            return getValidChoice(scanner, min, max); // Recursive call to re-validate
        }
        return choice;
    }
}
