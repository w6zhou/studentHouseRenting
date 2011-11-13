package com.uwaterloo.StudentHouseRenting.client.view;

import java.util.Queue;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.uwaterloo.StudentHouseRenting.client.presenter.MapPresenter;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class MapView extends Composite implements MapPresenter.Display{
	
	private FlowPanel fPanel;
	private Button filter;
	
	public MapView(){
		fPanel = new FlowPanel();
	    initWidget(fPanel);
	    filter=new Button("Back to Filter");
	    fPanel.add(filter);
	}

	private void buildUI(Queue<House> houseList) { 
	    final MapWidget map = new MapWidget();
		final String center="University of Waterloo";
		Geocoder geocoder=new Geocoder();
		
		geocoder.getLatLng(center, new LatLngCallback() {
		      public void onFailure() {
		        Window.alert(center+" not found");
		      }

		      public void onSuccess(LatLng point) {
		        map.setCenter(point, 13);
		      }
		    });

	    while(!houseList.isEmpty()){
	    	final House house=houseList.poll();
	    	final String address=house.getAddress();

			geocoder=new Geocoder();
			geocoder.getLatLng(address, new LatLngCallback() {
			      public void onFailure() {
			    	  //Window.alert(address+" not found");
			      }

			      public void onSuccess(LatLng point) {
				      final Marker marker = new Marker(point);
				      marker.addMarkerClickHandler(new MarkerClickHandler() {
						public void onClick(MarkerClickEvent event) {
							map.getInfoWindow().open(marker.getLatLng(), new InfoWindowContent(
									 "<b>Contact:        </b>"+house.getContact()+"<br/>"
									+"<b>Email:          </b>"+house.getEmail()+"<br/>"
									+"<b>Available Date: </b>"+house.getAvailableDate()+"<br/>"
									+"<b>Address:        </b>"+address+"<br/>"
									+"<b>Price:          </b>"+house.getPriceRange()));
							
						}
				      });
			    	  map.addOverlay(marker);
			      }
			    });
	    }
	    
	    map.setSize("50%", "100%");
	    // Add some controls for the zoom level
	    map.addControl(new MapTypeControl());
	    map.addControl(new LargeMapControl3D());

	    DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
	    dock.addNorth(map, 500);
	    
	    fPanel.add(dock);
		
	}


	@Override
	public void setMarkers(final Queue<House> houseList) {
		Maps.loadMapsApi("", "2", false, new Runnable() {
		      public void run() {
		  	    buildUI(houseList);
		      }
		    });
		
	}

	@Override
	public HasClickHandlers getFilterButton() {
		return filter;
	}

}
