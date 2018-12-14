//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;
public class DAO_driver {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try{
			DAO_Factory daoFactory = new DAO_Factory();

			daoFactory.activateConnection();
			
		
			System.out.println("Choose any one -->:");
			System.out.println("1.View Products");
			System.out.println("2.Add Products");
			System.out.println("3.Delete Product");
			System.out.println("4.View Feedbacks");
			System.out.println("5.Give Feedback");
			System.out.println("6.Exit");

			while(true){
				int a;
				a = s.nextInt();
				if(a==1 ){
					String name;
					System.out.println("Enter Product name : ");
					name = s.next();
					ProductDAO pdao = daoFactory.getProductDAO();
					Product p = pdao.getProductByName(name);
					p.print();
				}
				if(a==2 ){
					System.out.println("Enter Product Name : ");
					String name;
					name = s.next();
					System.out.println("Enter it Price :");
					int  price;
					price = s.nextInt();
					System.out.println("Enter Images links :");
					String image;
					image = s.next();
					ProductDAO pdao = daoFactory.getProductDAO();
					int product_id = pdao.getLastRecordId();
					Product p = new Product(product_id,name,price,image);
					pdao.addProduct(p);

				}
				if(a==3){
					int product_id;
					System.out.println("Enter Product Id :");
					product_id = s.nextInt();
					ProductDAO pdao= daoFactory.getProductDAO();
					pdao.deleteProduct(product_id);
					FeedbackDAO fdao = daoFactory.getFeedbackDAO();
					fdao.deleteFeedbackById(product_id);					
				}
				if(a==4 ){
					int product_id;
					System.out.println("Enter Product Id: ");
					product_id = s.nextInt();
					FeedbackDAO fdao = daoFactory.getFeedbackDAO();
					fdao.getFeedbackById(product_id);
					//f.print();

				}
				if(a==5){
					System.out.println("Enter Customer id : ");
					int customer_id;
					customer_id = s.nextInt();
					System.out.println("Enter product id : ");
					int product_id;
					product_id = s.nextInt();
					
					System.out.println("Give rating(1,2,3,4,5) : ");
					int rat;
					rat = s.nextInt();
					System.out.println("Give description : ");
					String desc;
					desc = s.next();
					FeedbackDAO fdao = daoFactory.getFeedbackDAO();
					Feedback f = new Feedback(customer_id,product_id,desc,rat);
					fdao.addFeedback(f);

				}
				if(a==6){
					break;
				}
				
			}



			//ProductDAO pdao = daoFactory.getProductDAO();
			//Product p = pdao.getProductByName("TrackPants");
			//p.print();
			//Product p = new Product(4,"Racket","1700","http://");
			//pdao.addProduct(p);

			/*
			System.out.println("Hello");
			FeedbackDAO fdao = daoFactory.getFeedbackDAO();
			System.out.println("Hello");
			Feedback f = new Feedback(2,2,2,"expensive",5);
			fdao.addFeedback(f);
			//Feedback f = fdao.getFeedbackById(2);
			//f.print();

			*/
			daoFactory.deactivateConnection();
		}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
		}
	}//end main
}//end FirstExample
