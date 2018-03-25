/**
 * Product class
 * @author Patryk
 *
 */
public class Product {
	private String name;
	private String ean;
	private int price;
	
	public Product() {
		this.name = "";
		this.ean = "";
		this.price = 0;
	}
	
	public Product(String name, String ean, int price) {
		this.name = name;
		this.ean = ean;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name = " + name + ", ean = " + ean + ", price = " + price + "\u20ac]"; // \u20ac = euro symbol
	}
	
	
}
