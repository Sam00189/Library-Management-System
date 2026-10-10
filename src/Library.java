import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library implements LibraryActions {

    public synchronized void issueBook(Book book, Member member) throws BookNotAvailableException, SQLException {
        if (book.isAvailable()) {
            // Mark unavailable in database
            Connection con = DataBaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE books SET is_available = false WHERE id = ?"
            );
            stmt.setInt(1, book.getBookId());
            stmt.executeUpdate();

            book.setIsAvailable(false);

            // Record the transaction
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.borrowBook(book, member, LocalDate.now().toString());

            System.out.println("Book issued: " + book.getTitle());
        } else {
            throw new BookNotAvailableException("Book is not available");
        }
    }

    public synchronized void returnBook(Book book, Member member) throws SQLException {
        Connection con = DataBaseConnection.getConnection();

        // Mark book available again
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE books SET is_available = true WHERE id = ?"
        );
        stmt.setInt(1, book.getBookId());
        stmt.executeUpdate();

        // Update return date in transaction
        PreparedStatement stmt2 = con.prepareStatement(
                "UPDATE transactions SET returndate = ? WHERE bookid = ? AND memberid = ?"
        );
        stmt2.setString(1, LocalDate.now().toString());
        stmt2.setInt(2, book.getBookId());
        stmt2.setInt(3, member.getId());
        stmt2.executeUpdate();

        book.setIsAvailable(true);

        System.out.println("Book returned: " + book.getTitle());
    }

    public double calculateFine(String issueDate, String returnDate) {
        LocalDate issue = LocalDate.parse(issueDate);
        LocalDate returned = LocalDate.parse(returnDate);
        long daysLate = ChronoUnit.DAYS.between(issue, returned) - 14; // 14 day limit
        if (daysLate > 0) {
            return daysLate * 2.0; // ₹2 per day
        }
        return 0;
    }
}
