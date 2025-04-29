import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	
	//Menüyü Gösterme -----------------------------------------------------------

	public static void showmenu() {	
		try (Connection conn = DatabaseConnection.getConnection()){
			
			if(conn != null) {
				String sql = "Select * from MenuItems";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				System.out.println("========== Menu ==========");
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String description = rs.getString("description");
					double price = rs.getDouble("price");
					
					System.out.println(id + "  -  " + name + " (" + description + ")   =>" + price + "₺");
				}
				
				
			}
			
			
		}catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	
	//Sipariş Oluşturma -------------------------------------------------------
	
	public static void createOrder(int UserId , Scanner scanner) {

		try(Connection conn = DatabaseConnection.getConnection()){
			if(conn != null) {
				
				String osql = "INSERT INTO Orders (UserId, status) VALUES (?, 'Hazırlanıyor')";
				PreparedStatement ostmt = conn.prepareStatement(osql, PreparedStatement.RETURN_GENERATED_KEYS);
				ostmt.setInt(1, UserId);
				ostmt.executeUpdate();
				
				ResultSet rset = ostmt.getGeneratedKeys();
				int orderId = -1;
				
				if(rset.next()) {
					orderId = rset.getInt(1);
				}
				
				if(orderId == -1) {
					System.out.println("Sipariş Oluşturulamadı !");
					return;
				}
				
				boolean eklemeyedevam = true;
				
				while(eklemeyedevam) {
					showmenu();
					
					System.out.println("Sipariş Vermek İstediğiniz Ürünün ID'sini Giriniz : ");
					int menuItemId = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("Kaç Adet İstiyorsunuz : ");
					int quantity = scanner.nextInt();
					scanner.nextLine();
					
					//Tabloya ekleme yeri
					
					String itemSql = "Insert Into OrderItems (orderId, menuItemId, quantity) values (?, ?, ?) ";
					PreparedStatement Itemstmt = conn.prepareStatement(itemSql);
					Itemstmt.setInt(1, orderId);
					Itemstmt.setInt(2, menuItemId);
					Itemstmt.setInt(3, quantity);
					Itemstmt.executeUpdate();
					
					//Daha Fazla Ürün ekletme Yeri
					System.out.println("Ürününüz Başarıyla Eklendi. Ürün Eklemeye Devam Etmek İster misiniz ?"
							+ "\n Evet ise e  / Hayır ise h 'ye basınız.");
					
					String devamke = scanner.nextLine();
					if(devamke.equals("h")) {
						eklemeyedevam = false;
					}
					else if(devamke.equals("e")) {
						showmenu();
					}
					else {
						System.out.println("Geçerli Değer Giriniz !!!");
					}
				}
				
				System.out.println("Ürünleriniz Başarıyla Oluşturulmuştur. Sipariş Ekranını Takip Edebilirsiniz...");
				
			}
		}catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	public static void updatemenu () {
		showmenu();
		
		
		
		
		
		
		try(Connection conn = DatabaseConnection.getConnection()) {
			if(conn != null) {
			
				
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
