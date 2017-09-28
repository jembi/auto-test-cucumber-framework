# Auto-Test Framework

Selenium & Java based frontend automation suite utilising the BDD methodologies of Cucumber and Gherkin 

## 1. Install JAVA SDK 8
## 2. Install Maven
## 3. Install Git
## 4. Install Intellji
## 5. Download chromedriver 2.2 https://sites.google.com/a/chromium.org/chromedriver/downloads

### Set Path Variables

User Variable Path = directory for java sdk 8 
Set Maven home in environment Variables 

### For Windows
Create the following System variables
- JAVA_HOME = PAth to java sdk
- M2_HOME = Path to maven installation
- MAVEN_HOME = Path to maven installation

### For Windows Edit Path System variable 
- Add %M2_HOME%\bin 
- Add directory to your chromedriver.exe


### Software preparatation

	        
	  $ mkdir aut-tests
          git clone https://github.com/jembi/auto-test-cucumber-framework.git
          mvn clean test

# 

### Framework Overview
The cucumber BDD testing framework specifies acceptance tests as written from the view of the Product Owner. Using keywords such as Given, When, Then and And, acceptance criteria tests known as feature files can then be broken down into testable steps. 
Jembi’s Cucumber Selenium framework is preconfigured to connect to Testrail and write test results in the form of individual test runs by specifying the test cases that are to be run.
Each time the test is run it is written to Testrail at the end of the test cycle. All test results are written at once.

#### Cucumber Selenium - Overall testframework leveraging the Cucumber framework with Selenium written in JAVA.

#### Feature File - The feature file specifies the steps in BDD language style

#### Hooks class - Hooks class is most important class as it performs the following functions
Ensures feature files trigger test results posted to test rail
Ensure the correct chromedriver depending on environment specified through Maven commands

#### Step Definition Feature File - Java class whereby the steps from  the feature file are broken down to be coded into automation tests

#### Feature Model Class - Java class whereby the step definition calls on methods that require action from the automated user such as entering text, finding/asserting fields on the UI

#### Feature PageObject Class - Java class whereby the necessary HTML objects are captured as WebElements to be manipulated by the associated model class

#### ChromeDriver.exe - Local chromedriver necessary in order 

#### Testrail Integration Class - Java class that writes test run results to Jembi’s test rail server

#### Cucumber Reports - Cucumber has a built in report generation whereby Feature files tested are automatically written to cucumbers own reporting system 

```
Steps to write an acceptance test
Each Feature file has it’s step defition, pageobject & model class
              
   1-Create Feature file and define feature scenario test & Testrail ID’s
              
   2-Run project and copy method stubs from output
              
   3-Create Step definition class & paste method stubs
              
   4-Create Page Object Class and define objects required
              
   5-Create Model Class & Define actions require with page objects
              
   6-Instantiate model object in Step definition & execute tests required per step
```
