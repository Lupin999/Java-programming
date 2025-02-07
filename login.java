import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String correctUsername = "myuser"; 
        String correctPassword = "mypassword"; 

        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3 && !loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            
            String password = maskPassword();

            
            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else {
                attempts++;
                System.out.println("Incorrect username or password. Attempts remaining: " + (3 - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Login failed. Too many attempts.");
        }

        scanner.close();
    }

    
    private static String maskPassword() {
        String password = null;
        try {
            password = new String(System.console().readPassword("Enter password: "));
        } catch (NullPointerException e) {
            System.out.println("Console not available. Using Scanner for input.");
            
            Scanner scanner = new Scanner(System.in);
            password = scanner.nextLine();
        }
        return password;
    }
}