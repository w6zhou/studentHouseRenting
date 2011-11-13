package com.uwaterloo.StudentHouseRenting.client.presenter;

import java.util.Queue;

import com.google.gwt.user.client.ui.HasWidgets;
import com.uwaterloo.StudentHouseRenting.shared.House;

public abstract interface Presenter {
	  public abstract void go(final HasWidgets container, Queue<House> houseList);
	}
