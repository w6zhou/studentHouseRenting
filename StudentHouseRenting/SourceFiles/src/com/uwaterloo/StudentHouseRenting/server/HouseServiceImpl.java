package com.uwaterloo.StudentHouseRenting.server;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.uwaterloo.StudentHouseRenting.client.HouseService;
import com.uwaterloo.StudentHouseRenting.shared.House;

/**
 * The server side implementation of the RPC service.
 */

@SuppressWarnings("serial")
public class HouseServiceImpl extends RemoteServiceServlet implements
		HouseService {

	public Queue<House> updateHouseList(Date date, float price) {
		Queue<House> result=new LinkedList<House>();
		Queue<House> que = new LinkedList<House>();
		SimpleDateFormat df=new SimpleDateFormat("MMM dd, yyyy");
		
		//for(int i=0;i<13;i++){
			try {
				readHTML.getData(1,que);
				readHTML.getData(2,que);
				readHTML.getData(3,que);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
			
			while(!que.isEmpty()){
				House curHouse=que.poll();
				String sPrice=curHouse.getPriceRange();
				StringTokenizer st=new StringTokenizer(sPrice, "$-");
				while(st.hasMoreTokens()){
					sPrice=st.nextToken();
				}
				st=new StringTokenizer(curHouse.getAvailableDate(), "-");
				Date dateBef=null;
				Date dateAfter=null;
				try {
					dateBef = df.parse(st.nextToken());
					dateAfter=df.parse(st.nextToken().substring(1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(date!=null&&dateBef!=null&&dateAfter!=null){
					if(Float.parseFloat(sPrice)<price&&date.after(dateBef)&&date.before(dateAfter)){
						result.add(curHouse);
					}
				}else{
					if(Float.parseFloat(sPrice)<price){
						result.add(curHouse);
					}					
				}
			}
		return result;
	}

}
