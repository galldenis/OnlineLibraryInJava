// LibraryFunctions3.java

import java.util.Scanner;

public class LibraryFunctions3 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void subscribeToNewsletter(Newsletter newsletter) {
        System.out.print("Enter your email to subscribe to the newsletter: ");
        String email = scanner.nextLine();

        if (isValidEmail(email)) {
            newsletter.setEmail(email);
            System.out.println("Subscribed to the newsletter successfully!");
        } else {
            System.out.println("Invalid email address. Please try again.");
            subscribeToNewsletter(newsletter);
        }
    }

    private static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
