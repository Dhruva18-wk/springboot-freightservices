package com.freightservices.autotests.schedulemgmt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Interface to scheduling flight
 * @author Dhruv
 *
 */
public abstract class FlightsToSchedule {
	
	Map<String,Integer> inventoryOrders  = new HashMap<>();
	 Map<String,String> DestOrders   = new LinkedHashMap<>();
	 Map<String,Integer> orderMap  = new HashMap<>();
	Map<String,String> cityCode  = new HashMap<>();
	
	abstract void getInventoryToPrepareFlightSchedule();
	abstract void prepareFlightSchedule(String orderKey, String date);
}
