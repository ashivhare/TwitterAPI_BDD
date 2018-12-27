#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@PostAndDeleteTweet
Feature: Posting and deleting a tweet using API
  I want to post and delete a tweet from my twitter account using twitter API

  Scenario: postAndDeleteTweetTest
  	When User submits a POST request for posting a tweet
    Then User should get a tweet id in response
    When User deletes the same tweet
    Then User should get a deleted tweet id in response
    And Test result should match with the expected result

