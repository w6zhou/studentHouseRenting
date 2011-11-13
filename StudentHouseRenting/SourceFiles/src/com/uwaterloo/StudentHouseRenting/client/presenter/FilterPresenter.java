package com.uwaterloo.StudentHouseRenting.client.presenter;

import java.util.Date;
import java.util.Queue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.uwaterloo.StudentHouseRenting.client.HouseServiceAsync;
import com.uwaterloo.StudentHouseRenting.client.event.ShowListEvent;
import com.uwaterloo.StudentHouseRenting.client.event.ShowMapEvent;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class FilterPresenter implements Presenter{
	private final HandlerManager eventBus;
	private final Display display;
	private HouseServiceAsync rpcService;
	
	public interface Display {
	    HasClickHandlers getMapButton();
	    HasClickHandlers getListButton();
	    HasValue<String> getPrice();
	    HasValue<Date> getDate();
	    Widget asWidget();
	}
	  

	public FilterPresenter(HouseServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    bind();
	}
	
	public void bind() {
		this.display.getMapButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doFilterMap();
			}
		});
		
		this.display.getListButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doFilterList();
			}
		});
	}

	public void go(final HasWidgets container, Queue<House> houseList) {
	    container.clear();
	    container.add(display.asWidget());
	  }
	
	private void doFilterMap() {
		String priceValue=display.getPrice().getValue();
		Date date=display.getDate().getValue();
		int price=9999;
		if(priceValue.equals("")){
			priceValue="9999";
		}
		try{
			price=Integer.parseInt(priceValue);
			rpcService.updateHouseList(date, price, new AsyncCallback<Queue<House>>() {
		        public void onSuccess(Queue<House> houseList) {
		          eventBus.fireEvent(new ShowMapEvent(houseList));
		        }
		        public void onFailure(Throwable caught) {
		          Window.alert("Error updating houseList");
		        }
		    });
		}catch(Exception e){
	          Window.alert("Please put valid number for price");
		}
	}
	
	private void doFilterList() {
		String priceValue=display.getPrice().getValue();
		Date date=display.getDate().getValue();
		int price=9999;
		if(priceValue.equals("")){
			priceValue="9999";
		}
		try{
			price=Integer.parseInt(priceValue);
			rpcService.updateHouseList(date, price, new AsyncCallback<Queue<House>>() {
		        public void onSuccess(Queue<House> houseList) {
		          eventBus.fireEvent(new ShowListEvent(houseList));
		        }
		        public void onFailure(Throwable caught) {
		          Window.alert("Error updating houseList");
		        }
		    });
		}catch(Exception e){
	          Window.alert("Please put valid number for price");
		}
	}
}
