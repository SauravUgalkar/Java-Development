import java.util.ArrayList;
import java.util.Scanner;

public class Cart {

    String prod_name;
    int price;
    int quantity;
    ArrayList<Cart> purchasedHistory = new ArrayList<>();

    public Cart(String prod_name, int price, int quantity) {
        this.prod_name = prod_name;
        this.price = price;
        this.quantity = quantity;
    }

    public static void main(String[] args) {
        Cart obj = new Cart(null, 0, 0);
        ArrayList<Cart> cart = new ArrayList<>();
        cart.add(new Cart("A", 500, 4));
        cart.add(new Cart("B", 400, 2));
        cart.add(new Cart("C", 100, 1));

        obj.menu(cart);
    }

    void menu(ArrayList<Cart> cart) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add to Cart");
            System.out.println("2. Remove from Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Purchase Items");
            System.out.println("5. View Purchased History");
            System.out.println("6. Exit\n");
            

            System.out.print("Enter your option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    addToCart(cart, scan);
                    break;
                case 2:
                    removeFromCart(cart, scan);
                    break;
                case 3:
                    viewCart(cart);
                    break;
                case 4:
                    purchaseItem(cart, scan);
                    break;
                case 5:
                    viewPurchasedHistory();
                    break;
                case 6:
                    System.out.println("Thank you for shopping! Exiting...");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    void addToCart(ArrayList<Cart> cart, Scanner scan) {
        System.out.print("Enter product name: ");
        String prod_name = scan.nextLine();
        boolean found = false;

        for (Cart c : cart) {
            if (c.prod_name.equalsIgnoreCase(prod_name)) {
                System.out.print("Enter the quantity to add: ");
                int temp = scan.nextInt();
                scan.nextLine();
                c.quantity += temp;
                System.out.println(temp + " more " + prod_name + "(s) added. Total: " + c.quantity + "\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.print("Enter price: ");
            int price = scan.nextInt();
            scan.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = scan.nextInt();
            scan.nextLine();
            cart.add(new Cart(prod_name, price, quantity));
            System.out.println(quantity + " " + prod_name + " added to cart at Rs. " + price + " each.\n");
        }
    }

    void removeFromCart(ArrayList<Cart> cart, Scanner scan) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is already empty.\n");
            return;
        }

        System.out.println("Remove from the cart:");
        System.out.println("1: Remove all items");
        System.out.println("2: Remove a specific item");
        System.out.print("Enter your option: ");
        int s = scan.nextInt();
        scan.nextLine();

        if (s == 1) {
            cart.clear();
            System.out.println("------ Your cart is now empty. -------\n");
        } else if (s == 2) {
            System.out.print("Enter the product name to remove: ");
            String product = scan.nextLine();
            boolean removed = false;

            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).prod_name.equalsIgnoreCase(product)) {
                    Cart item = cart.remove(i);
                    System.out.println(item.prod_name + " removed from cart.\n");
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                System.out.println("Product not found in cart.\n");
            }
        } else {
            System.out.println("Invalid option.\n");
        }
    }

    void viewCart(ArrayList<Cart> cart) {
        System.out.println("Your Cart:");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.\n");
        } else {
            for (Cart c : cart) {
                System.out.println(c.prod_name + " x" + c.quantity + " @ Rs." + c.price);
            }
        }
        System.out.println();
    }

    void purchaseItem(ArrayList<Cart> cart, Scanner scan) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Nothing to purchase.\n");
            return;
        }

        System.out.println("Purchase Items");
        System.out.print("Enter the product name to purchase: ");
        String productToBuy = scan.nextLine();

        boolean purchased = false;
        for (int i = 0; i < cart.size(); i++) {
            Cart item = cart.get(i);
            if (item.prod_name.equalsIgnoreCase(productToBuy)) {
                purchasedHistory.add(new Cart(item.prod_name, item.price, item.quantity));
                cart.remove(i);
                System.out.println(productToBuy + " purchased successfully!");
                System.out.println("--------------------------- Done\n");
                purchased = true;
                break;
            }
        }

        if (!purchased) {
            System.out.println("Product not found in cart.\n");
        }
    }

    void viewPurchasedHistory() {
        System.out.println("Purchased History:");
        if (purchasedHistory.isEmpty()) {
            System.out.println("No purchases yet.\n");
        } else {
            for (Cart p : purchasedHistory) {
                System.out.println(p.prod_name + " x" + p.quantity + " @ Rs." + p.price);
            }
        }
        System.out.println();
    }
}