import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Market {
    private static final String USER_DATA_FILE = "users.txt";
    private static final String ITEM_DATA_FILE = "items.txt";
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Market!");
    
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
    
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    User user = login(scanner);
                    if (user != null) {
                        MarketMenu(scanner, user);
                    }
                    break;
                case "2":
                    register(scanner);
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
    
    private static User login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
    
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
    
        try {
            Scanner fileScanner = new Scanner(new File(USER_DATA_FILE));
            while (fileScanner.hasNextLine()) {
                String[] userData = fileScanner.nextLine().split(",");
                if (userData[0].equals(username) && userData[1].equals(password)) {
                    System.out.println("Login successful!");
                    fileScanner.close();
                    return new User(username, password);
                }
            }
            fileScanner.close();
            System.out.println("Incorrect username or password.");
        } catch (IOException e) {
            System.out.println("Error reading user data.");
            e.printStackTrace();
        }
    
        return null;
    }
    
    private static void register(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
    
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
    
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();
    
            System.out.println("Registration successful!");
            currentUser = new User(username, password);
        } catch (IOException e) {
            System.out.println("Error writing user data.");
            e.printStackTrace();
        }
    }
    

    private static void MarketMenu(Scanner scanner, User currentUser) {
        while (true) {
            System.out.println("=======================================");
            System.out.println("| Welcome to the Online Market Item!    |");
            System.out.println("=======================================");
            System.out.println("|                                      |");
            System.out.println("| 1. View items                        |");
            System.out.println("| 2. Add item to cart                  |");
            System.out.println("| 3. View cart                         |");
            System.out.println("| 4. Your Inventory                    |");
            System.out.println("| 5. Account fund                      |");
            System.out.println("| 6. Logout                            |");
            System.out.println("|                                      |");
            System.out.println("=======================================");

            System.out.print("Enter your choice: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1:
                    viewItems();
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double itemPrice = scanner.nextDouble();
                    scanner.nextLine();
                    addItemToCart(itemName, itemPrice, currentUser);
                    break;
                case 3:
                    viewCart(currentUser);
                    break;
                case 4:
                    viewInventory(currentUser);
                    break;
               case 5:
                    displayAccountFunds();
                    break;
            
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    private static void addItemToCart(String itemName, double itemPrice, User currentUser) {
        currentUser.addItemToCart(itemName);
        currentUser.setBalance(currentUser.getBalance() - itemPrice);
        System.out.println(itemName + " added to cart.");
    }
    

    private static void viewItems() {
        try {
            // Create a new file object for items.txt
            File file = new File("items.txt");
    
            // Create a new scanner to read from the file
            Scanner scanner = new Scanner(file);
    
            // Read each line of the file and display the item details
            while (scanner.hasNextLine()) {
                String[] itemDetails = scanner.nextLine().split(",");
                System.out.println("=======================================");
                System.out.println("|                                      |");
                System.out.println("| Item name: "+ itemDetails[0]   +       "|");
                System.out.println("|                                      |");
                System.out.println("| Item price: "+ itemDetails[1]    +     "|");
                System.out.println("|                                      |");
                System.out.println("=======================================");
    
                System.out.println();
            }
    
            // Close the scanner
            scanner.close();
    
        } catch (FileNotFoundException e) {
            System.out.println("Error: items file not found");
        }
    }

    private static void viewCart(User user) {
        List<String> cart = user.getCart();
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (String item : cart) {
                System.out.println("- " + item);
            }
        }
    }

    private static void viewInventory(User user) {
        System.out.println("Your inventory:");
        try {
            Scanner scanner = new Scanner(new File(user.getUsername() + "_inventory.txt"));
            while (scanner.hasNextLine()) {
                String[] itemData = scanner.nextLine().split(",");
                String itemName = itemData[0];
                double itemPrice = Double.parseDouble(itemData[1]);
                int itemQuantity = Integer.parseInt(itemData[2]);
                System.out.println(itemName + " | Price: $" + itemPrice + " | Quantity: " + itemQuantity);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        }
    }
    
    public static void displayAccountFunds() {
        System.out.println("Your current balance is: $" + currentUser.getBalance());
    }
    
    private User getCurrentUser() {
        // code to retrieve the current user
        return currentUser;
    }
    
}   
