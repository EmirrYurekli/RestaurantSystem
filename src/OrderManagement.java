import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagement {


	//Müşteri Sipariş Durumu İzleme ---------------------------------------------------
	
	public static void viewcustomerorders(int UserId) {
		try(Connection conn = DatabaseConnection.getConnection()){
			String sql = "Select * from Orders Where UserId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, UserId);
			ResultSet rs = stmt.executeQuery();
			
			System.out.println("========== Siparişleriniz ==========");
			while(rs.next()) {
				int orderId = rs.getInt("id");
				String status = rs.getString("status");
				System.out.println("Sipariş ID: " + orderId + ", Durum: " + status);
			}
				
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	
	
	
	//Şefler için Hazırlananları görmek ------------------------------------------------------------
	
	public static void viewhazirlananorders() {
		try (Connection conn = DatabaseConnection.getConnection()){
			
			if(conn != null) {
				String sql = "select * from orders WHERE status = 'Hazırlanıyor'";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				System.out.println("========== Hazırlanacak Siparişler ==========");
				while(rs.next()) {
					int orderId = rs.getInt("id");
					int userId = rs.getInt("UserId");
					System.out.println("Sipariş ID: " + orderId + ", Kullanıcı ID: " + userId);
				}
			}		
			
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	//Kurye için Yolda olanları görmek --------------------------------------------------
	
	public static void viewYoldaOrders() {
		try (Connection conn = DatabaseConnection.getConnection()){
			
			if(conn != null) {
				String sql = "Select * from orders WHERE status = 'Yolda'";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				System.out.println();
				System.out.println("***** Yola Çıkacak Siparişler *****");
				while(rs.next()) {
					int orderId = rs.getInt("id");
					int userId = rs.getInt("UserId");
					System.out.println("Sipariş ID: " + orderId + ", Kullanıcı ID: " + userId);
				}
				if(rs.next()) {
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası " + e.getMessage());
		}
	}
	
	//Sipariş Durum Güncelleme -----------------------------------------
	
	 public static void updateOrderStatus(int orderId, String newStatus) {
	        try (Connection conn = DatabaseConnection.getConnection()) {
	            if (conn != null) {
	                String sql = "UPDATE Orders SET status = ? WHERE id = ?";
	                PreparedStatement stmt = conn.prepareStatement(sql);
	                stmt.setString(1, newStatus);
	                stmt.setInt(2, orderId);

	                int updatedRows = stmt.executeUpdate();
	                if (updatedRows > 0) {
	                    System.out.println("Sipariş durumu başarıyla " + newStatus + " olarak güncellendi!");
	                } else {
	                    System.out.println("Sipariş bulunamadı!");
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Veritabanı Hatası: " + e.getMessage());
	        }
	    }
	
	public static void viewadmin() {
		try(Connection conn = DatabaseConnection.getConnection()) {
			if(conn != null) {
				String sql = "select * from Orders";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				System.out.println("========== Tüm Siparişler ==========");
				while(rs.next()) {
					int orderId = rs.getInt("id");
					int userId = rs.getInt("UserId");
					String status = rs.getString("status");
					System.out.println("Sipariş ID: " + orderId + ", Kullanıcı ID: " + userId + ", Durumu: " + status);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Veritabanı Hatası: " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
