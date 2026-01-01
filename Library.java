
import java.util.*;

class Book {
    String name;
    int quantity;

    Book(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

class Student {
    String username;
    String password;
    List<String> borrowedBooks = new ArrayList<>();

    Student(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class Library {
    static Scanner sc = new Scanner(System.in);
    static Student currentUser;

    static void login(List<Student> users, List<Book> lib) {
        while (true) {
            System.out.println("\nEnter your Credentials \n");
            System.out.println("Enter your username: ");
            String username = sc.nextLine();
            System.out.println("Enter your password: ");
            String pass = sc.nextLine();

            for (Student u : users) {
                if (u.username.equals(username) && u.password.equals(pass)) {
                    currentUser = u;
                    System.out.println("Login Successful");
                    features(lib);
                    return;
                }
            }
            System.out.println("Invalid Credentials\n");
        }
    }

    static void register(List<Student> users) {
        System.out.println("Enter new username: ");
        String u = sc.nextLine();
        System.out.println("Enter password: ");
        String p = sc.nextLine();
        boolean exists = false;
        for (Student user : users) {
            if (user.username.equals(u)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            users.add(new Student(u, p));
            System.out.println("Registered successfully");
        } else {
            System.out.println("Username already exists");
        }
    }

    static void view(List<Book> lib) {
        for (Book b : lib) {
            System.out.println("Book name: " + b.name + " -> quantity: " + b.quantity);
        }
        System.out.println("Your borrowed books: " + currentUser.borrowedBooks);
    }

    static void borrow(List<Book> lib) {
        System.out.println("Enter the name of book: ");
        String book = sc.nextLine();
        for (Book b : lib) {
            if (b.name.equals(book)) {
                if (b.quantity > 0) {
                    b.quantity--;
                    currentUser.borrowedBooks.add(book);
                    System.out.println("Book issued.");
                    return;
                } else {
                    System.out.println("Out of stock.");
                    return;
                }
            }
        }
        System.out.println("Book is not available.");
    }

    static void returnBook(List<Book> lib) {
        System.out.println("Enter the name of book: ");
        String book = sc.nextLine();
        if (currentUser.borrowedBooks.contains(book)) {
            for (Book b : lib) {
                if (b.name.equals(book)) {
                    b.quantity++;
                    currentUser.borrowedBooks.remove(book);
                    System.out.println("Book return successful.");
                    return;
                }
            }
        } else {
            System.out.println("Enter the name of issued book correctly!");
        }
    }

    static void renew() {
        if (currentUser.borrowedBooks.isEmpty()) {
            System.out.println("You have not issued any books yet.");
        } else {
            System.out.println("Books renewed successfully.");
        }
    }

    static void search(List<Book> lib) {
        System.out.println("Enter search term: ");
        String term = sc.nextLine();
        boolean found = false;
        for (Book b : lib) {
            if (b.name.contains(term)) {
                System.out.println("Bookname: " + b.name + " quantity: " + b.quantity);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    static void features(List<Book> lib) {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Borrow book");
            System.out.println("2. Return book");
            System.out.println("3. Renew books");
            System.out.println("4. View books");
            System.out.println("5. Search books");
            System.out.println("6. Exit\n");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    borrow(lib);
                    break;
                case 2:
                    returnBook(lib);
                    break;
                case 3:
                    renew();
                    break;
                case 4:
                    view(lib);
                    break;
                case 5:
                    search(lib);
                    break;
                case 6:
                    System.out.println("\nExiting. Thank you!");
                    System.exit(0); 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.print("\nGo back to menu (yes/no): ");
            String ans = sc.nextLine();
            if (!ans.equals("yes")) {
                System.out.println("\nExiting. Thank you!");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to library \n");

        List<Student> users = new ArrayList<>();
        users.add(new Student("user1", "pass1"));
        users.add(new Student("user2", "pass2"));

        List<Book> lib = new ArrayList<>();
        lib.add(new Book("phy", 10));
        lib.add(new Book("chem", 10));
        lib.add(new Book("bio", 10));
        lib.add(new Book("math", 10));

        System.out.println("1. Login");
        System.out.println("2. Register");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 2) {
            register(users);
        }

        if (ch == 1 || ch == 2) {
            login(users, lib);
        } else {
            System.out.println("Invalid option.");
        }
    }
}