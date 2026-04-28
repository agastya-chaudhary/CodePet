package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/codepet",
                "root",
                "agastya@13"
        );
    }
}