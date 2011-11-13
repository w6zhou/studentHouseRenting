package com.uwaterloo.StudentHouseRenting.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowDetailEvent extends GwtEvent<ShowDetailEventHandler> {

	public static Type<ShowDetailEventHandler> TYPE = new Type<ShowDetailEventHandler>();

	public Type<ShowDetailEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowDetailEventHandler handler) {
	    handler.onShowDetail(this);
	}

}
