import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class admin {
	

	public static void adminmenu () {
		
		Scanner scanner = new Scanner(System.in);

		Menu.showmenu();
		try(Connection conn = DatabaseConnection.getConnection()) {
			if(conn != null) {
				System.out.println("***** Menüde Değişiklik İçin : *****"
						+ "\n -- Yeni Ürün Eklemek              için    => 1 "
						+ "\n -- Ürün Silmek                    için    => 2 "
						+ "\n -- Olan Ürünü Güncellemek         için    => 3");
				
				String choose = scanner.nextLine();
				
				if(choose.equals("1")) {
					addMenuItem(scanner);
				}else if(choose.equals("2")) {
					delMenuItem(scanner);
				}else if(choose.equals("3")) {
					updateMenuItems(scanner);
				}else {
					System.out.println("Geçerli Değer Giriniz.");
				}
			}	
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	public static void addMenuItem(Scanner scanner) {
		try(Connection conn = DatabaseConnection.getConnection()) {
			if (conn != null) {
				Menu.showmenu();
				System.out.println("Eklemek istedğiniz Ürün Adı :");
				String name = scanner.nextLine();
				
				System.out.println("Ürünün Açıklaması : ");
				String description = scanner.nextLine();
				
				System.out.println("Ürün Fiyatı :");
				Double price = scanner.nextDouble();
				scanner.nextLine();
				
				String sql = "Insert Into MenuItems (name, description, price) values (?, ?, ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setString(2, description);
				stmt.setDouble(3, price);

				int rowIns = stmt.executeUpdate();
				
				if(rowIns > 0) {
					System.out.println("Ürün Başarıyla Eklendi...");
				}
			}	
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}		
	}
	
	
	public static void delMenuItem(Scanner scanner) {
		try(Connection conn = DatabaseConnection.getConnection()) {
			if (conn != null) {
				Menu.showmenu();
				System.out.println("Silmek İstediğiniz Ürünün ID'si : ");
				int id = scanner.nextInt();
				scanner.nextLine();
				
				String sql = "Delete From MenuItems WHERE id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				
				int rowIns = stmt.executeUpdate();
				if(0 < rowIns) {
					System.out.println("Ürün Başarıyla Silindi...");
				}else {
					System.out.println("Ürün Bulunamadı...");
				}
						
			}			
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	public static void updateMenuItems(Scanner scanner) {
		try(Connection conn = DatabaseConnection.getConnection()) {
			if (conn != null) {
				Menu.showmenu();
				System.out.println("Güncellemek İstediğiniz Ürünün ID'si :");
				int id = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Yeni İsim :");
				String newname = scanner.nextLine();
				
				System.out.println("Yeni Description :");
				String newdes = scanner.nextLine();
				
				System.out.println("Yeni Fiyat :");
				double newprc = scanner.nextDouble();
				scanner.nextLine();
				
				String sql = "Update MenuItems SET name = ? , description=? , price = ? WHERE id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, newname);
				stmt.setString(2, newdes);
				stmt.setDouble(3, newprc);
				stmt.setInt(4, id);
				
				int updrow = stmt.executeUpdate();
				
				if (updrow > 0 ) {
					System.out.println(" Ürün Güncellemesi Başarılı");
				}else {
					System.out.println("Ürün Bulunuamadı");
				}
				
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
