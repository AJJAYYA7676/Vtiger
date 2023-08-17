package vTigerContactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_07CreateContactWithOrganizationTest {
	@Test
	public void createContactWithOrganizationTest() throws IOException {

		// Step 1: Create the necessary objects of all utilities
		JavaUtility jUtil = new JavaUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		WebDriver driver = null;

		// Step 2: Read the required Data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");

		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 2);
		String ORGNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 3) + jUtil.getRandomNumber();

		// Step 3: Launch the browser
		if (BROWSER.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("invalid browser name");
		}

		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver, 15);
		driver.get(URL);

		// Step 4: Login to Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 5: Navigate to Organization
		driver.findElement(By.linkText("Organizations")).click();

		// Step 6: Click On create Organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		// Step 7: Create Organization with Mandatory details and Save
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 8 Validate Organization
		String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if (OrgHeader.contains(ORGNAME)) {
//			System.out.println(OrgHeader);
//			System.out.println("Organization created successfully");
//		} else {
//			System.out.println("Organization creation failed");
//		}
		Assert.assertTrue(OrgHeader.contains(ORGNAME));

		// Step 9: Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 10: Click on Create Contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Step 11: Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// Step 12: Navigate to Organizations look up image
		driver.findElement(By.xpath(" //input[@name='account_name']/following-sibling::img[@title='Select']")).click();

		// Step 13: Switch the control to organizations window
		wUtil.switchToWindow(driver, "Accounts");
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();

		driver.findElement(By.xpath("//a[text()='" + ORGNAME + "']")).click();// Dynamic Xpath - Xpath generated at run
																				// time

		// Step 14: Switch the control back to pareent window
		wUtil.switchToWindow(driver, "Contacts");

		// Step 15: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 16: Validate for Contacts
		String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if (ContactHeader.contains(LASTNAME)) {
//			System.out.println(ContactHeader);
//			System.out.println("PASS");
//		} else {
//			System.out.println("FAIL");
//		}
		Assert.assertTrue(ContactHeader.contains(LASTNAME), "FAIL:: ContactHeader not contain LastName");
		// Step 17: Logout of Application
		WebElement adminImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, adminImage);
		driver.findElement(By.linkText("Sign Out")).click();

		wUtil.minimizeWindow(driver);
		driver.quit();
	}
}
