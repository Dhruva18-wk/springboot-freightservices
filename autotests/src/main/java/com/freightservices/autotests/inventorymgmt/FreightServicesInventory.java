package com.freightservices.autotests.inventorymgmt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Interface for freight services inventory
 * @author Dhruv
 *
 */
public abstract class FreightServicesInventory {
	 //single functionality of inventory
	
	public Map<String,Integer> inventoryOrders  = new HashMap<>();
	public Map<String,String> DestOrders   = new LinkedHashMap<>();
	public Map<String,Integer> orderMap  = new HashMap<>();
	public Map<String,String> cityCode  = new HashMap<>();
	abstract void getOrderMap();
	abstract void updateInventory();
}
