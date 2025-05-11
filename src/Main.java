import java.util.Scanner;

public class Main {

	public static void main(String[] args) {


		
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Restaurant Yönetim Sistemin Hoş Geldiniz...");
		System.out.println("Nasıl Giriş Yapmak İstersiniz "
				+"\n - Müşteri    için   =>  1"
				+"\n - Kurye      için   =>  2"
				+"\n - Şef        için   =>  3"
				+"\n - Yönetici   için   =>  4");
		
		int role = scanner.nextInt();
		scanner.nextLine();
		
		String selectedrole = "";

		
		switch (role) {
		case 1: {
			selectedrole = "customer";
			break;
		}case 2: {
			System.out.println("courirer Girildi");
			selectedrole = "courirer";
			break;
		}case 3: {
			System.out.println("Chef Girildi");
			selectedrole = "chef";
			break;
		}case 4: {
			System.out.println("Admin Girildi");
			selectedrole = "admin";
			break;
		}
		default:
			System.out.println("Geçerli Değer Giriniz...");
			
		}
		
		if(selectedrole.equals("customer")) {
			System.out.println(" - Giriş Yapmak   için  => 1"
						  + "\n - Kayıt Olmak    için  => 2");
			int cusChoice = scanner.nextInt();
			scanner.nextLine();
			
			if(cusChoice == 1) {	//giriş
				int UserId = Customer.loginUser(scanner, selectedrole);
				System.out.println("========== Müşteri Ekranına Hoşgeldiniz =========="
								+  "\n - Sipariş Vermek             için   =>  1 "
								+  "\n - Sipariş Durumunu Görmek    için   =>  2 "
								+  "\n - Bilgilerini Güncellemek    için   =>  3 ");
				
				int cos2 = scanner.nextInt();
				scanner.nextLine();
				if(cos2 == 1) {
					Menu.createOrder(UserId, scanner);
				}
				else if(cos2 == 2) {
					OrderManagement.viewcustomerorders(UserId);
				}else if (cos2 ==3) {
					Customer.updateCustomer(UserId, scanner);
				}else {
					System.out.println("Geçerli Değer Giriniz...");
				}
			}
			
			else if (cusChoice == 2) {	//kayıt
				Customer.registercustomer(scanner);
				Customer.loginUser(scanner, selectedrole);
			}
			else {
				System.out.println("Geçersiz Değer Girdiniz...");
			}
		}
		else if(selectedrole.equals("courirer")) {
			Customer.loginUser(scanner, selectedrole);
			System.out.println("========== Kurye Ekranına Hoşgeldiniz ==========");
			OrderManagement.viewYoldaOrders();
		}
		else if(selectedrole.equals("chef")) {
			Customer.loginUser(scanner, selectedrole);
			System.out.println("========== Chef Ekranına Hoşgeldiniz =========="
					+  "\n - Menüyü Görmek              için   =>  1 "
					+  "\n - Sipariş Durumunu Görmek    için   =>  2");
			int chef2 = scanner.nextInt();
			scanner.nextLine();
			if(chef2 == 1) {
				Menu.showmenu();
			}
			else if(chef2 == 2) {
				OrderManagement.viewhazirlananorders();
				System.out.println();
				System.out.println("***** Hazırlanan Siparişlerin Sipariş ID İşaretleniyiniz... *****");
				int chf3 = scanner.nextInt();
				scanner.nextLine();
				OrderManagement.updateOrderStatus(chf3, "Yolda");
			}
		}else if (selectedrole.equals("admin")) {
			Customer.loginUser(scanner, selectedrole);
			System.out.println("========== Admin Paneline Hoşgeldiniz =========="
					+  "\n - Tüm Siparişleri Görmek						için   =>  1 "
					+  "\n - Menüyü Güncellemek							için   =>  2 "
					+  "\n - Kullancıları Güncellemek						için   =>  3 ");
			int adm2 = scanner.nextInt();
			scanner.nextLine();
			if(adm2 == 1) {
				OrderManagement.viewadmin();
			}
			else if (adm2 == 2) {
				admin.adminmenu();
			}else if (adm2 == 3) {
				Users.adminUser();
			}else {
				System.out.println("Geçerli Değer Giriniz...");
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
