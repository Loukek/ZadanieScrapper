import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String dbhost = "jdbc:mysql://localhost:3306/persona5";
    private static String user = "root";
    private static String password = "";
    private static Connection conn;

    public static Connection connect() {
        try {
            conn = DriverManager.getConnection(dbhost, user, password);
            System.out.println("Połączono z bazą \n");
        } catch (SQLException e) {
            System.out.println("Połączenie z bazą nieudane");
            e.printStackTrace();
        } finally {
            return conn;
        }
    }
}


