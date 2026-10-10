import java.sql.SQLException;

public interface LibraryActions {
     void issueBook(Book book,Member member)throws BookNotAvailableException, SQLException;
     void returnBook (Book book,Member member) throws SQLException;
}
