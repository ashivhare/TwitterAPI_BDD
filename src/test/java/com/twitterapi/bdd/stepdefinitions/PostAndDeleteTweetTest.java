package com.twitterapi.bdd.stepdefinitions;

import java.util.Hashtable;

import org.testng.SkipException;

import com.cucumber.listener.Reporter;
import com.twitterapi.bdd.basetest.BaseTest;
import com.twitterapi.bdd.utils.DataUtil;
import com.twitterapi.bdd.utils.TwitterAPIConstants;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class PostAndDeleteTweetTest extends BaseTest{
	
	String testCaseName;
	Hashtable<String, String> data;
	ResponseSpecBuilder builder;
	ResponseSpecification rspec;
	String testResult;
	
	@Before
	public void requestSpec(Scenario scenario) {
		testCaseName = scenario.getName();
		System.out.println(testCaseName);
		data = DataUtil.getData(xls, testCaseName);

		if(!DataUtil.isSkip(xls, testCaseName) || data.get(TwitterAPIConstants.RUNMODE_COL).equals("N")){
			throw new SkipException("Skipping the test as runmode is NO");
		}
		
		System.out.println("data set");
		RestAssured.baseURI = TwitterAPIConstants.TWITTER_PROD_BASE_URL;
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectContentType(ContentType.JSON);
		builder.expectStatusCode(Integer.parseInt(TwitterAPIConstants.POST_TWEET_STATUS_CODE));
		rspec = builder.build();
	}

	
	@When("^User deletes the same tweet$")
	public void user_deletes_the_same_tweet() {
		String[] authParameters = {data.get("consumerKey"), data.get("consumerSecret"), data.get("accessToken"), data.get("secretToken")};
		String deleteResource = TwitterAPIConstants.DELETE_TWEET_RESOURCE1+PostTweetTest.tweetID+TwitterAPIConstants.DELETE_TWEET_RESOURCE2;
		String tweetDeleted = deleteTweet(authParameters, deleteResource, rspec);

		if((tweetDeleted.equals(data.get("truncated")))){
			testResult="Success";
		}else {
			testResult = "Unsuccessful";
		}

	}

	@Then("^User should get a deleted tweet id in response$")
	public void user_should_get_a_deleted_tweet_id_in_response() {

		if(!testResult.equals(data.get("expectedResult"))) {
			Reporter.addStepLog("Test has been failed");
		}else {
			Reporter.addStepLog("Test has been passed");
		}

	}
}
