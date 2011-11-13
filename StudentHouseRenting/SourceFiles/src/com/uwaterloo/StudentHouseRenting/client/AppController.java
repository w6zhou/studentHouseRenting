package com.uwaterloo.StudentHouseRenting.client;

import java.util.Queue;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.uwaterloo.StudentHouseRenting.client.event.ShowDetailEvent;
import com.uwaterloo.StudentHouseRenting.client.event.ShowDetailEventHandler;
import com.uwaterloo.StudentHouseRenting.client.event.ShowFilterEvent;
import com.uwaterloo.StudentHouseRenting.client.event.ShowFilterEventHandler;
import com.uwaterloo.StudentHouseRenting.client.event.ShowListEvent;
import com.uwaterloo.StudentHouseRenting.client.event.ShowListEventHandler;
import com.uwaterloo.StudentHouseRenting.client.event.ShowMapEvent;
import com.uwaterloo.StudentHouseRenting.client.event.ShowMapEventHandler;
import com.uwaterloo.StudentHouseRenting.client.presenter.DetailPresenter;
import com.uwaterloo.StudentHouseRenting.client.presenter.ListPresenter;
import com.uwaterloo.StudentHouseRenting.client.presenter.MapPresenter;
import com.uwaterloo.StudentHouseRenting.client.presenter.FilterPresenter;
import com.uwaterloo.StudentHouseRenting.client.presenter.Presenter;
import com.uwaterloo.StudentHouseRenting.client.view.DetailView;
import com.uwaterloo.StudentHouseRenting.client.view.FilterView;
import com.uwaterloo.StudentHouseRenting.client.view.ListView;
import com.uwaterloo.StudentHouseRenting.client.view.MapView;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class AppController implements Presenter , ValueChangeHandler<String>{
  private final HandlerManager eventBus;
  private final HouseServiceAsync rpcService; 
  private HasWidgets container;
  
  public AppController(HouseServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
  private void bind() {
	    History.addValueChangeHandler(this);

	    eventBus.addHandler(ShowFilterEvent.TYPE,
	            new ShowFilterEventHandler() {
	              public void onShowFilter(ShowFilterEvent event) {
	                doShowFilter();
	              }
	            });  
	    
	    eventBus.addHandler(ShowListEvent.TYPE,
	            new ShowListEventHandler() {
	              public void onShowList(ShowListEvent event) {
	                doShowList(event.getHouseList());
	              }
	            });  
	    
	    eventBus.addHandler(ShowMapEvent.TYPE,
	            new ShowMapEventHandler() {
	              public void onShowMap(ShowMapEvent event) {
	                doShowMap(event.getHouseList());
	              }
	            });  
	    
	    eventBus.addHandler(ShowDetailEvent.TYPE,
	            new ShowDetailEventHandler() {
	              public void onShowDetail(ShowDetailEvent event) {
	                doShowDetail();
	              }
	            });  
  }

  private void doShowFilter() {
      History.newItem("Filter");
  }
  
  private void doShowMap(Queue<House> queue) {
      History.newItem("Map", false);
	  Presenter presenter = new MapPresenter(rpcService, eventBus, new MapView());
	  presenter.go(container, queue);
  }

  private void doShowList(Queue<House> queue) {
      History.newItem("List", false);
      Presenter presenter = new ListPresenter(rpcService, eventBus, new ListView());
	  presenter.go(container, queue);
  }
  
  private void doShowDetail() {
      History.newItem("Detail");
  }
  
  public void go(final HasWidgets container, Queue<House> houseList) {
    this.container = container;
    
    if ("".equals(History.getToken())) {
      History.newItem("Filter");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {
      Presenter presenter = null;

      if (token.equals("Filter")) {
    	  presenter = new FilterPresenter(rpcService, eventBus, new FilterView());
      }
      else if (token.equals("List")) {
    	  presenter = new ListPresenter(rpcService, eventBus, new ListView());
      }
      else if (token.equals("Map")) {
    	  presenter = new MapPresenter(rpcService, eventBus, new MapView());
      }else if(token.equals("Detail")){
    	  presenter = new DetailPresenter(rpcService, eventBus, new DetailView());
      }
      
      if (presenter != null) {
    	  presenter.go(container, null);
      }
    }
  }

}
