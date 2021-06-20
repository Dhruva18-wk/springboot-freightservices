package com.freightservices.autotests.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.json.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freightservices.autotests.inventorymgmt.InventoryManagement;



public class CommonUtil {

	private final static Logger LOGGER = Logger.getLogger(InventoryManagement.class.getName());

	CommonUtil(){
		LOGGER.setLevel(Level.SEVERE);
	}

	/**
	 * Utility Method to get todays date in format
	 * yyyy-MM-dd 
	 * @return
	 */
	public static String getTodaysDate() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		String finalDate = formatDate.format(cal.getTime());
		return finalDate;		
	}

	/**
	 * Utility method to get incremented date
	 * @param numberOfDaysToIncrement
	 * @return String, returns formatted incremented date
	 */
	public static String getIncrementedDate(int numberOfDaysToIncrement) {
		if ( numberOfDaysToIncrement==0 )
			return getTodaysDate();
		Date today = new Date();               
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");            
		Calendar c = Calendar.getInstance();        
		c.add(Calendar.DATE, numberOfDaysToIncrement);  // number of days to add      
		String nextDate = (String)(formattedDate.format(c.getTime()));
		LOGGER.config("===current date=="+nextDate);
		return nextDate;
	}
	/**
	 * Utility method to convert json to map
	 * @param filePath, file path as input
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> convertJSONToMap( String filePath ) {
		Map<String,String> inputOrderMap  = new HashMap<>();
		System.out.println("===========this is the filetpath+=="+filePath);
		//JsonElement jsonElement = null;
		//JSONParser parser = new JSONParser();

		try{
			   InputStream getLocalJsonFile = new FileInputStream(filePath);
			   inputOrderMap = new ObjectMapper().readValue(getLocalJsonFile, HashMap.class);

		}catch(FileNotFoundException e){
			LOGGER.severe("==FILE NOT FOUND==");
			return null;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//Gson g = new Gson();
		//Type mapType = new TypeToken<Map<String,Object>>(){}.getType();
		//inputOrderMap = g.fromJson(jsonElement,mapType);
		LOGGER.config("===INPUTORDERMAP=="+inputOrderMap);
		return inputOrderMap;

		//Gson g = new Gson();
		//Type mapType = new TypeToken<Map<String,Object>>(){}.getType();
		//inputOrderMap = g.fromJson(jsonElement,mapType);

	}

	/**
	 * Utility method to read json
	 * @param filePath, file path as input
	 * @return
	 */
	//public  JsonElement readJson(String filePath) {
	//	JsonElement jsonElement = null;
		//JSONParser parser = new JSONParser();
//		try{
//			FileReader fileReader = new FileReader(filePath);
//			jsonElement = (JsonElement) parser.parse(fileReader);
//		}catch(FileNotFoundException e){
//			LOGGER.severe("==FILE NOT FOUND==");
//		}catch(Exception e){
//			LOGGER.severe("==FILE NOT FOUND==");
//		}
		//return jsonElement;
	//}

}
