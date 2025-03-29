import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderConfirm {
    int totalPrice;
    Customer customer;
    List<Customer> customerList;

    public OrderConfirm(int totalPrice, Customer customer,List<Customer> customerList) {
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.customerList = customerList;
    }
     public void addPoints(){
        int prevPoints = customer.getPoints();
        int newPoints = totalPrice/10;

     }

    public void printReceipt(Cart cart)
    {
        System.out.println("+---------------------+");
        System.out.println("|       Receipt       |");
        System.out.println("+---------------------+");
        System.out.println("Customer ID: " + customer.getCustomerID());
        int ordered = 0;
        for(Salad salad:cart.addedToCart){
            ordered++;
            System.out.println(ordered+ ". "+salad.getName()+" - "+ salad.getPrice()+"tk");
        }
        int totalPrice = cart.calculateTotalCartPrice();
        System.out.println("Total Price: " + totalPrice+"tk");
        if(totalPrice<500){
            updateCustomerPoints(customer,customerList,customer.getPoints());
        }
        else if(totalPrice>500){
            totalPrice = totalPrice - customer.getPoints();
            System.out.println("Points used: " + customer.getPoints());
            System.out.println("Total price after discount: " + totalPrice+"tk");
            updateCustomerPoints(customer,customerList,0);
        }

        System.out.println("Thank you for ordering from Savour the Flavours of Health!");
        System.out.println("\n\nReturning to main menu!\n\n");
    }
    public void updateCustomerPoints(Customer customer,List<Customer> customerList,int point) {
        int pointsEarned = (int) (totalPrice / 10); // Calculate points

        for (int i = 0; i < customerList.size(); i++) {
            Customer c = customerList.get(i);
            if (c.equals(customer)) {
                int updatedPoint = point +pointsEarned;
                // Create a new customer with updated points
                Customer updatedCustomer = new Customer(c.getCustomerID(), c.getPhoneNumber(), c.getPassword(),updatedPoint);

                // Replace the old customer with the new one
                customerList.set(i, updatedCustomer);
                System.out.println("Points earned: " + pointsEarned);
                System.out.println("Updated point: " + updatedPoint);
                RewriteCustomerFile(customerList);
                return;
            }
        }
    }
    public static void RewriteCustomerFile(List<Customer> customerList){
        String filename = "Customer.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (Customer customer : customerList) {
                // Write the customer data, separated by commas, followed by a newline
                writer.write(customer.getCustomerID() + "," +
                        customer.getPhoneNumber() + "," +
                        customer.getPassword() + "," +
                        customer.getPoints() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
