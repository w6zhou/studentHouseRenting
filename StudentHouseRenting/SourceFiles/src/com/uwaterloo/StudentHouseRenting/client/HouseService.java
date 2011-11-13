package com.uwaterloo.StudentHouseRenting.client;

import java.util.Date;
import java.util.Queue;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.uwaterloo.StudentHouseRenting.shared.House;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("houseService")
public interface HouseService extends RemoteService {
	Queue<House> updateHouseList(Date date, float price);
}
