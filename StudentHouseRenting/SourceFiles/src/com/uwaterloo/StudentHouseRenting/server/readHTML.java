package com.uwaterloo.StudentHouseRenting.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;

import com.uwaterloo.StudentHouseRenting.shared.House;

public class readHTML {
	static String base = "https://listings.och.uwaterloo.ca/";
	static String url = "Classifieds/Search/Results?cid=2&p=1&Classifieds-page=";

	public static void main(String[] args) throws Exception {
		Queue<House> que = new LinkedList<House>();
		//for(int i=0;i<13;i++){
			getData(1,que);
		//}
		while(!que.isEmpty()){
		 que.poll().print();
		 }
	}
	
	public static Queue<House> getData(int page, Queue<House> que) throws IOException {
		try{
		URL oracle = new URL(base + url + page);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine, data = "";

		while ((inputLine = in.readLine()) != null) {
			data += inputLine;
		}

		data = data
				.substring(data.indexOf("<tbody>"), data.indexOf("</tbody>"));

		while (data.indexOf("<tr") > 0) {
			String post = data.substring(data.indexOf("<tr"),
					data.indexOf("</tr>"));
			// Get Image
			String img = post.substring(post.indexOf("<td>") + 4,
					post.indexOf("</td>"));
			img = img.substring(img.indexOf("<img"), img.indexOf("/>"));
			if (img.contains("No Picture")) {
				img = "NO PIC";
			} else {
				img = img.substring(img.indexOf("\"") + 1,
						img.indexOf("\"", img.indexOf("\"") + 1));
			}
			

			// Get URL to Post Detail
			post = post.substring(post.indexOf("</td>") + 5);
			String postURL = post.substring(post.indexOf("<a href=\"") + 9,
					post.indexOf("\">", post.indexOf("<a href=\"") + 8));
			// Get Title of Post
			post = post.substring(post.indexOf(postURL) + postURL.length() + 2);
			String title = post.substring(0, post.indexOf("<br/>")).trim();
			post = post.substring(post.indexOf(title) + title.length() + 2);
			// Get Posted Date
			post = post.substring(post.indexOf("<em>") + 4);
			String postDate = post.substring(post.indexOf("</em>") + 5,
					post.indexOf("<br/>"));
			post = post.substring(post.indexOf(postDate) + postDate.length()
					+ 2);
			String googleLocationURL = post.substring(
					post.indexOf("href='") + 6,
					post.indexOf("'", post.indexOf("href='") + 6));
			post = post.substring(post.indexOf(googleLocationURL)
					+ googleLocationURL.length() + 2);
			// Get Address
			String address = post.substring(post.indexOf("'>") + 2,
					post.indexOf("</a>"));
			post = post.substring(post.indexOf(address) + address.length() + 2);
			String contact = post.substring(post.indexOf("</em>") + 5,
					post.indexOf("<br/>", post.indexOf("</em>")));
			post = post.substring(post.indexOf(contact) + contact.length() + 2);
			// Get Availability
			String available = post.substring(post.indexOf("</em>") + 5,
					post.indexOf("<br />", post.indexOf("</em>")));
			post = post.substring(post.indexOf(available) + available.length()
					+ 2);
			//System.out.println(post);

			post = post.substring(post.indexOf("\">") + 2);
			post = post.substring(post.indexOf("\">") + 2);
			// Get Email
			//System.out.println(post);
			//String email = post.substring(post.indexOf("\">") + 2,
					//post.indexOf("</a>")).trim();
			//post = post.substring(post.indexOf("\">") + 2);
			//post = post.substring(post.indexOf(email) + email.length() + 2);
			// Get Price
			post = post.substring(post.indexOf("</td>") + 5);
			String price = post.substring(post.indexOf("<td>") + 4,
					post.indexOf("</td>")).trim();
			price = price.replace(" ", "");
			// System.out.println(price);
			data = data.substring(data.indexOf(post) + post.length() + 4);
			
			String detail = "";
			try {
				oracle = new URL(base + postURL);
				yc = oracle.openConnection();
				in = new BufferedReader(new InputStreamReader(
						yc.getInputStream()));
				//String inputLine, data = "";

				while ((inputLine = in.readLine()) != null) {
					detail += inputLine;
				}
				detail = detail.substring(detail.indexOf("<textarea"), detail.indexOf("</textarea>"));
				detail = detail.substring(detail.indexOf("\">")+2);
				//this.detail = data;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			que.offer(new House(base + img, base + postURL, title, postDate,
					googleLocationURL, address, contact, "", available,
					price, detail));
		}

		in.close();
		}catch(Exception e){
			e.printStackTrace();
			return que;
		}
		return que;
	}
}