package com.freightservices.autotests.readinput;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.freightservices.autotests.inventorymgmt.InventoryManagement;
import com.freightservices.autotests.schedulemgmt.FlightsSchedule;

@Component
public class ReadUserInput {
	
	public void  getUserInput() {
		Scanner readConsole = new Scanner(System.in);
		System.out.println("===Input Order File Name==");

		String orderFilePath = readConsole.nextLine();
		System.out.println("orderfletpah====");

		InventoryManagement.orderFilePath = orderFilePath;

		System.out.println("===Input Number of Flights to Schedule per day***==");
		FlightsSchedule.numberOfFlightsToSchedule = Integer.valueOf(readConsole.nextLine());
		System.out.println("===Number of Orders Per Flight==");
		FlightsSchedule.numberOfOrdersPerFlight = Integer.valueOf(readConsole.nextLine());
		readConsole.close();
	}
}
