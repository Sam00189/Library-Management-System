import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL= "jdbc:mysql://localhost:3306/Library_db";
    private static final String user ="Db_UserName";
    private static final String password = "Db_password";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,user,password);
    }
}
