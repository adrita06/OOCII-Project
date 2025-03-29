import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {

    List<Salad> addedToCart;
    public Cart() {
        addedToCart = new ArrayList<>();
    }
    public void addToCart(Salad salad){
        addedToCart.add(salad);
    }
    public int calculateTotalCartPrice(){
        int totalPrice = 0;
        for(Salad salad:addedToCart){
            totalPrice+= salad.getPrice();
        }
        return totalPrice;
    }
    public void removeFromCart(int num) {
        int index = num-1;
        if(addedToCart.size()==0){
            System.out.println("Cart is empty!");
            return;
        }
        if (index >= 0 && index < addedToCart.size()) {
            addedToCart.remove(index);
            System.out.println("Removed from cart successfully");
        } else {
            System.out.println("Invalid index!");
        }
    }
    public void removeFromCart2(Salad salad) {
        addedToCart.remove(salad);
    }
    public void emptyCart() {
        addedToCart.clear();
    }


    public void viewCart(){
        int saladNum=0;
        boolean saladFound = false;
        System.out.println("+--------------------+");
        System.out.println("|      Your Cart     |");
        System.out.println("+--------------------+");
        for(Salad salad:addedToCart){
            saladNum++;
            saladFound = true;
            System.out.println(saladNum+". "+salad.getName());
            getIngredient(salad);
            System.out.println("Price :" + salad.getPrice()+"tk");
            System.out.println("Calories :" + salad.calculateTotalCalorie()+"kcal");
        }
        if(!saladFound){
            System.out.println("Your cart is empty");
            return;
        }
        System.out.println("Total price: " + calculateTotalCartPrice() );
    }
    public void getIngredient(Salad salad){
        Map<Ingredient,Integer> ingredients = salad.getIngredients();
        for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int weight = entry.getValue();
            String ingredientName = ingredient.getName();
            int caloriesPerIngredient = ingredient.calculateCalories(weight);
            int price = salad.getPrice();

            System.out.printf("| %-20s | %4dg   | %4d kcal |\n", ingredientName, weight, caloriesPerIngredient);
        }

    }



}
