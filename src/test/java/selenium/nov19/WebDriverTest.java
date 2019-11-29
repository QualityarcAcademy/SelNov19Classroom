package selenium.nov19;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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

}
