import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class
 * @author Patryk
 *
 */
public class Main {

	public static void main(String[] args) {
		Shop shop = null;
		try {
			shop = new Shop();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		Cart cart = new Cart();
		System.out.println("Welcome in our shop! Choose what you want to do: \n Type 'scan' if you want to scan item "
				+ "manually \n Type 'random' if you don't know any barcode and you want to add random item to your cart \n"
				+ " Type 'exit' if you want to finish shopping");
		while(true) {
		System.out.print("Your choice: ");
		String choice;
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextLine();
		if(choice.equals("exit")) {
			break;
		}else if(choice.equals("scan")) {
			System.out.print("Type ean of product (13 digits): ");
			String ean = scanner.nextLine();
			if(!shop.findProductInDatabase(ean, cart)) {
				System.out.println("Product not found");
			}
		}else if(choice.equals("random")) {
			Random r = new Random();
			int index = r.nextInt(shop.getProducts().size());
			System.out.println(shop.getProducts().get(index));
			cart.add(shop.getProducts().get(index));
			System.out.println("Added to cart");
			
		}else {
			System.out.println("Invalid command. Try Again");
		}
		}
		cart.printingReceipt();
		System.out.print("Goodbye!");
	

	}

}
