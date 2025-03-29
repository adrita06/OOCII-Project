import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    Scanner scanner = new Scanner(System.in);
    private String customerID;
    private String phoneNumber;
    private String password;
    private int points;

    public Customer(String customerID, String phoneNumber, String password,int points) {
        this.customerID = customerID;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.points = points;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }
}
