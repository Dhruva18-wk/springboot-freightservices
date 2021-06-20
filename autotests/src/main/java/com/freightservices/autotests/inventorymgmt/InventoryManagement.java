/**
 * 
 */
package com.freightservices.autotests.inventorymgmt;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import com.freightservices.autotests.util.CommonUtil;




/**
 * Class for managing inventory of orders
 * 
 * @author Dhruv
 *
 */
@Component
public class InventoryManagement extends FreightServicesInventory {

	public final static Logger LOGGER = Logger.getLogger(InventoryManagement.class.getName());
	protected  Map<String,String> inputOrderMap  = new HashMap<>();
	public static String orderFilePath;


	public InventoryManagement() {

		cityCode = CommonUtil.convertJSONToMap("CityCode.json");	
		cityCode.keySet()
		.stream()
		.forEach( k -> orderMap.put(k.toString(), 0));
		LOGGER.setLevel(Level.INFO);

	}

	/**
	 * Method to update inventory based on orders
	 */
	@Override
	public void updateInventory() {
		//Read file json and conver to map

		//destination/numberofORders
		try {
			getOrderMap();
			inputOrderMap.entrySet()
			.stream()
			.map(entry -> entry + "=" +entry.getKey())
			.forEach(entry ->{
				String destN = entry;
				LOGGER.config("destn**"+destN);
				String orderNumber = destN.split("=")[0];

				if(destN.contains("=")) {
					destN = destN.split("=")[2];
					LOGGER.config("destn**"+destN);
					destN = destN.split("}")[0];
					LOGGER.config("destn**"+destN);

				}
				if ( cityCode.get(destN) != null) {
					if( inventoryOrders.get(destN) != null)
						inventoryOrders.put(destN, inventoryOrders.get(destN)+1);

					else
						inventoryOrders.put(destN,1);

					DestOrders.put(orderNumber,destN);
					LOGGER.config("desDestOrderstn**"+inventoryOrders.get(destN));
					LOGGER.config("inventoryOrders**"+inventoryOrders);



				}

			});

			LOGGER.config("===inventoryOrders=="+inventoryOrders);
		}
		catch(Exception e) {
			System.out.println("===There was error while udpating inventory please check..."+ExceptionUtils.getStackTrace(e));
		}


	}

	/**
	 * Method to get order from the user
	 */
	@Override
	public  void getOrderMap() {
		//Read Json
		// COnver to MAP
		inputOrderMap = CommonUtil.convertJSONToMap( orderFilePath );
	}
}
