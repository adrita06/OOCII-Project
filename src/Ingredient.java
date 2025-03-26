public class Ingredient {
    private String name;
    private int caloriesPer100g;
    private int pricePer100g;

    public Ingredient(String name, int caloriesPer100g, int pricePer100g) {
        this.name = name;
        this.caloriesPer100g = caloriesPer100g;
        this.pricePer100g = pricePer100g;
    }

    public String getName() {
        return name;
    }

    public int getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public int getPricePer100g() {
        return pricePer100g;
    }

    // Method to calculate calories for a given weight (in grams)
    public int calculateCalories(int weightInGrams) {
        return (caloriesPer100g * weightInGrams) / 100;
    }
    public int calculatePrice(int weightInGrams) {
        return (pricePer100g * weightInGrams) / 100;
    }
}
