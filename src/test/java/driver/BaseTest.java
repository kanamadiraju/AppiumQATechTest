/**
* 
*/
package driver;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public static WebDriverWait wait;

	/**
	 * Starting the Appium Server through Code using AppiumServiceBuilder
	 * @throws IOException 
	 * 
	 */
	public static void startAppiumServer() throws IOException {
		
		Runtime runtime = Runtime.getRuntime();
	    try {
	        runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
	        Thread.sleep(5000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
        
	}
	/**
	 * Stop's the Appium Server and Close the Connection
	 */
	public static void stopAppiumServer() {
       // server.stop();
		 Runtime runtime = Runtime.getRuntime();
		    try {
		        runtime.exec("taskkill /F /IM node.exe");
		        runtime.exec("taskkill /F /IM cmd.exe");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
    }
	/**
	 * 
	 * @return : wait
	 */
	public WebDriverWait getWebDriverWait() {
		return wait;
	}
}
