import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Shop class
 * @author Patryk
 *
 */

public class Shop {

	private LinkedList<Product> products;
	Connection connection;
	
	public Shop() throws SQLException{
		this.products = new LinkedList<>();
		connection = SqliteConnection.Connector();
		System.out.println("Creating database with 35 items. Please wait...");
		generateDatabase();
		for(int i=0;i<35;i++) {
			addProductToDatabase(Generator.generateProduct());
		}
	}
	
	public void generateDatabase() throws SQLException {
		if(isDbConnected()) {
			PreparedStatement preparedStatement = null;
			String query = "create table if not exists Products (EAN varchar(13) primary key unique,name varchar(30),price integer)";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
			
			query = "delete from Products";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
			
			preparedStatement.close();

		}
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void addProductToDatabase(Product product){
		PreparedStatement preparedStatement = null;
		
		String query = "insert into Products values(?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, product.getEan());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setInt(3, product.getPrice());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		products.add(product);
		
	}
	
	public boolean findProductInDatabase(String ean, Cart cart){
		if(ean.length() != 13) {
			System.out.println("Wrong EAN!");
			return false;
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Products where EAN = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ean);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				preparedStatement.close();
				resultSet.close();
				for(int i=0;i<products.size();i++) {
					if(products.get(i).getEan().equals(ean)) {
						System.out.println(products.get(i));
						cart.add(products.get(i));
						System.out.println("Added to cart");
						break;
					}
				}
				return true;
			}else {
				preparedStatement.close();
				resultSet.close();
				return false;
			}
			
		} catch (SQLException e) {
			return false;
		}
	}
	
	public LinkedList<Product> getProducts() {
		return products;
	}
	public void setProducts(LinkedList<Product> products) {
		this.products = products;
	}
}
