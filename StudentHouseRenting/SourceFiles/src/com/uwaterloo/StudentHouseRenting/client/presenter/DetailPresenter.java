package com.uwaterloo.StudentHouseRenting.client.presenter;

import java.util.Queue;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.uwaterloo.StudentHouseRenting.client.HouseServiceAsync;
import com.uwaterloo.StudentHouseRenting.client.view.DetailView;
import com.uwaterloo.StudentHouseRenting.shared.House;

public class DetailPresenter implements Presenter {

	public DetailPresenter(HouseServiceAsync rpcService,
			HandlerManager eventBus, DetailView detailView) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void go(HasWidgets container, Queue<House> houseList) {
		// TODO Auto-generated method stub
		
	}

}
