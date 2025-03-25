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
    public void addSalad(Salad salad){
        saladList.add(salad);
    }

    public void displayMenu(){
        System.out.println("Menu: ");
        int menuNum=0;
        for(Salad salad:saladList){
            Map<Ingredient,Integer> ingredients = salad.getIngredients();
            menuNum++;
            System.out.println(menuNum + "." + salad.getName() + ": " + salad.getDescription());
            System.out.println("Ingredients: ");
            for(Map.Entry<Ingredient,Integer> entry : ingredients.entrySet()){
                Ingredient ingredient = entry.getKey();
                int weight = entry.getValue();
                String ingredientName=ingredient.getName();
                int caloriesPerIngredient = ingredient.calculateCalories(weight);
                System.out.println(ingredientName+"("+weight+"g) - " +caloriesPerIngredient+ " kcal" );
            }
            System.out.println("Total Calories: " + salad.calculateTotalCalorie() + " kcal");
            System.out.println("\n");
        }

    }
    public void availableIngredients(){

    }
}
