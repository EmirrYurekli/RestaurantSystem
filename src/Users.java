import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Users {

	public static void adminUser () {
		
		Scanner scanner = new Scanner(System.in);

		try(Connection conn = DatabaseConnection.getConnection()) {
			if(conn != null) {
				System.out.println("***** Menüde Değişiklik İçin : *****"
						+ "\n -- Tüm Kullanıcıları Görmek	için	 => 1 "
						+ "\n -- Yeni Kullanıcı Eklemek              için    => 2 "
						+ "\n -- Kullanıcı Silmek                    için    => 3 "
						+ "\n -- Olan Kullanıcıyı Güncellemek        için    => 4 ");
				
				String choose = scanner.nextLine();
				
				if(choose.equals("1")) {
					viewAllUsers();
				}else if(choose.equals("2")) {
					addUser(scanner);
				}else if(choose.equals("3")) {
					deleteUser(scanner);
				}else if(choose.equals("4")) {
					updateUser(scanner);
				}else {
					System.out.println("Geçerli Değer Giriniz.");
				}
			}	
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	public static void viewAllUsers() {
	    try (Connection conn = DatabaseConnection.getConnection()) {
	        if (conn != null) {
	            String sql = "SELECT * FROM Users";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();

	            System.out.println("=== Kullanıcı Listesi ===");
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String surname = rs.getString("surname");
	                String phone = rs.getString("phone");
	                String role = rs.getString("role");
	                System.out.println("ID: " + id + " | Ad: " + name + " " + surname + " | Telefon: " + phone + " | Rol: " + role);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}

	
	public static void addUser(Scanner scanner) {
	    try (Connection conn = DatabaseConnection.getConnection()) {
	        if (conn != null) {
	            System.out.println("=== Yeni Kullanıcı Ekleme ===");

	            System.out.print("Ad: ");
	            String name = scanner.nextLine();

	            System.out.print("Soyad: ");
	            String surname = scanner.nextLine();

	            System.out.print("Telefon: ");
	            String phone = scanner.nextLine();

	            System.out.print("Şifre: ");
	            String password = scanner.nextLine();

	            System.out.print("Adres: ");
	            String address = scanner.nextLine();

	            System.out.print("Rol (customer/chef/courier/admin): ");
	            String role = scanner.nextLine();

	            String sql = "INSERT INTO Users (name, surname, phone, password, address, role) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, name);
	            stmt.setString(2, surname);
	            stmt.setString(3, phone);
	            stmt.setString(4, password);
	            stmt.setString(5, address);
	            stmt.setString(6, role);

	            int rowsInserted = stmt.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Kullanıcı başarıyla eklendi.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}

	public static void deleteUser(Scanner scanner) {
	    try (Connection conn = DatabaseConnection.getConnection()) {
	        if (conn != null) {
	            viewAllUsers(); 

	            System.out.print("Silmek istediğiniz kullanıcının ID'si: ");
	            int id = scanner.nextInt();
	            scanner.nextLine();

	            String sql = "DELETE FROM Users WHERE id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, id);

	            int rowsDeleted = stmt.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Kullanıcı başarıyla silindi.");
	            } else {
	                System.out.println("Kullanıcı bulunamadı.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}

	public static void updateUser(Scanner scanner) {
	    try (Connection conn = DatabaseConnection.getConnection()) {
	        if (conn != null) {
	            viewAllUsers(); 

	            System.out.print("Güncellemek istediğiniz kullanıcının ID'si: ");
	            int id = scanner.nextInt();
	            scanner.nextLine();

	            System.out.print("Yeni Ad: ");
	            String newName = scanner.nextLine();

	            System.out.print("Yeni Soyad: ");
	            String newSurname = scanner.nextLine();

	            System.out.print("Yeni Telefon: ");
	            String newPhone = scanner.nextLine();

	            System.out.print("Yeni Şifre: ");
	            String newPassword = scanner.nextLine();

	            System.out.print("Yeni Adres: ");
	            String newAddress = scanner.nextLine();

	            System.out.print("Yeni Rol (customer/chef/courier/admin): ");
	            String newRole = scanner.nextLine();

	            String sql = "UPDATE Users SET name = ?, surname = ?, phone = ?, password = ?, address = ?, role = ? WHERE id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, newName);
	            stmt.setString(2, newSurname);
	            stmt.setString(3, newPhone);
	            stmt.setString(4, newPassword);
	            stmt.setString(5, newAddress);
	            stmt.setString(6, newRole);
	            stmt.setInt(7, id);

	            int updatedRows = stmt.executeUpdate();
	            if (updatedRows > 0) {
	                System.out.println("Kullanıcı başarıyla güncellendi.");
	            } else {
	                System.out.println("Kullanıcı bulunamadı.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
