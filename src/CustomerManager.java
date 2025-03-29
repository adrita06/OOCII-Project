import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {
    Scanner scanner = new Scanner(System.in);
    static List<Customer> customerList;

    public CustomerManager() {
        this.customerList = new ArrayList<>();
    }
    public static void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public void registerCustomer(){
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.next();
        while(!validPhoneNumber(phoneNumber)){
            System.out.println("Invalid phone number.\nEnter Phone Number:");
            phoneNumber=scanner.next();
        }
        while (!canUsePhoneNumber(phoneNumber,"Customer.csv")) {
            System.out.println("Phone number already exists.\nEnter a new number");
            phoneNumber = scanner.next();
        }
        System.out.println("Enter password:");
        String password = scanner.next();
        String hashedPassword2 = PasswordField.hashPassword(password);
        String customerID = autogenerate("Customer.csv");
        Customer customer = new Customer(customerID,phoneNumber,hashedPassword2,0);
        addCustomer(customer);
        customerFile(customer);


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

    private boolean validPhoneNumber(String phoneNumber){
        return phoneNumber.matches("(\\+8801|01)\\d{9}");

    }
    public static boolean canUsePhoneNumber(String phone, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length < 3) {
                    continue;
                }
                String storedNumber = details[1];
                if (storedNumber.equals(phone)) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public String autogenerate(String filename) {
        String generatedID=null;
        if(filename.equals("Customer.csv"))
            generatedID = "C-01"; // Default ID if file is empty
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            String lastID = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].contains("-")) { // Validate data
                    String[] partsOfId = data[0].split("-");
                    if (partsOfId.length == 2) { // Ensure correct ID format
                        lastID = partsOfId[1]; // Get the numeric part of the last ID
                    }
                }
            }

            if (lastID != null) {
                int parsedID = Integer.parseInt(lastID);
                int newID = parsedID + 1;
                    generatedID = "C-" + String.format("%02d", newID); // Format ID as "M-XX"

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format in file: " + e.getMessage());
        }
        return generatedID;
    }
    public static void customerFile(Customer customer){
        String filename = "Customer.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Write the customer data, separated by commas
            writer.write(customer.getCustomerID() + "," +
                    customer.getPhoneNumber() + "," +
                    customer.getPassword() + "," +
                    customer.getPoints() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
