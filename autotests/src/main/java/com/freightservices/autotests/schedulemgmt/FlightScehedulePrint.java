package com.freightservices.autotests.schedulemgmt;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freightservices.autotests.inventorymgmt.InventoryManagement;
import com.freightservices.autotests.readinput.ReadUserInput;
/**
 * Class to print the flight schedule on console
 * @author Dhruv
 *
 */
@Component
public class FlightScehedulePrint {

    @Autowired
    public ReadUserInput readUserInput;
    
	@Autowired
	public InventoryManagement inventoryManagement;
	
	@Autowired
	public FlightsSchedule flightsSchedule;
	
	
	static protected Map<String,String> updatedDestOrders   = new LinkedHashMap<>();
	int repeatIteration = 0;	

	public InventoryManagement getInventoryManagement() {
		return inventoryManagement;
	}
	
	
	public ReadUserInput getReadUserInput() {
		return readUserInput;
	}

	
	public FlightsSchedule getFlightsSchedule() {
		return flightsSchedule;
	}

	/**
	 * Method displays flight schedule on console
	 */
	public void displayFlightSchedule() {

		System.out.println("============================*****");

		try {
			updatedDestOrders
			.keySet()
			.stream()
			.forEach(k->{ 
				FlightsSchedule.flightScheduleMap.entrySet()
				.stream()
				.filter(entry -> entry.getKey().contains(k))
				.forEach(kk -> {
					System.out.println("Order: "+k+", Flight"+kk.getValue()+", departure: YUL, arrival: "
							+updatedDestOrders.get(k)
							+", day:"+kk.getKey());
					System.out.println("============================*****");

				});
			});
		}
		catch(Exception e) {
			System.out.println("===There was error while displaying flight schedule please check..."+ExceptionUtils.getStackTrace(e));
		}

	}


}
