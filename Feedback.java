import java.lang.*;

public class Feedback{
	int feedback_id;
	int customer_detail;
	int product_detail;
	String des;
	int rating;

	Feedback(){}
	Feedback(int c_detail,int p_detail,String d,int r){
		customer_detail = c_detail;
		product_detail = p_detail;
		des = d;
		rating = r;
	}

	public int getFeedbackId(){return feedback_id;}
	public int getCustomerDetail(){return customer_detail;}
	public int getRating(){return rating;}
	public int getProductDetail(){return product_detail;}
	public String getDes(){return des;}
	public void setDesc(String d){des = d;}
	public void setRating(int r){rating = r;}
	public void setCustomerDetail(int c){customer_detail = c;}
	public void setProductDetail(int p_d){product_detail = p_d;}
	public void print(){
		System.out.println("Custoer Id: "+customer_detail);
		System.out.println("Description: "+des);
		System.out.println("Rating: "+rating);
	}
}