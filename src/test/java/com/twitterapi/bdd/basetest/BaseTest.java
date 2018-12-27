package com.twitterapi.bdd.basetest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.twitterapi.bdd.utils.ExtentManager;
import com.twitterapi.bdd.utils.TwitterAPIConstants;
import com.twitterapi.bdd.utils.Xls_Reader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {

	public WebDriver driver;
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	public Xls_Reader xls = new Xls_Reader(TwitterAPIConstants.DATA_XLS_PATH);
	public String sheet = TwitterAPIConstants.TESTDATA_SHEET;
	String postTweet;
	String postTweetId;
	String deletedTweetTruncated;
	String tweetText;
	Response response;
	JsonPath js;


	/**
	 * Capturing screenshot
	 */
	public void takeScreenshot(){
		Date d = new Date();
		String screenshotFile=d.toString().replace(":","_").replace(" ", "_")+".png";	
		new File(TwitterAPIConstants.REPORTS_PATH+ExtentManager.reportFolder+"//screenshot").mkdirs();
		String filePath = TwitterAPIConstants.REPORTS_PATH+ExtentManager.reportFolder+"//screenshot//"+screenshotFile;
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(srcFile, new File(filePath));
			Reporter.addScreenCaptureFromPath(filePath);
		}catch(IOException e){

			e.printStackTrace();

		}
		
	}

	/**
	 * Reporting test case failure
	 * @param failureMessage
	 */
	public void reportFailure(String failureMessage){
		takeScreenshot();
		Assert.fail("Test failed");
	}

	public String getLatestTweet(String[] authParameters, String tweetCount, String getTweetResource) {
		
		try {
			Response response = 
					given().
							auth().oauth(authParameters[0], authParameters[1], authParameters[2], authParameters[3]).
							queryParam("count",tweetCount).
							
					when().
							get(getTweetResource).
					then().
							//spec(rspec).
					extract().
							response();
			
			String responseString = response.asString();
			JsonPath js = new JsonPath(responseString);
			tweetText = js.get("text").toString();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Failed to get the tweet - "+e.toString());
		}
		return tweetText;
		
	}

	/**
	 * Posts a tweet
	 * @param authParameters
	 * @param tweetMessage
	 * @param postResource
	 * @param rspec
	 */
	public String postTweet(String[] authParameters, String tweetMessage, String postResource, ResponseSpecification rspec) {

		try {
			response = 
					given().
						auth().oauth(authParameters[0], authParameters[1], authParameters[2], authParameters[3]).
						queryParam("status", tweetMessage).

					when().
						post(postResource).
					then().
						//spec(rspec).
					extract().
						response();

			String response1 = response.asString();
			js = new JsonPath(response1);
			postTweetId = js.get("id").toString();
			Reporter.addStepLog(js.get("text").toString());
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Error encountered while posting a tweet -> " +e.toString());
		}
		
		return postTweetId;

	}
	
	/**
	 * deletes a tweet
	 * @param authParameters
	 * @param deleteResource
	 * @param rspec
	 * @return
	 */
	public String deleteTweet(String[] authParameters, String deleteResource, ResponseSpecification rspec) {
		
		try {
			response = 
					given().
							auth().oauth(authParameters[0], authParameters[1], authParameters[2], authParameters[3]).
													
					when().
							post(deleteResource).
					then().
							//spec(rspec).
					extract().
							response();
			Reporter.addStepLog(deleteResource);
			String response2 = response.asString();
			js = new JsonPath(response2);
		//	Reporter.addStepLog(js.get("text").toString());
			deletedTweetTruncated = js.get("truncated").toString();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Error encountered while deleting a tweet -> " +e.toString());
		}
		return deletedTweetTruncated;
	}
}

