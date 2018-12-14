import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ProductDAO_JDBC implements ProductDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public ProductDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}
	@Override
	public Product getProductByName(String name){
		Product p = new Product();
		PreparedStatement prestmt = null;
		String sql;
		sql = "select description,price from product where description=?";

		//Product p = new Product();
		//String sql;
		//Statement stmt= null;
		try{
			prestmt = dbConnection.prepareStatement(sql);
			prestmt.setString(1,name);

			//sql = "select description,price from product";
			ResultSet rs = prestmt.executeQuery();
			while(rs.next()){
				String desc = rs.getString("description");
				int price = rs.getInt("price");
				p.setDescription(desc);
				p.setPrice(price);
			}

		}
		catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		return p;

	}


	@Override
	public int getLastRecordId(){

		PreparedStatement prestmt = null;
		String sql;
		sql = "select product_id from product";
		int product_id=0;
		;
		Statement stmt= null;
		try{
			stmt = dbConnection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			rs.last();

			
			product_id = rs.getInt("product_id");
			//System.out.println(product_id);

		}
		catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		
		return product_id;

	}


	@Override
	public void addProduct(Product product){
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into product(description,price,images) values(?,?,?)";
		try{
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, product.getDescription());
			preparedStatement.setInt(2, product.getPrice());
			preparedStatement.setString(3,product.getImages());

			preparedStatement.executeUpdate();
			System.out.println("Product: Name " + product.getDescription() 
				+ ", added to the database");
 

		}
		catch(SQLException e){
			System.out.println(e.getMessage());
 		}
 		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

	@Override
	public void deleteProduct(int  id){
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "delete from product where product_id=?";
		try{
			preparedStatement = dbConnection.prepareStatement(sql);
 			preparedStatement.setInt(1,id);
			//preparedStatement.setString(1, product.getDescription());
			//preparedStatement.setInt(2, product.getPrice());
			//preparedStatement.setString(3,product.getImages());

			preparedStatement.executeUpdate();
			System.out.println("Product"
				+ ", deleted from the database");
 

		}
		catch(SQLException e){
			System.out.println(e.getMessage());
 		}
 		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

}

	

