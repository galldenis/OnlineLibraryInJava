import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private Connection connection;

    public Library() {
        this.connection = DatabaseManager.getConnection();
        createBooksTableIfNotExists();
    }

    private void createBooksTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(255) NOT NULL," +
                "author VARCHAR(255) NOT NULL," +
                "quantity INT NOT NULL)";

        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        String insertSQL = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getQuantity());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String selectSQL = "SELECT * FROM books";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(title, author, quantity);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book searchBook(String title) {
        String selectSQL = "SELECT * FROM books WHERE title = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");
                return new Book(title, author, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new LibraryExceptions.BookNotFoundException("Book not found");
    }

    public void showStock() {
        List<Book> books = getBooks();

        System.out.println("Library Stock:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void closeConnection() {
        DatabaseManager.closeConnection();
    }
}
