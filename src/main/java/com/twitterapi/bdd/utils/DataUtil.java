package com.twitterapi.bdd.utils;

import java.util.Hashtable;

public class DataUtil {
		
	public static Hashtable<String,String> getData(Xls_Reader xls, String testCaseName){
		String sheet = TwitterAPIConstants.TESTDATA_SHEET;
		int testStartRowNum = 1;
		while(!xls.getCellData(sheet, 0, testStartRowNum).equals(testCaseName)){
			testStartRowNum++;
		}
		int colStartRowNum = testStartRowNum+1;
		int dataStartRowNum = testStartRowNum+2;
		
		//Calculate rows of data
		int rows =1;
		while(!xls.getCellData(sheet, 0, dataStartRowNum+rows).equals("")){
			
			rows++;
			
		}
		int cols =0;
		while(!xls.getCellData(sheet, cols, colStartRowNum).equals("")){
			
			cols++;
	}
		
		//Read the data
		//Hashtable<String,String> data = new Hashtable<String,String>();
		
		//int iData = 0;
		Hashtable<String,String> table = null;
		
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key =xls.getCellData(sheet, cNum, colStartRowNum);
				String value =xls.getCellData(sheet, cNum, rNum);
				//data[iData][cNum]= (xls.getCellData(sheet, cNum, rNum));
				table.put(key, value);
				
			}
			//data[iData][0]=table;
			//iData++;
		}
		return table;
	
	}
	//True - for runmode Y
	//False -for runmode N
	public static boolean isSkip(Xls_Reader xls, String testName){
		
		int rows = xls.getRowCount("TestCases");
		for(int rNum=2;rNum<=rows;rNum++){
			String tcid = xls.getCellData("TestCases", "TCID", rNum);
			if(tcid.equals(testName)){
				String runmode= xls.getCellData("TestCases", "RunMode", rNum);
				if(runmode.equals("Y")){
					return true;
				}
				else return false;
			}
					
		}return false; // Default
		
		
	}

	}

