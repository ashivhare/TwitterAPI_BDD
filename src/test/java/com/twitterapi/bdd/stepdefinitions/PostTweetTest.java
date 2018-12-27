package com.twitterapi.bdd.stepdefinitions;

import java.util.Hashtable;

import org.testng.SkipException;

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


public class PostTweetTest extends BaseTest{
	String testCaseName;
	Hashtable<String, String> data;
	ResponseSpecBuilder builder;
	ResponseSpecification rspec;
	String testResult;
	static String tweetID;

	@Before
	public void requestSpec(Scenario scenario) {
		testCaseName = scenario.getName();
		data = DataUtil.getData(xls, testCaseName);

		if(!DataUtil.isSkip(xls, testCaseName) || data.get(TwitterAPIConstants.RUNMODE_COL).equals("N")){
			throw new SkipException("Skipping the test as runmode is NO");
		}
		
		RestAssured.baseURI = TwitterAPIConstants.TWITTER_PROD_BASE_URL;
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectContentType(ContentType.JSON);
		builder.expectStatusCode(Integer.parseInt(TwitterAPIConstants.POST_TWEET_STATUS_CODE));
		rspec = builder.build();
	}


	@When("^User submits a POST request for posting a tweet$")
	public void user_submits_a_POST_request_for_posting_a_tweet() {

		String[] authParameters = {data.get("consumerKey"), data.get("consumerSecret"), data.get("accessToken"), data.get("secretToken")}; 
		String tweetMessage = data.get("statusTweet");
		String postResource = TwitterAPIConstants.POST_TWEET_RESOURCE;
		tweetID = postTweet(authParameters, tweetMessage, postResource, rspec);   
	}

	
	@Then("^User should get a tweet id in response$")
	public void user_should_get_a_tweet_id_in_response() {
		if(tweetID != null && !tweetID.isEmpty()) {
			testResult="Success";
		}else {
			testResult = "Unsuccessful";
		}

	}

	@Then("^Test result should match with the expected result$")
	public void test_result_should_match_with_the_expected_result() {
		if(!testResult.equals(data.get("expectedResult"))) {
			reportFailure("Test has been failed");
		}
	}

}


