import java.io.*;
import java.util.*;

public class Loader {

    public static void loadSaladsFromCSV(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String saladName = parts[0].trim();
                String description = parts[1].trim();
                int price = Integer.parseInt(parts[3].trim());
                Salad salad = new Salad(saladName, description,price);
                String ingredients = parts[2].trim();

                String[] ingredientArray = ingredients.split(";");
                for (String ing : ingredientArray) {
                    ing = ing.trim();
                    if (ing.contains("(") && ing.contains("g)")) {
                        int start = ing.lastIndexOf("(");
                        int end = ing.lastIndexOf("g)");
                        String ingredientName = ing.substring(0, start).trim();
                        int weight = Integer.parseInt(ing.substring(start + 1, end).trim());

                        for (Ingredient ingredient : SaladCounter.getIngredients()) {
                            if (ingredient.getName().equals(ingredientName)) {
                                salad.addIngredient(ingredient, weight);
                                break;
                            }
                        }
                    }
                }
                SaladCounter.getSaladList().add(salad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadIngredientFromCSV(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 3) continue;

                String ingredientName = parts[0].trim();
                int caloriePer100g = Integer.parseInt(parts[1].trim());
                int pricePer100g = Integer.parseInt(parts[2].trim());

                Ingredient ingredient = new Ingredient(ingredientName, caloriePer100g,pricePer100g);
                SaladCounter.getIngredients().add(ingredient);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
