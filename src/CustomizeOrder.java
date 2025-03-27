import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomizeOrder extends Order{
    Map<Ingredient, Integer> selectedIngredients = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    @Override
    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<Ingredient, Integer> entry : selectedIngredients.entrySet()) {
            totalPrice += entry.getKey().calculatePrice(entry.getValue());
        }
        return totalPrice;
    }
    public int calculateTotalCalorie() {
        int totalCalorie = 0;
        for (Map.Entry<Ingredient, Integer> entry : selectedIngredients.entrySet()) {
            totalCalorie += entry.getKey().calculateCalories(entry.getValue());
        }
        return totalCalorie;
    }

    public Map<Ingredient, Integer> customizeSalad(SaladCounter saladCounter, CustomizeOrder order) {
        int price = 0;
        int calorie = 0;
        boolean shouldContinue = true;
        Ingredient selectedIngredient = null;

        while (shouldContinue) {
            saladCounter.displayIngredients();
            System.out.println("Enter Ingredient: ");
            String ingredientName = scanner.nextLine();
            for (Ingredient ingredient : saladCounter.getIngredients()) {
                if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                    selectedIngredient = ingredient;
                    break;
                }
            }

            // If ingredient is not found, prompt again
            if (selectedIngredient == null) {
                System.out.println("Ingredient not found. Please enter a valid ingredient.");
                continue;
            }

            // Get valid weight input
            int weight = 0;
            while (true) {
                System.out.println("Enter amount (in g): ");
                if (scanner.hasNextInt()) {
                    weight = scanner.nextInt();
                    if (weight > 0) break; // Valid weight
                    else System.out.println("Weight must be greater than 0.");
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear invalid input
                }
            }
            scanner.nextLine();

            selectedIngredients.put(selectedIngredient, weight);
            price = order.updatePrice(selectedIngredient, weight, price);
            calorie = order.updateCalorie(selectedIngredient, weight, calorie);

            // Continue or exit
            int choice;
            while (true) {
                System.out.println("1. Add more ingredient");
                System.out.println("2. Reduce the amount of ingredient");
                System.out.println("3. Exit");
                String input = scanner.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    if (choice == 3) {
                        shouldContinue = false;
                        break;
                    } else if (choice == 2 || choice == 1) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Enter 1 or 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a valid number.");
                }
            }


        }

        return selectedIngredients;
    }

    public int updatePrice(Ingredient ingredient,int weight,int price){
        System.out.println("Adding: "+ingredient.calculatePrice(weight)+"tk");
        price+=ingredient.calculatePrice(weight);
        System.out.println("Current price: "+price+"tk");
        return price;

    }
    public int updateCalorie(Ingredient ingredient,int weight,int calorie){
        System.out.println("Adding: "+ingredient.calculateCalories(weight)+"kcal");
        calorie+=ingredient.calculateCalories(weight);
        System.out.println("Total calorie: "+ calorie+"kcal");
        return calorie;
    }



}
