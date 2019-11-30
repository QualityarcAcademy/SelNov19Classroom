package servicenow;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.nov19.WebDriverTest;
import support.ReadExcel;



public class CreateIncident extends WebDriverTest{
	Select dropdownEle;

	@Test(dataProvider = "testData")
	public void createNewIncident(String username, String password, String Keyword) throws InterruptedException {
		/*
		 * This test case creates a new incident
		 * @ Data - User credential 
		 */	
		//String[][] testData = ReadExcel.readExcelData("testData");

		//Launch application
		openBrowser("https://dev92430.service-now.com/");

		//Switch to frame
		driver.switchTo().frame("gsft_main");

		//Login
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();

		//Verify login is successful
		boolean isLoginSucceeded = driver.findElement(By.xpath("//span[text()='System Administrator']")).isDisplayed();
		if(isLoginSucceeded) {
			System.out.println("****Login Sucessful*****");
		}else {
			System.out.println("****Login failed******");
		}

		//Search the keyword 'incident'
		driver.findElement(By.id("filter")).click();
		driver.findElement(By.id("filter")).sendKeys(Keyword);
		driver.findElement(By.linkText("Create New")).click();

		//Switch to frame
		driver.switchTo().frame("gsft_main");

		//Store the parent window
		String parentWindow = driver.getWindowHandle();
		System.out.println("Parent window handle "+parentWindow);

		//Fill in the form 
		String incidentNumReInitial = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("The incident number is "+incidentNumReInitial);
		writeToPropertyFile(incidentNumReInitial);
		Thread.sleep(2000);
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Thread.sleep(2000);

		//Switch to child window
		//Set<String> winHandles = driver.getWindowHandles();
		//System.out.println("Total window opened "+winHandles.size());

//		for (String string : winHandles) {
//			System.out.println(string);
//
//		}
		//Switch to Child window

		//int count =0;
//		for (String winHandle : winHandles) {
//			if(!winHandle.equals(parentWindow)) {
//				driver.switchTo().window(winHandle);
//				System.out.println("Window switched");
//			}
//			//count=count+1;
//		}
		
		switchToChildWindow(parentWindow);

		driver.findElement(By.xpath("//tbody[@class='list2_body']/*[1]/td/a")).click();

		//Switch back to the parent window and frame
		driver.switchTo().window(parentWindow);
		driver.switchTo().frame("gsft_main");

		//Select category
		WebElement categoryDropdown = driver.findElement(By.id("incident.category"));
		selectByVisibleTextOption(categoryDropdown, "Software");
//		dropdownEle= new Select(categoryDropdown);
//		dropdownEle.selectByVisibleText("Software");

		//Select sub category 
		WebElement subcategoryDropdown = driver.findElement(By.id("incident.subcategory"));
		dropdownEle= new Select(subcategoryDropdown);
		dropdownEle.selectByVisibleText("Email");
		

		//Click on lookup Business icon
		driver.findElement(By.id("lookup.incident.business_service")).click();

		//Switch to the child window and search for 'Outlook'
//		Set<String> winHandles1 = driver.getWindowHandles();
//		System.out.println("Total window opened "+winHandles1.size());
//
//		for (String string : winHandles1) {
//			System.out.println(string);
//
//		}
//		//Switch to Child window
//
//		//int count =0;
//		for (String winHandle : winHandles1) {
//			if(!winHandle.equals(parentWindow)) {
//				driver.switchTo().window(winHandle);
//				System.out.println("Window switched");
//			}
//			//count=count+1;
//		}
		
		switchToChildWindow(parentWindow);
	
		driver.findElement(By.xpath("//input[@placeholder='Search' and @class='form-control']"))
		.sendKeys("Outlook", Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Outlook Web Access (OWA)']")).click();

		//Switch back to the parent window and frame
		driver.switchTo().window(parentWindow);
		driver.switchTo().frame("gsft_main");

		//Select contact type
		WebElement contactType = driver.findElement(By.id("incident.contact_type"));
		dropdownEle= new Select(contactType);
		dropdownEle.selectByVisibleText("Email");

		//Select impact
		WebElement impact = driver.findElement(By.id("incident.impact"));
		dropdownEle= new Select(impact);
		dropdownEle.selectByVisibleText("1 - High");


		//Select urgency
		WebElement urgency = driver.findElement(By.id("incident.urgency"));
		dropdownEle= new Select(urgency);
		dropdownEle.selectByVisibleText("1 - High");

		//Verify the priority field is disabled
		boolean isPriorityEnabled = driver.findElement(By.id("incident.priority")).isEnabled();
		Assert.assertTrue(isPriorityEnabled, "The priority field is not disabled");

		//Enter description 
		driver.findElement(By.id("incident.description"))
		.sendKeys("Email could not be configured for my machine");

		//Click on submit 
		driver.findElement(By.id("sysverb_insert_bottom")).click();

		//Verify alert message
		String alertMessage = driver.findElement(By.xpath("(//div[@role='alert']/span)[2]")).getText();
		Assert.assertTrue(alertMessage.equals("The following mandatory fields are not filled in: Short description"), "Alert message is not verified");

		//Re-submit 
		driver.findElement(By.id("incident.short_description")).sendKeys("Email issue");
		driver.findElement(By.id("sysverb_insert_bottom")).click();

		//Store the incident number to property file
		//writeToPropertyFile(incidentNumReInitial);

		//Close the driver
		driver.close();

	}

}
