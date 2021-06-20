package com.freightservices.autotests.schedulemgmt;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import com.freightservices.autotests.inventorymgmt.FreightServicesInventory;
import com.freightservices.autotests.util.CommonUtil;

/**
 * Class schedules flight with given inventory data
 * @author Dhruv
 *
 */
@Component
public class FlightsSchedule extends FlightsToSchedule {

	private final static Logger LOGGER = Logger.getLogger(FlightsSchedule.class.getName());
	public static int numberOfFlightsToSchedule = 0;
	private static int numberOfFlights = 0;
	public static int numberOfOrdersPerFlight = 0;
	static int numberOFDateToIncrement = 0;
	static StringBuilder ordersToAppend = new StringBuilder();;
	int i =0;
	String orderNumber ="";

	static Map<String, String> flightScheduleMap = new LinkedHashMap<>();

//THIS CLASS DEPENDS ON FOLLOWING FIELDS
// InventoryManagement.cityCode
// InventoryManagement.inventoryOrders
//InventoryManagement.orderMap
//InventoryManagement.DestOrders
//JUST MAKE SURE YOU ARE NOT DEPENDENT ON CONCRETE CLASS
	
	public FlightsSchedule(FreightServicesInventory freightServicesInventory) {
		this.cityCode = freightServicesInventory.cityCode;
		this.DestOrders = freightServicesInventory.DestOrders;
		this.inventoryOrders = freightServicesInventory.inventoryOrders;
		this.orderMap =  freightServicesInventory.orderMap;
	}

	/**
	 * Method to get inventory to schedule flights
	 */
	public void getInventoryToPrepareFlightSchedule() {

		try {
			cityCode.keySet()
			.stream()
			.filter(e -> 
			inventoryOrders.get(e)!=null && inventoryOrders.get(e)>0)
			.forEach(k ->{
				prepareFlightSchedule( k, CommonUtil.getIncrementedDate( numberOFDateToIncrement ));
			});
		}
		catch(Exception e) {
			System.out.println("===There was error while getting inventory to prepare flight schedule please check..."+ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * Method to prepare flight schedule based on order key and date
	 */
	@Override
	public void prepareFlightSchedule(String orderKey, String date) {

		try {
			orderMap.keySet()
			.stream()
			.filter(k-> k.toString()==orderKey)
			.forEach(k->{
				numberOfFlights++;
				if ( inventoryOrders.get(k) >= numberOfOrdersPerFlight) {
					inventoryOrders.put(k, inventoryOrders.get(k)-numberOfOrdersPerFlight);
					String tmpOrderNumber = getOrderNumber(k,20);
					orderNumber = orderNumber +" "+tmpOrderNumber;
					tmpOrderNumber = tmpOrderNumber.replace("Orders:::", "");
					tmpOrderNumber = tmpOrderNumber.replace(":::Orders", "");
					String[] orderNumbers = tmpOrderNumber.split(",");
					LOGGER.config("====THESE orderNumbers**"+Arrays.toString(orderNumbers));

					for (String orderNumber: orderNumbers) {
						DestOrders.remove(orderNumber);
						FlightScehedulePrint.updatedDestOrders.put(orderNumber,k);

					}
					FlightsSchedule.flightScheduleMap.put(date+" "+tmpOrderNumber,k+"="+numberOfFlights);
					LOGGER.config("====flightScheduleMap::"+FlightsSchedule.flightScheduleMap);


				}
				else if ( Integer.valueOf(inventoryOrders.get(k)) > 0) {
					String tmpOrderNumber = getOrderNumber(k,inventoryOrders.get(k));
					orderNumber = orderNumber +" "+tmpOrderNumber;
					inventoryOrders.put(k, 0);
					tmpOrderNumber = tmpOrderNumber.replace("Orders:::", "");
					tmpOrderNumber = tmpOrderNumber.replace(":::Orders", "");
					String[] orderNumbers = tmpOrderNumber.split(",");

					for (String orderNumber: orderNumbers) {
						FlightScehedulePrint.updatedDestOrders.put(orderNumber,k);
						DestOrders.remove(orderNumber);

					}
					FlightsSchedule.flightScheduleMap.put(date+" "+tmpOrderNumber,k+"="+numberOfFlights);
					LOGGER.config("====flightScheduleMap::"+FlightsSchedule.flightScheduleMap);


				}
				if ( numberOfFlights == numberOfFlightsToSchedule) {
					numberOfFlights=0;
					ordersToAppend= new StringBuilder();
					numberOFDateToIncrement++;
					orderNumber="";
				}



			});
			if ( inventoryOrders.get(orderKey) > 0) {
				prepareFlightSchedule( orderKey,  CommonUtil.getIncrementedDate( numberOFDateToIncrement ));
			}
			LOGGER.info("====flightScheduleMap::"+FlightsSchedule.flightScheduleMap);

		}
		catch(Exception e) {
			System.out.println("===There was error while preparing flight schedule, please check..."+ExceptionUtils.getStackTrace(e));
		}

	}

	private String getOrderNumber(String key, int ordersToCount) {

		try {
			String orderNumber = DestOrders
					.entrySet()
					.stream()
					.filter(entry-> entry.getValue().equals(key))
					.limit(ordersToCount)
					.map(entry-> new String(entry.getKey()))
					.collect(Collectors.joining(",", "Orders:::", ":::Orders"));
			LOGGER.config("====THESE ARE ORDER NUMBERS**"+orderNumber);
			return orderNumber;	
		}
		catch(Exception e) {
			System.out.println("===There was error while getting order please check..."+ExceptionUtils.getStackTrace(e));
		}

		return null;

	}
}
