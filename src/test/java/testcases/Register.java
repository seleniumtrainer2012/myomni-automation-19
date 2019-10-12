package testcases;
import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import basetest.BaseTest;
import util.DataReader;
import util.ExtentManager;
public class Register extends BaseTest{	

	@Test
	public void NewUserRegistration() {
		String TestCaseName="New User Registration TestCase"; 

		report=ExtentManager.getInstance();
		test=report.startTest(TestCaseName);

		test.log(LogStatus.INFO, "Started execution of "+TestCaseName);

		test.log(LogStatus.INFO, "Loading cofig file");
		loadConfig();
		
		//get data from excel sheet
		HashMap<String,String> TCData=DataReader.getData(TestCaseName);

		test.log(LogStatus.INFO, "Lanching browser -"+prop.getProperty("Browser"));
		launchBrowser();

		test.log(LogStatus.INFO, "Navigating to -"+prop.getProperty("URL"));
		navigateTo();
		
		/*
		 * flow
		 */
		click("Register_XPATH");
		test.log(LogStatus.INFO, "Filling user registration form");
		
		setText("LoginName_NAME", TCData.get("LoginName"));
		setText("Email_NAME", TCData.get("Email"));
		setText("Password_NAME", TCData.get("Password"));		
		setText("ConfirmPassword_NAME", TCData.get("ConfirmPassword"));
		setText("FirstName_ID", TCData.get("FirstName"));
		
		takeScreenshot();
		
		
		
		

	}

	//@Test
	public void DuplicateUserRegistration() {
		String TestCaseName="Duplicate User Registration TestCase";	
		report=ExtentManager.getInstance();
		test=report.startTest(TestCaseName);

		test.log(LogStatus.INFO, "Started execution of "+TestCaseName);

		test.log(LogStatus.INFO, "Loading cofig file");
		loadConfig();

		test.log(LogStatus.INFO, "Lanching browser -"+prop.getProperty("Browser"));
		launchBrowser();

		test.log(LogStatus.INFO, "Navigating to -"+prop.getProperty("URL"));
		navigateTo();

		takeScreenshot();

	}



	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
		report.endTest(test);
		report.flush();

	}


}
