package com.uwaterloo.StudentHouseRenting.client.view;

import java.util.LinkedList;
import java.util.Queue;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.uwaterloo.StudentHouseRenting.client.presenter.ListPresenter;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class ListView  extends Composite implements ListPresenter.Display {

	private Button filter;
	private final FlexTable housesTable;
	
	public ListView(){
	    DecoratorPanel contentTableDecorator = new DecoratorPanel();
	    initWidget(contentTableDecorator);
	    contentTableDecorator.setWidth("100%");
	    contentTableDecorator.setWidth("36em");

	    housesTable = new FlexTable();
	    housesTable.setWidth("100%");
	    FlexCellFormatter cellFormatter = housesTable.getFlexCellFormatter();
	    cellFormatter.setColSpan(0, 0, 3);
	    cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    filter=new Button("Back to Filter");
	    housesTable.setWidget(0, 0, filter);
	    contentTableDecorator.add(housesTable);
	}

	public HasClickHandlers getFilterButton() {
		return filter;
	}

	//get houses
	  public void setData(Queue<House> data) {
		    //contactsTable.removeAllRows();
		  
		    
		    /*for (int i = 0; i < data.size(); ++i) {
		      contactsTable.setWidget(i, 0, new CheckBox());
		      contactsTable.setText(i, 1, data.get(i));
		    }*/
		  
		  //VerticalPanel vPanelViewer = new VerticalPanel();
		  //initWidget(vPanelViewer);
		  
		  Queue<House> houses = new LinkedList<House>(); 
		  houses = data;
		  //FlowPanel fPanel = new FlowPanel();
		  int i = 1;
		  while(!houses.isEmpty()){
				 //que.poll().print();
				housesTable.setText(i, 0, String.valueOf(i));
				/*House tmpHouse = houses.poll();
				housesTable.setWidget(i-1, 1, new Image(tmpHouse.getImg()));*/
				 //que.poll().print();
				housesTable.setText(i, 0, String.valueOf(i));
				House tmpHouse = houses.poll();
				String strImg = tmpHouse.getImg();
				if (strImg.equals("https://listings.och.uwaterloo.ca/NO PIC")) 
					strImg = "https://listings.och.uwaterloo.ca/Pictures/nopic.jpg";
				housesTable.setWidget(i, 1, new Image(strImg));
				Anchor anchorAddress = new Anchor();
				anchorAddress.setText(tmpHouse.getAddress());
				anchorAddress.setHref(tmpHouse.getPostUrl());
				housesTable.setWidget(i, 2, anchorAddress);
				i++;
		  }
		  //vPanelViewer.add(housesTable);
		  //vPanel.add(housesTable);
		  
	}
}
