package com.freightservices.autotests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.freightservices.autotests.schedulemgmt.FlightScehedulePrint;

/**
 * Hello world!
 *
 */


@SpringBootTest
public class FreightServicesTest {

	@Autowired
	private FlightScehedulePrint flightScehedulePrint;
	
	//This test will only look into business requirements
    @Test
	public void freightServicesTest()
	{
    	this.flightScehedulePrint.getReadUserInput().getUserInput();
    	this.flightScehedulePrint.getInventoryManagement().updateInventory();
    	this.flightScehedulePrint.getFlightsSchedule().getInventoryToPrepareFlightSchedule();
    	this.flightScehedulePrint.displayFlightSchedule();
	}



}
