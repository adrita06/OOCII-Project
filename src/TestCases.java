import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TestCases {

    @Test
    void testCalculateTotalPrice() {
        // Create a test instance of the class containing calculateTotalPrice
        Order orderProcessor = new Order();

        // Create test ingredients
        Ingredient lettuce = new Ingredient("Lettuce", 10,10);
        Ingredient tomato = new Ingredient("Tomato", 15,20);
        Ingredient cucumber = new Ingredient("Cucumber", 5,15);

        // Create a map of selected ingredients and their quantities
        Map<Ingredient, Integer> selectedIngredients = new HashMap<>();
        selectedIngredients.put(lettuce, 200);  // 200/100 * 10 = 20
        selectedIngredients.put(tomato, 300);   // 300/100 * 20 = 60
        selectedIngredients.put(cucumber, 400); // 400/100 * 15 = 60

        // Expected total price = 20 + 60 + 60 = 140
        int expectedTotalPrice = 140;

        // Call the method and assert
        int actualTotalPrice = orderProcessor.calculateTotalPrice(selectedIngredients);
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }
    @Test
    void testCalculateTotalCalories() {
        // Create a test instance of the class containing calculateTotalPrice
        Order orderProcessor = new Order();

        // Create test ingredients
        Ingredient lettuce = new Ingredient("Lettuce", 10,10);
        Ingredient tomato = new Ingredient("Tomato", 15,20);
        Ingredient cucumber = new Ingredient("Cucumber", 5,15);

        // Create a map of selected ingredients and their quantities
        Map<Ingredient, Integer> selectedIngredients = new HashMap<>();
        selectedIngredients.put(lettuce, 200);  // 200/100 * 10 = 20
        selectedIngredients.put(tomato, 300);   // 300/100 * 15 = 45
        selectedIngredients.put(cucumber, 400); // 400/100 * 5 = 20

        // Expected total price = 20 + 45 + 20 = 85
        int expectedTotalCalories = 85;

        // Call the method and assert
        int actualTotalCalories = orderProcessor.calculateTotalCalorie(selectedIngredients);
        assertEquals(expectedTotalCalories, actualTotalCalories);
    }

    @Test
    void testGetIngredient() {
        // Create a test instance of the class containing calculateTotalPrice
        Salad salad = new Salad("Vegetable","Healthy",250);

        // Create test ingredients
        Ingredient lettuce = new Ingredient("Lettuce", 10,10);
        Ingredient tomato = new Ingredient("Tomato", 15,20);
        Ingredient cucumber = new Ingredient("Cucumber", 5,15);

        salad.addIngredient(lettuce,100);
        salad.addIngredient(tomato,200);
        salad.addIngredient(cucumber,50);

        Map<Ingredient, Integer> expectedIngredients = new HashMap<>();
        expectedIngredients.put(lettuce,100);
        expectedIngredients.put(tomato,200);
        expectedIngredients.put(cucumber,50);



        // Call the method and assert
        Map<Ingredient, Integer> actualIngredients = salad.getIngredients();


        assertEquals(expectedIngredients,actualIngredients);
    }
}
