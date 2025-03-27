import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    Scanner scanner = new Scanner(System.in);
    private String customerID;
    private String phoneNumber;

    public Scanner getScanner() {
        return scanner;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
