package com.uwaterloo.StudentHouseRenting.client.event;

import java.util.Queue;

import com.google.gwt.event.shared.GwtEvent;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class ShowListEvent extends GwtEvent<ShowListEventHandler> {

	public static Type<ShowListEventHandler> TYPE = new Type<ShowListEventHandler>();
	private final Queue<House> houseList;

	public ShowListEvent(Queue<House> houseList) {
		this.houseList=houseList;
	}

	public Queue<House> getHouseList(){
		return houseList;
	}
	public Type<ShowListEventHandler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(ShowListEventHandler handler) {
	    handler.onShowList(this);
	}

}
