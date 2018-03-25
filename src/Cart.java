import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Cart class
 * @author Patryk
 *
 */
public class Cart {

	private LinkedList<Product> products;
	private int value;
	
	public Cart() {
		this.products = new LinkedList<>();
		this.value = 0;
	}
	
	public void add(Product product) {
		this.products.add(product);
	}
	
	public void calculateValue() {
		this.value = 0;
		for(int i=0;i<products.size();i++) {
			this.value += products.get(i).getPrice();
		}
	}
	
	public void printingReceipt() {
		calculateValue();
		for(int i=0;i<products.size();i++) {
			System.out.println(products.get(i));
		}
		System.out.println("Total Value: "+value);
		try {
			System.out.println("Printer is printing your receipt...");
			saveReceipt();
			System.out.println("Done.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveReceipt() throws IOException {
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("receipt.txt"));
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(output));
		printWriter.println("RECEIPT");
		printWriter.println("--------");
		for(int i=0;i<products.size();i++) {
			printWriter.println(products.get(i).toString());
		}
		printWriter.println("--------");
		printWriter.write("TOTAL VALUE: "+value);
		printWriter.close();
		output.close();
	}

	public LinkedList<Product> getProducts() {
		return products;
	}

	public void setProducts(LinkedList<Product> listOfProducts) {
		this.products = listOfProducts;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
