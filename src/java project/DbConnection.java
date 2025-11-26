import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class DbConnection {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String name = "root";
    private static final String pass = "Akash@2004";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, name, pass);
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
