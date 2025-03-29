import java.util.*;

public class PredefinedMenuOrder extends Order{
    Scanner scanner = new Scanner(System.in);
    Salad saladSelected;
    public Salad placeOrder(SaladCounter saladCounter){

        int totalCalorie = 0;
        saladSelected = getSalad(saladCounter);
        int totalPrice = saladSelected.getPrice();

            System.out.println("Do you want to add any extra ingredient?(Yes/No)");
            String choice = scanner.next();
            while(true) {
                if (choice.equalsIgnoreCase("Yes")) {
                    CustomizeOrder customOrder = new CustomizeOrder();
                    Map<Ingredient, Integer> ingredientAdded = customOrder.customizeSalad(saladCounter, customOrder, totalPrice);
                    saladSelected = addExtraIngredient(saladSelected, ingredientAdded, totalPrice);
                    totalPrice += calculateTotalPrice(saladSelected.getIngredients());
                    totalCalorie += calculateTotalCalorie(saladSelected.getIngredients());
                    System.out.println("Updated Total Price: " + totalPrice + "tk");
                    System.out.println("Updated Total Calorie: " + totalCalorie + "tk");
                    return saladSelected;
                } else if (choice.equalsIgnoreCase("No")) {
                    return saladSelected;
                } else {
                    System.out.println("Invalid Choice");
                }
            }

    }
    private Salad getSalad(SaladCounter saladCounter){
        Salad saladSelected = null;
        System.out.println("Enter salad name you want to order: ");
        String saladName = scanner.nextLine();
        for(Salad salad:saladCounter.getSaladList()){
            if(salad.getName().equalsIgnoreCase(saladName)){
                saladSelected = salad;
               // totalPrice = salad.getPrice();
                return saladSelected;
            }
        }
        while(saladSelected == null){
            System.out.println("Invalid salad name");
            System.out.println("Enter salad name you want to order: ");
            saladName = scanner.nextLine();
            for(Salad salad:saladCounter.getSaladList()){
                if(salad.getName().equalsIgnoreCase(saladName)){
                    saladSelected = salad;
                    //totalPrice = salad.getPrice();
                    return saladSelected;
                }
            }
        }
        return saladSelected;

    }
    public Salad addExtraIngredient(Salad salad, Map<Ingredient, Integer> ingredientAdded, int price) {
        // Create a new Map to store updated ingredients
        Map<Ingredient, Integer> updatedIngredients = new HashMap<>(salad.getIngredients());

        // Add new ingredients
        for (Map.Entry<Ingredient, Integer> ingredient : ingredientAdded.entrySet()) {
            updatedIngredients.put(ingredient.getKey(), ingredient.getValue());
        }
        price += calculateTotalPrice(ingredientAdded);

        // Create a new salad name
        String name = salad.getName() + " (Customized)";

        // Return a new Salad object with the updated ingredients
        return new Salad(name, salad.getDescription(), price, updatedIngredients);
    }

}
