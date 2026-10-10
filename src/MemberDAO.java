import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MemberDAO {
    public void addMember (Member member) throws SQLException {
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("insert into members(name,email,borrowedCount) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setInt(3, member.getBorrowedBookCount());

        stmt.executeUpdate();

        ResultSet generatedKeys =stmt.getGeneratedKeys();
        if(generatedKeys.next()){
            member.setId(generatedKeys.getInt(1));
        }
    }

    public Member getMemberByName ( String name) throws SQLException{
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from members where name = ?");
        stmt.setString(1,name);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()){
            int id=rs.getInt("memberid");
            String userName =rs.getString("name");
            String email = rs.getString("email");
            int borrowedCount =rs.getInt("borrowedCount");
            Member member = new Member (userName, email, borrowedCount);
            member.setId(id);
            return member;
        }
        return null;
    }
}
