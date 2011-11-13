package com.uwaterloo.StudentHouseRenting.client;

import java.util.Date;
import java.util.Queue;


//import com.google.gwt.sample.contacts.shared.ContactDetails;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.uwaterloo.StudentHouseRenting.shared.House;

/**
 * The async counterpart of <code>houseService</code>.
 */
public interface HouseServiceAsync {

	public void updateHouseList(Date date, float price, AsyncCallback<Queue<House>> callback);
}
