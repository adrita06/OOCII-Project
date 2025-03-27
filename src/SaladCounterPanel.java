import java.util.Map;
import java.util.Scanner;

public class SaladCounterPanel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SaladCounter saladCounter = new SaladCounter();
        String saladMenu = "salad_menu.csv";
        String ingredientList = "ingredients.csv";
        Loader.loadIngredientFromCSV(ingredientList);
        Loader.loadSaladsFromCSV(saladMenu);
        while (true) {
            System.out.println("\n1. Menu");
            System.out.println("2. Customize Salad");
            System.out.println("3. Popular Picks");
            System.out.println("4. Admin Settings (for admins only)");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1-> {
                    PredefinedMenuOrder predefinedMenuOrder=new PredefinedMenuOrder();
                    predefinedMenuOrder.placeOrder(saladCounter);
                }
                case 2 ->{
                    CustomizeOrder order = new CustomizeOrder();
                    saladCounter.displayIngredients();
                    order.customizeSalad(saladCounter,order);
                }
                case 3 -> System.out.println("Showing popular picks...");
                case 4 -> System.out.println("Admin settings...");
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("Would you like to:");
            System.out.println("1.Add another salad");
            System.out.println("2.Checkout");
            System.out.println("3.Cancel Order");
            int choice2 = scanner.nextInt();
            if(choice2==1){
                continue;
            }
        }
    }
}
