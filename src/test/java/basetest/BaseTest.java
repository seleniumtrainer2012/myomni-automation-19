package basetest;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {	
	public FileInputStream fis=null;
	public Properties prop=null;
	public WebDriver driver=null;
	public ExtentReports report=null;
	public ExtentTest test=null;

	/*
	 * Author-
	 * Name-
	 * Description-
	 * Arguments-
	 * return type-
	 * change log-
	 */

	public void loadConfig() {
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
			prop=new Properties();
			prop.load(fis);

		}catch(Exception e) {
			e.printStackTrace();
		}		

	}

	public void launchBrowser() {		
		if(prop.getProperty("Browser").equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
			driver=new ChromeDriver();
		}else if(prop.getProperty("Browser").equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")+"//src//test//resources//drivers//IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
	}

	public void navigateTo() {
		driver.get(prop.getProperty("URL"));
	}

	public WebElement findElement(String locatorKey) {

		if(locatorKey.endsWith("_XPATH")) {
			return driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_ID")) {
			return driver.findElement(By.id(prop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_NAME")) {
			return driver.findElement(By.name(prop.getProperty(locatorKey)));
		}else {
			System.out.println("Invalid locator specified!");
			test.log(LogStatus.FAIL, "Could not find element with lcoatorKey-"+locatorKey);
			takeScreenshot();
			return null;
		}
	}

	public void click(String locatorKey) {
		findElement(locatorKey).click();
	}

	public void setText(String locatorKey,String inputValue) {
		findElement(locatorKey).sendKeys(inputValue);;
	}

	public String getText(String locatorKey) {
		return findElement(locatorKey).getText();
	}



	public void takeScreenshot() {
		try {
			Date d=new Date();
			String timeStamp=d.toString().replace(" ", "_").replace(":", "_");
			String screenshotPath=System.getProperty("user.dir")+"//src//test//resources//screenshots//s_"+timeStamp+".PNG";

			TakesScreenshot snapshot=(TakesScreenshot)driver;
			File f=snapshot.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(f, new File(screenshotPath));
			
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}





}
