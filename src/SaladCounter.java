import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaladCounter {
    private static List<Ingredient> ingredients;
    private static List<Salad> saladList;

    public static List<Ingredient> getIngredients() {
        return ingredients;
    }

    public static List<Salad> getSaladList() {
        return saladList;
    }

    public SaladCounter() {
        this.saladList = new ArrayList<>();
        this.ingredients = new ArrayList<>();
    }
    public void displayMenu() {
        System.out.println("         MENU           ");
        System.out.println("+----------------------+");

        int menuNum = 0;

        for (Salad salad : saladList) {
            Map<Ingredient, Integer> ingredients = salad.getIngredients();
            menuNum++;

            // Salad name and description
            System.out.println(menuNum + ". " + salad.getName());
            System.out.println("   " + salad.getDescription());

            // Print table headers for ingredients
            System.out.println("+----------------------+---------+-----------+");
            System.out.printf("| %-20s | %-7s | %-9s |\n", "Ingredient", "Weight", "Calories");
            System.out.println("+----------------------+---------+-----------+");

            // Print each ingredient
            printEachIngredient(ingredients);


            System.out.println("+----------------------+---------+-----------+");
            System.out.printf("| Total Calories                 |  %d kcal |\n", salad.calculateTotalCalorie());
            System.out.printf("| Price                          |  %d tk   |\n", salad.getPrice());
            System.out.println("+----------------------+---------+-----------+\n");
        }
    }
    private static void printEachIngredient(Map<Ingredient, Integer> ingredients) {
        for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int weight = entry.getValue();
            String ingredientName = ingredient.getName();
            int caloriesPerIngredient = ingredient.calculateCalories(weight);

            System.out.printf("| %-20s | %4dg   | %4d kcal |\n", ingredientName, weight, caloriesPerIngredient);
        }
    }

    public void displayIngredients(){
        System.out.println("+------------------------+--------------------+-----------------+");
        System.out.println("|  Available Ingredients |  Calorie Per 100g  |  Price Per 100g |");
        System.out.println("+------------------------+--------------------+-----------------+");
        int menuNum=0;
        for(Ingredient ingredient:ingredients){
            menuNum++;
            System.out.printf("| %-22s | %-18d | %-15d |\n",
                    menuNum + ". " + ingredient.getName(),
                    ingredient.getCaloriesPer100g(),
                    ingredient.getPricePer100g());
        }

        System.out.println("+------------------------+--------------------+-----------------+");

    }
}
