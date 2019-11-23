package selenium.nov19;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class JBHIFITest extends WebDriverTest {
	
	@Test
	public void getPrice() {
		openBrowser("<Your URL>");
		
		//Search Airpod
		driver.findElementByXPath("//input[@placeholder='Search products']").sendKeys("Airpod", Keys.ENTER);
		List<WebElement> listOfProducts = driver.findElementsByXPath("//div[@class='ais-hit--details product-tile__details']/h4");
		
		//Iterate through all products and print
		
		int count = 1;
		for (WebElement webElement : listOfProducts) {
			String productTitle = webElement.getText();
			System.out.println(count+"."+productTitle);
			count = count+1;
			
			if(productTitle.contains("Apple AirPods Pro")) {
				webElement.click();
				break;
			}
		}
		
		/*
		 * Calculate 5% of the overall price
		 * 
		 */
		
		String priceInDollar = driver.findElementByXPath("//span[@class='price']").getText();
		System.out.println("The price in dollars is "+priceInDollar);
		
		
		//Method - 1: Replace
		String priceReplace = priceInDollar.replace("$", "");
		System.out.println("Replace outcome is"+priceReplace);
		
		//Method 2 - Substring
		String priceSubstring = priceInDollar.substring(1);
		System.out.println("Substring outcome is "+priceSubstring);
		
		//Method - 3: Split
		String[] priceSplit = priceInDollar.split("\\$");
		System.out.println("Split outcome "+priceSplit[1]);
		
		
		//Convert string into int
		int princeInInt = Integer.parseInt(priceReplace);
		int percentage = (int)(princeInInt*0.05);
		System.out.println("The 5% discount is "+percentage);
		
		double princeInDouble = Double.parseDouble(priceReplace);
		double princeVal = princeInDouble*0.05;
		System.out.println(princeVal);
		
		
		
		
		
		
		
	}

}
