package com.uwaterloo.StudentHouseRenting.shared;
import java.io.Serializable;
@SuppressWarnings("serial")
public class House implements Serializable {

	private String img = "";
	private String postUrl = "";
	private String title = "";
	private String postDate = "";
	private String googleURL = "";
	private String address = "";
	private String contact = "";
	private String email = "";
	private String available = "";
	private String price = "";
	private String detail = "";

	public House() {}
	
	public House(String img, String postUrl, String title, String postDate, String googleURL, String address, String contact, String email, String available, String price, String detail){
		this.img = img;
		this.postUrl = postUrl;
		this.title = title;
		this.postDate = postDate;
		this.googleURL = googleURL;
		this.address = address;
		this.contact = contact;
		this.email = email;
		this.available = available;
		this.price = price;
		this.detail=detail;
		/*try {
			URL oracle;
			oracle = new URL(postUrl);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine, data = "";

			while ((inputLine = in.readLine()) != null) {
				data += inputLine;
			}
			data = data.substring(data.indexOf("<textarea"), data.indexOf("</textarea>"));
			data = data.substring(data.indexOf("\">")+2);
			this.detail = data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	public String getDetail(){
		//Get the Details of the Post
		return detail;
	}
	public String getImg(){
		//Get the Image URL, if no URL the returns NO PIC
		return img;
	}
	public String getPostUrl(){
		//Get the URL of the Post with details
		return postUrl;
	}
	public String getTitle(){
		//Get the Title of the post
		return title;
	}
	public String getPostDate(){
		//Get the date posted
		return postDate;
	}
	public String getGoogleMapUrl(){
		//returns URL of google map of the address
		return googleURL;
	}
	public String getAddress(){
		//Returns the address of the house
		return address;
	}
	public String getContact(){
		//Returns the contact information
		return contact;
	}
	public String getEmail(){
		//Returns the email of the contact
		return email;
	}
	public String getAvailableDate(){
		//Returns the house' availability
		return available;
	}
	public String getPriceRange(){
		//Returns the house Range
		return price;
	}
	public void print(){
		//Prints out all the data, for debugging
		System.out.println("Image: " + img);
    	System.out.println("Post URL: " + postUrl);
    	System.out.println("Title: " + title);
    	System.out.println("Post Date: " + postDate);
    	System.out.println("Google: " + googleURL);
    	System.out.println("Address: " + address);
    	System.out.println("Contact: " + contact);
    	System.out.println("Email: " + email);
    	System.out.println("Available: " + available);
    	System.out.println("Price: " + price);
    	System.out.println("Details: " + detail);
	}
}
