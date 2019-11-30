package selenium.nov19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverTest {
	public RemoteWebDriver driver;


	public void openBrowser(String URL) {

		//
		//System.setProperty("webdriver.chrome.driver", "C:/Users/Local User/Desktop/data/Drivers/chromedriver.exe");		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Search a product
		//driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Airpod");

		//driver.findElement(By.xpath("//input[@type='submit']")).click();



	}

	/*
	 * Re-usable function for moving to a child window
	 */

	public void switchToChildWindow(String parentWindow) {
		Set<String> winHandles = driver.getWindowHandles();
		for (String winHandle : winHandles) {
			if(!winHandle.equals(parentWindow)) {
				driver.switchTo().window(winHandle);
				System.out.println("Window switched");
			}
			//count=count+1;
		}


	}
	
	/*
	 * Re-usable method for handling dropdown
	 */
	
	public void selectByVisibleTextOption(WebElement dropdownEle, String optionToSelect) {
		
		Select dropdown = new Select(dropdownEle);
		dropdown.selectByVisibleText(optionToSelect);
		
		
	}
	
	/*
	 * Re-usable method to write to a property file
	 */
	
	public void writeToPropertyFile(String valToStore) {
		
		Properties prop = new Properties();
		prop.setProperty("InciNum", valToStore);
		
		try {
			prop.store(new FileOutputStream("./output/incidentNum.properties"), "The Value is stored");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	/*
	 * Reading a property file
	 */
	
	public String readPropertyFile(String key) {
		Properties prop = new Properties();
		String incNum = null;
		try {
			prop.load(new FileInputStream("./output/incidentNum.properties"));
			incNum = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return incNum;
		
	}
	
	//


}
