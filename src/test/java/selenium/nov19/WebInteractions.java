package selenium.nov19;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.mongodb.MapReduceCommand.OutputType;

public class WebInteractions extends WebDriverTest{

	@Test
	public void webInteractions() throws IOException, InterruptedException {

		//Open the application
		openBrowser("http://the-internet.herokuapp.com/upload");


		/*
		 * 1. Dropdown
		 */

		WebElement dropdownEle = driver.findElementById("dropdown");
		Select dropdown = new Select(dropdownEle);

		List<WebElement> options = dropdown.getOptions();

		for (WebElement option : options) {
			System.out.println(option.getText());

		}

		//Select option 1
		dropdown.selectByVisibleText("Option 1");


		/*
		 * 2. Checkbox
		 */

		boolean selected = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected();
		System.out.println(selected);

		//Check if checkbox 2 is selected

		/*
		 * 3. Windows
		 */


		driver.findElement(By.linkText("Click Here")).click();
		driver.findElement(By.linkText("Elemental Selenium")).click();

		//Get the window handles

		Set<String> windowHandles = driver.getWindowHandles();

		for (String string : windowHandles) {
			driver.switchTo().window(string);

			//Get title
			String title = driver.getTitle();

			if(title.equals("Elemental Selenium: Receive a Free, Weekly Tip on Using Selenium like a Pro")) {
				driver.switchTo().window(string);
				System.out.println("Switched to the window");
				String h2Title = driver.findElement(By.tagName("h2")).getText();
				System.out.println(h2Title);
				break;
			}

		}

		/*
		 * Alerts
		 */
		driver.findElementByXPath("//button[text()='Click for JS Prompt']").click();
		driver.switchTo().alert().sendKeys("JS Alert");
		driver.switchTo().alert().accept();

		/*
		 * Frames
		 * 
		 */
		driver.switchTo().frame("mce_0_ifr");
		driver.findElementByXPath("//body[@id='tinymce']").clear();
		driver.findElementByXPath("//body[@id='tinymce']").sendKeys("iframes");
		/*
		 * Screenshot
		 */
		//

		File screenshot = driver.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("./output/screenshot1.png"));

		/*
		 * Upload
		 */
		driver.findElementById("file-upload").click();
		Thread.sleep(500);
		driver.findElementById("file-upload").sendKeys("C:\\Users\\Local User\\eclipse-workspace\\selenium.nov19\\output\\screenshot1.png");
		driver.findElementById("file-submit").click();
	}


}
