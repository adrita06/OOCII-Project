public class Ingredient {
    private String name;
    private int caloriesPer100g;

    public Ingredient(String name, int caloriesPer100g) {
        this.name = name;
        this.caloriesPer100g = caloriesPer100g;
    }

    public String getName() {
        return name;
    }

    public int getCaloriesPer100g() {
        return caloriesPer100g;
    }

    // Method to calculate calories for a given weight (in grams)
    public int calculateCalories(int weightInGrams) {
        return (caloriesPer100g * weightInGrams) / 100;
    }
}
