import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class FeedbackDAO_JDBC implements FeedbackDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public FeedbackDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}
	@Override
	public void getFeedbackById(int id){
		PreparedStatement prestmt = null;
		Feedback s = new Feedback();
		String sql;
		sql = "select customer_detail,des,rating from feedback where product_detail=?";
		//Statement stmt= null;
		try{
			prestmt = dbConnection.prepareStatement(sql);
			prestmt.setInt(1,id);
			ResultSet rs = prestmt.executeQuery();

			while(rs.next()){
				String desc = rs.getString("des");
				int rating = rs.getInt("rating");
				int customer_id = rs.getInt("customer_detail");
				//s.setDesc(desc);
				//s.setRating(rating);
				//s.setCustomerDetail(customer_id);
				System.out.println("Description : "+desc);
				System.out.println("Rating : "+rating);
				System.out.println("ProductId : "+customer_id);
			}


		}
		catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
	//return s;

	}
	@Override
	public void addFeedback(Feedback feedback){
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into feedback(customer_detail,product_detail,des,rating) values(?,?,?,?)";
		try{
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setInt(1, feedback.getCustomerDetail());
			preparedStatement.setInt(2, feedback.getProductDetail());
			preparedStatement.setString(3,feedback.getDes());
			preparedStatement.setInt(4,feedback.getRating());

			preparedStatement.executeUpdate();
			System.out.println("Feedback: added to the database");
 

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
	public void deleteFeedbackById(int  product_id){
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "delete from feedback where product_detail=?";
		try{
			preparedStatement = dbConnection.prepareStatement(sql);
 			preparedStatement.setInt(1,product_id);
			//preparedStatement.setString(1, product.getDescription());
			//preparedStatement.setInt(2, product.getPrice());
			//preparedStatement.setString(3,product.getImages());

			preparedStatement.executeUpdate();
			System.out.println("Feedback"
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

	

