import java.util.Map;

public class Order {
    public int calculateTotalPrice(Map<Ingredient,Integer> selectedIngredients){
        int totalPrice = 0;
        for (Map.Entry<Ingredient, Integer> entry : selectedIngredients.entrySet()) {
            totalPrice += entry.getKey().calculatePrice(entry.getValue());
        }
        return totalPrice;
    }
    public int calculateTotalCalorie(Map<Ingredient,Integer> selectedIngredients) {
        int totalCalorie = 0;
        for (Map.Entry<Ingredient, Integer> entry : selectedIngredients.entrySet()) {
            totalCalorie += entry.getKey().calculateCalories(entry.getValue());
        }
        return totalCalorie;
    }
}
