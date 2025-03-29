import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Salad {
    private String name;
    private String description;
    private int price;
    private Map<Ingredient, Integer> ingredients; //storing ingredient and weight

    public Salad(String name, String description,int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = new HashMap<>();
    }
    public Salad(String name, String description,int price,Map<Ingredient,Integer> ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient, int weight) {
        if (weight <= 0) {
            System.out.println("Invalid weight. Must be greater than 0.");
            return;
        }
        ingredients.put(ingredient, weight);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int calculateTotalCalorie(){
        int totalCalories = 0;
        for(Map.Entry<Ingredient,Integer> entry: ingredients.entrySet()){
            Ingredient ingredient = entry.getKey();
            int weight = entry.getValue();
            totalCalories += ingredient.calculateCalories(weight);
        }
        return totalCalories;
    }

    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }
}
