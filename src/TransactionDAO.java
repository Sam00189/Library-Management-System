import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    public void borrowBook(Book book, Member member, String issueDate)throws SQLException{
        Connection con=DataBaseConnection.getConnection();


        PreparedStatement stmt =con.prepareStatement("insert into transactions (bookId,memberId,issuedate) values (?,?,?)");
        stmt.setInt (1,book.getBookId());
        stmt.setInt(2,member.getId());
        stmt.setString(3,issueDate);

        stmt.executeUpdate();
    }
    public String getIssueDate(int bookid)throws SQLException {
        Connection con =DataBaseConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("select issuedate from transactions where bookid = ?");
        stmt.setInt(1,bookid);
        ResultSet rs =stmt.executeQuery();
        if(rs.next()){
            return rs.getString("issuedate");
        }
        return null;
    }
}

