public class LibraryFunctions1 {

    public static void printBookDetails(Book book) {
        System.out.println("Book Details:");
        System.out.println(book);


        Thread functionThread = new Thread(() -> {

        });
        functionThread.start();
    }

    public static void addSampleBooks(Library library) {
        // Introduce a thread for adding sample books
        Thread addSampleBooksThread = new Thread(() -> {
            // Add sample books implementation
            System.out.println("Adding sample books...");
        });
        addSampleBooksThread.start();
    }
}