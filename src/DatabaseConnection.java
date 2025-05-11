import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=Mimari Proje;encrypt=false;trustServerCertificate=true;";

	private static final String USER = ""; 
    private static final String PASSWORD = ""; 
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız: " + e.getMessage());
            return null;
        }
    }

}
