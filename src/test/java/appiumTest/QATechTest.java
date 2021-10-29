package appiumTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import driver.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class QATechTest extends BaseTest{

	public AndroidDriver<WebElement> driver;
	public static Properties prop;
	public static String projectPath = System.getProperty("user.dir");
	
	public QATechTest() throws IOException {
		prop = new Properties();		
		FileInputStream fs = new FileInputStream(projectPath+"//src//test/resources//projectconfig.properties");
		prop.load(fs);
	}
	
	
	@Test
	public void ValidateCounter() throws IOException
	{
		
		WebElement counterButton =driver.findElement(By.xpath(prop.getProperty("counter_btn")));
        Scanner scanner = new Scanner(System.in);
		
		//int numberFromConsole=Integer.valueOf(System.getProperty("num1"));
		
		System.out.print("Enter Number of times counter to be Increment: ");
		int numberFromConsole = scanner.nextInt();
		scanner.close();
		System.out.println("The number of times counter to be increment : "+numberFromConsole);
				
		for(int i=0;i<numberFromConsole;i++) {
			counterButton.click();			
		}
		
		WebElement Results = driver.findElement(By.xpath(prop.getProperty("counterValue_txt")));
		int actualValueOfCounter = Integer.parseInt(Results.getText());	
		int expectedValueOfCounter = numberFromConsole;
		Assert.assertEquals(expectedValueOfCounter, actualValueOfCounter);
        

	}		
	
	@BeforeTest
	public void setUp() throws InterruptedException, IOException{      
	
		BaseTest.startAppiumServer();
		
		final DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("automationName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("platformName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("platformVersion"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
		cap.setCapability("noReset", true);
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/APK/app-release.apk");

		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");		
		driver = new AndroidDriver<WebElement>(url, cap);		
		System.out.println("Application started--------------");
		

	    }
	 
	@AfterTest
	public void tearDown() {
	        System.out.println("test completed");
	        driver.quit();
	        BaseTest.stopAppiumServer();

	    }

}
