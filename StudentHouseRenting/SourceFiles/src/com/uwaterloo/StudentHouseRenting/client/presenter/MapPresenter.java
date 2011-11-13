package com.uwaterloo.StudentHouseRenting.client.presenter;

import java.util.Queue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.uwaterloo.StudentHouseRenting.client.HouseServiceAsync;
import com.uwaterloo.StudentHouseRenting.client.event.ShowFilterEvent;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class MapPresenter implements Presenter{
	private final HandlerManager eventBus;
	private final Display display;
	private HouseServiceAsync rpcService;
	
	public interface Display {
	    HasClickHandlers getFilterButton();
	    void setMarkers(Queue<House> data);
	    Widget asWidget();
	}
	  

	public MapPresenter(HouseServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    bind();
	}
	
	public void bind() {
		this.display.getFilterButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ShowFilterEvent());
			}
		});
	}

	public void go(final HasWidgets container, Queue<House> houseList) {
  	    container.clear();
	    container.add(display.asWidget());
	    display.setMarkers(houseList);
	}
}
