import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PredefinedMenuOrder extends Order{
    Scanner scanner = new Scanner(System.in);
    List<Salad> selectedSalad = new ArrayList<>();

    @Override
    public int calculateTotalPrice() {
        return 0;
    }
    public void placeOrder(SaladCounter saladCounter){
        int totalPrice = 0;
        int totalCalorie = 0;
            saladCounter.displayMenu();
            System.out.println("Enter salad name you want to order: ");
            String saladName = scanner.nextLine();
            for(Salad salad:saladCounter.getSaladList()){
                if(salad.getName().equalsIgnoreCase(saladName)){
                    totalPrice = salad.getPrice();
                }
            }
            System.out.println("Do you want to add any extra ingredient?(Yes/No)");
            String choice = scanner.next();
            while(true) {
                if (scanner.hasNext()) {
                    if (choice.equalsIgnoreCase("Yes")) {
                        CustomizeOrder customOrder = new CustomizeOrder();
                        customOrder.customizeSalad(saladCounter, customOrder);

                        totalPrice += customOrder.calculateTotalPrice();
                        totalCalorie += customOrder.calculateTotalCalorie();
                        System.out.println("Updated Total Price: " + totalPrice + "tk");
                        System.out.println("Updated Total Calorie: " + totalCalorie + "tk");
                        break;
                    } else if (choice.equalsIgnoreCase("No")) {
                        break;
                    } else {
                        System.out.println("Invalid Choice");
                    }
                } else {
                    System.out.println("Invalid Choice");
                }
            }

    }
    public void addExtraIngredient(){

    }
}
