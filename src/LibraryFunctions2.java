public class LibraryFunctions2 {

    public static void printAllBooks(Library library) {

        Thread printAllBooksThread = new Thread(() -> {

        });
        printAllBooksThread.start();
        try {
            printAllBooksThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}