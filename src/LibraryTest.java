import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testDefaultConstructor() {
        Library library = new Library();
        assertNotNull(library);
        assertTrue(library.getBooks().isEmpty());
    }

    @Test
    void testAddBookConstructor() {
        Book book = new Book("Test Book", "Test Author", 5);
        Library library = new Library();
        library.addBook(book);

        assertFalse(library.getBooks().isEmpty());
        assertEquals(book, library.getBooks().get(0));
    }

    @Test
    void testShowStock() {
        Library library = new Library();
        Book book1 = new Book("Book 1", "Author 1", 10);
        Book book2 = new Book("Book 2", "Author 2", 5);
        library.addBook(book1);
        library.addBook(book2);

        // Capture the output of the showStock method and check if it contains expected information
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        library.showStock();
        String output = outputStream.toString();
        assertTrue(output.contains("Book 1") && output.contains("Book 2"));
    }

    @Test
    void testSearchBook() {
        Library library = new Library();
        Book book1 = new Book("Book 1", "Author 1", 10);
        Book book2 = new Book("Book 2", "Author 2", 5);
        library.addBook(book1);
        library.addBook(book2);

        // Test searching for an existing book
        Book foundBook = library.searchBook("Book 1");
        assertEquals(book1, foundBook);

        // Test searching for a non-existing book
        assertThrows(LibraryExceptions.BookNotFoundException.class, () -> library.searchBook("Book 3"));
    }



    // Additional tests can be added here...

}
