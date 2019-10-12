package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import basetest.BaseTest;
import util.ExtentManager;

public class Login extends BaseTest{

	@Test
	public void invalidlogin() {
		String TestCaseName="Invalid Login TestCase";
		report=ExtentManager.getInstance();
		test=report.startTest(TestCaseName);

		test.log(LogStatus.INFO, "Started execution of "+TestCaseName);

		test.log(LogStatus.INFO, "Loading cofig file");
		loadConfig();

		test.log(LogStatus.INFO, "Lanching browser -"+prop.getProperty("Browser"));
		launchBrowser();

		test.log(LogStatus.INFO, "Navigating to -"+prop.getProperty("URL"));
		navigateTo();

		/*
		 * flow
		 * 
		 */
		
		click("LoginBtn_XPATH");
		
		String actualMsg=getText("LoginEmptyMsg_XPATH");
		if(actualMsg.equals(prop.getProperty("EmptyUserNamePwdExpectedMessage"))) {
			test.log(LogStatus.PASS, "This field is required message displayed!");
			
		}else {
			test.log(LogStatus.FAIL, "This field is required message is NOT displayed!");
			Assert.fail();
		}	
		takeScreenshot();

	}


	//@Test
	public void validlogin() {
		String TestCaseName="Valid Login TestCase";
		report=ExtentManager.getInstance();
		test=report.startTest(TestCaseName);

		test.log(LogStatus.INFO, "Started execution of "+TestCaseName);
		test.log(LogStatus.INFO, "Loading cofig file");
		loadConfig();

		test.log(LogStatus.INFO, "Lanching browser -"+prop.getProperty("Browser"));
		launchBrowser();

		test.log(LogStatus.INFO, "Navigating to -"+prop.getProperty("URL"));
		navigateTo();





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
