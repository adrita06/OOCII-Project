import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    Scanner scanner = new Scanner(System.in);
    String customerID;
    String phoneNumber;
    Map<Ingredient,Integer> selectedIngredients = new HashMap<>();
    public Map<Ingredient,Integer> customizeSalad(SaladCounter saladCounter) {
        Ingredient selectedIngredient = null;
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Enter Ingredient ");
            String ingredient = scanner.next();
            for (Ingredient ingredient1 : saladCounter.getIngredients()) {
                if (ingredient1.getName().equalsIgnoreCase(ingredient)) {
                    selectedIngredient = ingredient1;
                    break;
                }
            }
            System.out.println("Enter amount (in g)");
            int weight = scanner.nextInt();
            selectedIngredients.put(selectedIngredient, weight);
            System.out.println("1.Exit");
            System.out.println("2.Continue");
            int choice = scanner.nextInt();
            switch (choice){
                case(1)-> shouldContinue = false;
                case(2)-> {continue;}
                default -> System.out.println("Invalid Choice.\nContinuing");

                }

        }
        return selectedIngredients;
    }
    public void addExtraIngredient(SaladCounter saladCounter){

    }
}
