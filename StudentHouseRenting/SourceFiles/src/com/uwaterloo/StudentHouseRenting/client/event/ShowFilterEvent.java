package com.uwaterloo.StudentHouseRenting.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowFilterEvent extends GwtEvent<ShowFilterEventHandler> {

	public static Type<ShowFilterEventHandler> TYPE = new Type<ShowFilterEventHandler>();
	@Override
	public Type<ShowFilterEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowFilterEventHandler handler) {
	    handler.onShowFilter(this);		
	}

}
