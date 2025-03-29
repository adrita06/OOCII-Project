import java.util.ArrayList;
import java.util.List;

public class CustomerLogin {
    List<Customer> customerList;
    public CustomerLogin() {
        this.customerList = CustomerRegistraion.getCustomerList();
    }
    public Customer authenticateLogin(String phone, String password, String filename) {
        // Hash the entered password before comparing with the stored one
        String hashedPassword = PasswordField.hashPassword(password);

        // Loop through the customer list to check the credentials
        for (Customer customer : customerList) {
            // Compare the phone number and hashed password
            boolean passwordEquals = customer.getPassword().equals(hashedPassword);
            boolean numberEquals = customer.getPhoneNumber().equals(phone);

            // If both match, return the customer
            if (passwordEquals && numberEquals) {
                return customer;
            }
        }

        // Return null if no match found
        return null;
    }
}
