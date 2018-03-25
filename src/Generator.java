import java.util.Random;

/**
 * Generator class
 * Generates name, ean and price of product
 * @author Patryk
 *
 */
public class Generator {

	private static Random r = new Random();
	private static final int EAN_LENGTH = 13;
	public static final String[] ADJECTIVES = {"small","big","red","black","white","green","blue","yellow","orange","gray",
			"violet","pink","new","old","modern","old-fashioned"};
	public static final String[] NOUNS = {"computer","keyboard","pen","pencil","knife","phone","monitor","notebook",
			"chair","table","car","toy","bag","knapsack","shoes","t-shirt","trousers","dress","sweater"};
	
	public static String generateName() {
		String adjective = ADJECTIVES[r.nextInt(ADJECTIVES.length)];
		String noun = NOUNS[r.nextInt(NOUNS.length)];
		return adjective + " " + noun;
	}
	
	public static String generateEan() {
		StringBuilder ean = new StringBuilder();
		for(int i = 0;i<EAN_LENGTH;i++) {
			ean.append(r.nextInt(9));
		}
		return ean.toString();
	}
	
	public static int generatePrice() {
		return r.nextInt(1000)+1;
	}
	
	public static Product generateProduct() {
		return new Product(generateName(), generateEan(), generatePrice());
	}
	
	
	
}
