package com.uwaterloo.StudentHouseRenting.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StudentHouseRenting implements EntryPoint {
	public void onModuleLoad() {
	    HouseServiceAsync rpcService = GWT.create(HouseService.class);
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(rpcService, eventBus);
	    appViewer.go(RootPanel.get(), null);
	  }
}
