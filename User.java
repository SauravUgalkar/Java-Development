import java.util.*;
import java.util.ArrayList; 
import java.util.List;

public class User{
    String username;
    String password;
    int balance;
   
    List<String> cart = new ArrayList<>();
   
    public User() {
        cart.add("user1");
        cart.add("user2");
        cart.add("pass1");
        cart.add("pass2");
        cart.add("100");
        cart.add("200");
    
    }
    void Login() {
        System.out.println("Welcome to the Login Page");
        System.out.println("-------------------------");
        System.out.println("Please enter your credentials to login.");

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = scan.nextLine();
        System.out.print("Enter password: ");   
        password = scan.nextLine();
         if(username.equals(cart.get(0)) && password.equals(cart.get(2))){
            System.out.println("Login successful!");
        } else if(username.equals(cart.get(1)) && password.equals(cart.get(3))){
            System.out.println("Login successful!\n");
        } else {
            System.out.println("Invalid username or password.");
            Login();
        }
    }
    void ViewBalance() {
        if(username.equals("user1")) {
            System.out.println(username + " Your current balance is: RS. " + cart.get(4));
        } else if(username.equals("user2")) {
            System.out.println("Your current balance is: RS. " + cart.get(5));
        }
        else 
            {System.out.println("Error retrieving balance.");
    }
    }   

    void features() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Balance Enquiry");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit\n");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your option: ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Balance Enquiry selected.\n");
                ViewBalance();
    
                Scanner scan1 = new Scanner(System.in);
                System.out.print("\nGo back to main menu (yes/no): ");
                String ans = scan1.nextLine();
                if(ans.equals("yes"))
                features();
                break;
            case 2:
                System.out.println("Deposit selected.\n");
                Deposit();
                Scanner scan2 = new Scanner(System.in);
                System.out.print("\nGo back to main menu (yes/no): ");
                String ans1 = scan2.nextLine();
                if(ans1.equals("yes"))
                features();
                break;
            case 3:
                System.out.println("Withdraw selected.\n");
                Withdraw();
                Scanner scan3 = new Scanner(System.in);
                System.out.print("\nGo back to main menu (yes/no): ");  
                String ans2 = scan3.nextLine();
                if(ans2.equals("yes"))  
                features(); 
                break;
            case 4:
                System.out.println("\nExiting. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                features();
                break;
        }
    }

    void Deposit() {
        System.out.print("Enter amount to deposit: ");
        Scanner scan = new Scanner(System.in);
        int amount = scan.nextInt();
         if(username.equals("user1")) {
            String depo = cart.get(4);
            int newBalance = Integer.parseInt(depo) + amount;
            cart.set(4, Integer.toString(newBalance));
            System.out.println("Deposit successful. New balance is: RS. " + cart.get(4));

        } else if(username.equals("user2")) {
            String depo = cart.get(5);
            int newBalance = Integer.parseInt(depo) + amount;
            cart.set(5, Integer.toString(newBalance));
            System.out.println("Deposit successful. New balance is: RS. " + cart.get(5));
        }
        else 
            {System.out.println("Error retrieving balance.");
    }
      
    }

    void Withdraw() {
        System.out.print("Enter amount to withdraw: ");
        Scanner scan = new Scanner(System.in);
        int amount = scan.nextInt();
         if(username.equals("user1")) {
            String with = cart.get(4);
            int newBalance = Integer.parseInt(with) - amount;
            if (newBalance < 0) {
                System.out.println("\n Insufficient balance. Withdrawal failed. You have only RS. " + cart.get(4) + " in your account.");
                features();
                return;
            }
            cart.set(4, Integer.toString(newBalance));
            System.out.println("Withdrawal successful. New balance is: RS. " + cart.get(4));

        } else if(username.equals("user2")) {
            String with = cart.get(5);
            int newBalance = Integer.parseInt(with) - amount;
            if (newBalance < 0) {
                System.out.println("Insufficient balance. Withdrawal failed.");
                features();
                return;
            }
            cart.set(5, Integer.toString(newBalance));
            System.out.println("Withdrawal successful. New balance is: RS. " + cart.get(5));
        }
        else 
            {System.out.println("Error retrieving balance.");
    }
      
    }

    public static void main(String[] args) {
        User obj = new User();
        obj.Login();
        obj.features();
        
    }
}
