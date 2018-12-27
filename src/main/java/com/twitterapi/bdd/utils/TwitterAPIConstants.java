package com.twitterapi.bdd.utils;

import java.util.Hashtable;

public class TwitterAPIConstants {

	//Environment
	public static final String ENV = "PROD"; //STAGE, QA, TEST 

	//URLs
	public static final String TWITTER_PROD_BASE_URL = "https://api.twitter.com/1.1/statuses";
	public static final String TWITTER_STAGE_BASE_URL = "http://216.10.245.166";
	public static final String TWITTER_QA_BASE_URL = "";
	public static final String TWITTER_TEST_BASE_URL = "";
	
	//Unique Parameters & Resources
	public static final String POST_TWEET_STATUS_CODE="200";
	public static final String GET_TWEET_STATUS_CODE = "200";
	public static final String GET_TWEET_RESOURCE = "/home_timeline.json";
	public static final String POST_TWEET_RESOURCE = "/update.json";
	public static final String DELETE_TWEET_RESOURCE1 = "/destroy/";
	public static final String DELETE_TWEET_RESOURCE2 = ".json";
	public static final String ADD_RESOURCE = "/maps/api/place/add/json";
	public static final String DELETE_RESOURCE = "/maps/api/place/delete/json";
	
	// Paths																							
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\data\\Data.xlsx";
	public static final String TESTDATA_SHEET = "Data";
	public static final String TESTCASES_SHEET = "TestCases";
	public static final String CHROME_DRIVER_EXE = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	public static final String GECKO_DRIVER_EXE = System.getProperty("user.dir")+"\\drivers\\geckodriver.exe";
	public static final String IE_DRIVER_EXE = System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe";
	public static final String REPORTS_PATH = "D:\\reports\\TwitterAPI_BDD\\";
	//XML Locations
	
	//JSON Locations
	public static final String DELETE_JSON_REQUEST = System.getProperty("user.dir")+"\\src\\main\\java\\com\\restassured\\pom\\jsonrequests\\DeleteJsonRequest.JSON";
	
	//Element XPATHs in XML Requests/Responses
	
	//Data
	public static final Object RUNMODE_COL = "RunMode";

	

	//Login Page
	
	//DashBoard Page
	
	//Customer Landing Page
	
	//Recurring Profiles Page
	
	//Manage Customer Page
		
	//FrontStore Login Page
	
	//FrontStore CustomerLanding Page
	
	//CheckOut Page
	
	//Ghost Orders Page
	
	//Order View Page


	//*************************************************************************************************************//


	public static Hashtable<String, String> table;
	public static Hashtable<String, String> tableMBO;
	public static Hashtable<String, String> getRASPEnvDetails(){
		if(table==null){
			table = new Hashtable<String, String>();
			if(ENV.equals("STAGE")){
				table.put("url", TWITTER_STAGE_BASE_URL);
			}
			else if(ENV.equals("QA")){
				table.put("url", TWITTER_QA_BASE_URL);
			}
			else if(ENV.equals("TEST")){
				table.put("url", TWITTER_TEST_BASE_URL);
			}
			else if(ENV.equals("PROD")){
				table.put("url", TWITTER_PROD_BASE_URL);
			}
			
		}
		return table;

	}

/*	public static Hashtable<String, String> getMBOEnvDetails(){
		if(tableMBO==null){
			tableMBO = new Hashtable<String, String>();
			if(ENV.equals("TEST")){
				tableMBO.put("url", MBO_TEST_BASE_URL);

			}else if(ENV.equals("QA")){
				tableMBO.put("url", MBO_QA_BASE_URL);

			}else if(ENV.equals("STAGE")){
				tableMBO.put("url", MBO_STAGE_BASE_URL);
			}

		}
		return tableMBO;

	}*/
}
