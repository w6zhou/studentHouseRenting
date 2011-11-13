package com.uwaterloo.StudentHouseRenting.client.view;

import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.uwaterloo.StudentHouseRenting.client.presenter.FilterPresenter;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FilterView extends Composite implements FilterPresenter.Display {
	private ListBox lstBoxProvince;
	private ListBox lstBoxCity;
	private ListBox lstBoxSchool;
	private TextBox price;
	private Button mapButton;
	private Button listButton;
	private DateBox dateBox;
	
	@SuppressWarnings("deprecation")
	public FilterView() {
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidth("100%");
		decPanel.setWidth("36em");
	    initWidget(decPanel);
	    
		FlexTable panel = new FlexTable();
	    decPanel.setWidget(panel);
	    FlexCellFormatter cellFormatter = panel.getFlexCellFormatter();
	    panel.setHTML(0, 0, "Filter the renting house");
	    cellFormatter.setColSpan(0, 0, 2);
	    cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    Label province=new Label("Province:");
		panel.setWidget(1,0,province);
		
		lstBoxProvince = new ListBox();
		lstBoxProvince.setWidth("100%");
		lstBoxProvince.addItem("Select Province");
		lstBoxProvince.addItem("Ontario");
		panel.setWidget(1,1,lstBoxProvince);

	    Label city=new Label("City:");
		panel.setWidget(2,0,city);
		
		lstBoxCity = new ListBox();
		lstBoxCity.setWidth("100%");
		lstBoxCity.addItem("Select City");
		lstBoxCity.addItem("Waterloo");
		lstBoxCity.addItem("Kitchener");
		panel.setWidget(2,1,lstBoxCity);
		
	    Label school=new Label("University:");
		panel.setWidget(3,0,school);
		
		lstBoxSchool = new ListBox();
		lstBoxSchool.setWidth("100%");
		lstBoxSchool.addItem("Select Institution");
		lstBoxSchool.addItem("University of Waterloo");
		lstBoxSchool.addItem("Wilfrid Laurier University");
		panel.setWidget(3,1,lstBoxSchool);

	    Label maxPrice=new Label("MAX Price:");
		panel.setWidget(4,0,maxPrice);
		price=new TextBox();
		panel.setWidget(4,1,price);

	    Label startDate=new Label("Starting Date:");
		panel.setWidget(5,0,startDate);
		dateBox=new DateBox();
		dateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		dateBox.hideDatePicker();
		panel.setWidget(5, 1, dateBox);

	    Label showOn=new Label("Show Available Houses on:");
		panel.setWidget(6, 0, showOn);
		mapButton=new Button("Map");
		listButton=new Button("List");
		panel.setWidget(6, 1, mapButton);
		panel.setWidget(7, 1, listButton);
	}
	
	public Widget asWidget() {
		return this;
	}
	  
	public HasClickHandlers getMapButton() {
		return mapButton;
	}
	  
	public HasClickHandlers getListButton() {
		return listButton;
	}
	
	public HasValue<String> getPrice(){
		return price;
	}
	
	public HasValue<Date> getDate(){
		return dateBox;
	}
}