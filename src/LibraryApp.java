import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {
        Library library = new Library();

        int choice;
        Scanner scanner = new Scanner(System.in);
        Newsletter newsletter = new Newsletter();

        do {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Show Stock");
            System.out.println("3. Search Book");
            System.out.println("4. Subscribe to Newsletter");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Quantity: ");
                        int quantity = scanner.nextInt();

                        Thread addBookThread = new Thread(() -> {
                            Book newBook = new Book(title, author, quantity);
                            if (newBook.validate()) {
                                library.addBook(newBook);
                                System.out.println("Book added successfully.");
                            } else {
                                System.out.println("Invalid quantity. Book not added.");
                            }
                        });
                        addBookThread.start();
                        addBookThread.join();
                        break;

                    case 2:

                        Thread showStockThread = new Thread(library::showStock);
                        showStockThread.start();
                        showStockThread.join();
                        break;

                    case 3:
                        System.out.print("Enter Book Title to search: ");
                        String searchTitle = scanner.nextLine();

                        Thread searchBookThread = new Thread(() -> {
                            try {
                                Book foundBook = library.searchBook(searchTitle);
                                System.out.println("Book found: " + foundBook);
                            } catch (LibraryExceptions.BookNotFoundException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        });
                        searchBookThread.start();
                        searchBookThread.join();
                        break;

                    case 4:

                        Thread subscribeThread = new Thread(() -> LibraryFunctions3.subscribeToNewsletter(newsletter));
                        subscribeThread.start();
                        subscribeThread.join();
                        break;

                    case 5:
                        System.out.println("Exiting the Library System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (LibraryExceptions.BookNotFoundException | LibraryExceptions.InvalidEmailException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
            }
        } while (choice != 5);

        scanner.close();
    }
}
