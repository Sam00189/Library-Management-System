import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookDAO {
    public void addBook(Book book)throws SQLException{
        Connection con=DataBaseConnection.getConnection();
        PreparedStatement stmt= con.prepareStatement(" INSERT INTO books (title, author, isbn, is_available) VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1,book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3,book.getIsbn());
        stmt.setBoolean(4,book.isAvailable());

        stmt.executeUpdate();

        ResultSet generatedKeys =stmt.getGeneratedKeys();
        if(generatedKeys.next()){
            book.setBookId(generatedKeys.getInt(1));
        }
    }
    public  Book getBookByTitle(String title)throws SQLException{
            Connection con =DataBaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * from books WHERE title=? ");
            stmt.setString(1,title);
            ResultSet rs =stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String bookTitle =rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                boolean isAvailable = rs.getBoolean("is_available");
                Book book = new Book(bookTitle, author, isbn, isAvailable);
                book.setBookId(id);
                return book;
            }
            return null;
    }
}
