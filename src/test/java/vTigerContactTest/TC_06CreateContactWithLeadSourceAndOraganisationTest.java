package vTigerContactTest;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_06CreateContactWithLeadSourceAndOraganisationTest {

	@Test
	public void createContactWithLeadSourceAndOraganisationTest() throws Throwable {

//		Step 1: Create all the Objects
		PropertyFileUtility pUtil = new PropertyFileUtility();
		JavaUtility jUtil = new JavaUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

//		Property File - Common Data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");

		/* Excel File - Test Data */
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 1, 2) + jUtil.getRandomNumber();
		String ORGNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 3);
		String LEADSOURCE = eUtil.readDataFromExcelSheet("Contacts", 7, 3);

		WebDriver driver = null;

		// Step 2: Launch Browser - run time polymorphism - driver
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("invalid browser name");
		}

		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver, 20);
		driver.get(URL);

		// Step 3: Login To Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//a/img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		Thread.sleep(5000);
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			driver.switchTo().window(windowId);
		}
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Amdocs']")).click();
//			Thread.sleep(3000);
//			driver.switchTo().defaultContent();
		Thread.sleep(5000);
//			it is not clicking on SAVE button NoSuchWindowException
//		System.out.println(driver.getWindowHandles());
		Set<String> allWindowIds1 = driver.getWindowHandles();
		for (String windowId1 : allWindowIds1) {
			driver.switchTo().window(windowId1);
		}
		Select select = new Select(driver.findElement(By.name("leadsource")));
		select.selectByValue(LEADSOURCE);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]' and @class='crmButton small save']"))
				.sendKeys(Keys.ENTER);
//		logout
		Thread.sleep(3000);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		//driver.manage().window().minimize();
		driver.quit();
	}
}
