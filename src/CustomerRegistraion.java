import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRegistraion {
    Scanner scanner = new Scanner(System.in);
    private static List<Customer> customerList;
    public CustomerRegistraion() {
        this.customerList = new ArrayList<>();
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static void addCustomer(Customer customer) {
            customerList.add(customer);
    }

    public void registerCustomer() throws IOException {
            System.out.println("Enter Phone Number:");
            String phoneNumber = scanner.next();
            while (!validPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number.\nEnter Phone Number:");
                phoneNumber = scanner.next();
            }
            while (!canUsePhoneNumber(phoneNumber)) {
                System.out.println("Phone number already exists.\nEnter a new number");
                phoneNumber = scanner.next();
            }

            System.out.println("Enter password:");
            String password = scanner.next();
            String hashedPassword = PasswordField.hashPassword(password);

            String customerID = generateID();
            Customer customer = new Customer(customerID, phoneNumber, hashedPassword, 0);
            customerList.add(customer);
            saveCustomer(customer);  // Save to file
        }

        public Customer authenticateLogin(String phone, String password) {
            String hashedPassword = PasswordField.hashPassword(password);
            for (Customer customer : customerList) {
                if (customer.getPhoneNumber().equals(phone) && customer.getPassword().equals(hashedPassword)) {
                    return customer;
                }
            }
            return null;
        }

        private boolean validPhoneNumber(String phoneNumber) {
            return phoneNumber.matches("(\\+8801|01)\\d{9}");
        }
        public boolean canUsePhoneNumber(String phone) {
            try (BufferedReader reader = new BufferedReader(new FileReader("Customer.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    if (details.length >= 2 && details[1].trim().equals(phone)) {
                        return false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    public void saveCustomer(Customer customer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Customer.csv", true))) {
            writer.write(customer.getCustomerID() + "," +
                    customer.getPhoneNumber() + "," +
                    customer.getPassword() + "," +
                    customer.getPoints() + "\n");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public String generateID() {
        String lastID = "C-01";  // Default ID if file is empty

        try (BufferedReader reader = new BufferedReader(new FileReader("Customer.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].contains("-")) {
                    lastID = data[0];  // Get last ID from file
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        String[] parts = lastID.split("-");
        if (parts.length == 2) {
            try {
                int number = Integer.parseInt(parts[1]) + 1;
                return "C-" + String.format("%02d", number);
            } catch (NumberFormatException e) {
                System.err.println("Invalid ID format: " + e.getMessage());
            }
        }
        return "C-01";  // Fallback ID
    }


}
