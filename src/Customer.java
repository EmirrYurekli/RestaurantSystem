import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Customer {
	
	//Müşteri Kayıt yeri --------------------------------
	
	public static void registercustomer(Scanner scanner) {  
		
		
		System.out.println("========== Müşteri Kayıt Ekranı ==========");
		
		System.out.print("İsminiz : ");
		String name = scanner.nextLine();
		
		System.out.println("Soyisminiz :");
		String surname = scanner.nextLine();
		
		System.out.println("Telefon Numaranız :");
		String phone = scanner.nextLine();
		
		System.out.println("Adresiniz :");
		String address = scanner.nextLine();
		
		System.out.println("Şifreniz : ");
		String password = scanner.nextLine();
		
		try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO Users (name, surname, phone, address, password, role) VALUES (?, ?, ?, ?, ?, 'customer')";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, surname);
                stmt.setString(3, phone);
                stmt.setString(4, address);
                stmt.setString(5, password);

                int rowsInserted = stmt.executeUpdate(); //sql bağlantı çalıştıurma
                if (rowsInserted > 0) {
                    System.out.println("Kayıt başarılı!");
                } else {
                    System.out.println("Kayıt sırasında bir hata oluştu.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Veritabanı hatası: " + e.getMessage());
        }
	
	}
	
	
	//Kullanıcı Girişi ---------------------------------------
	
	public static int loginUser (Scanner scanner, String role) {
		System.out.println("========== Giriş Ekranı ==========");
		
		
		System.out.println("Telefon Numaranız :");
		String phone = scanner.nextLine();
		
		System.out.println("Şifreniz : ");
		String password = scanner.nextLine();
		
		try(Connection conn = DatabaseConnection.getConnection()){
			if(conn != null) {
				String sql = "Select * from Users WHERE phone = ? AND password = ? AND role = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, phone);
				stmt.setString(2, password);
				stmt.setString(3, role);
				
				var resultSet = stmt.executeQuery(); 
				
				if(resultSet.next()) {
					System.out.println("Giriş Başarılı. Hoşgeldiniz " + resultSet.getString("name") + " " + resultSet.getString("surname"));
					int userId = resultSet.getInt("id");
					return userId;
				}
				else {
					System.out.println("Giriş Başarısız. Telefon Numarası veya Şifre yanlış!!!");
				}
			}
			
		}catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
		return -1;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
