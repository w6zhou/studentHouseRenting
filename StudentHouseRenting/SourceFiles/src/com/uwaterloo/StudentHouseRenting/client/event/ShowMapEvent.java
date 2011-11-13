package com.uwaterloo.StudentHouseRenting.client.event;

import java.util.Queue;

import com.google.gwt.event.shared.GwtEvent;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class ShowMapEvent extends GwtEvent<ShowMapEventHandler> {

	public static Type<ShowMapEventHandler> TYPE = new Type<ShowMapEventHandler>();
	private final Queue<House> houseList;
	
	public ShowMapEvent(Queue<House> houseList) {
		this.houseList=houseList;
	}
	
	public Queue<House> getHouseList(){
		return houseList;
	}

	@Override
	public Type<ShowMapEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowMapEventHandler handler) {
	    handler.onShowMap(this);
	}

}
