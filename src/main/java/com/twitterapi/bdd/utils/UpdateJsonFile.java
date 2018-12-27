package com.twitterapi.bdd.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdateJsonFile {
	public static boolean updateJsonFileValues(String keyTobeUpdated, String newValue, String jsonBody, String jsonFilePath) throws JSONException, JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		String key = keyTobeUpdated;

		JSONObject jo = new JSONObject(jsonBody);
		//Read from file
		JSONObject root = mapper.readValue(new File(jsonFilePath), JSONObject.class);

		String val_older = jo.getString(key);
		//String val_older = root.getString(key);

		//Compare values
		if(!newValue.equals(val_older))
		{
			//Update value in object
			root.put(key,newValue);

			//Write into the file
			try (FileWriter file = new FileWriter(jsonFilePath)) 
			{
				file.write(root.toString());
				System.out.println("Successfully updated json object to file...!!");
			}
		}
		return false;
	} 
}
