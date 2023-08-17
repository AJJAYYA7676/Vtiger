package vTigerOrganizationTest;

import org.openqa.selenium.By;
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

public class TC_03CreateOrganizationWithIndustryAndTypeTest {
	@Test
	public void createOrganizationWithIndustryAndTypeTest() throws Throwable {

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
		String ORGNAME = eUtil.readDataFromExcelSheet("Organization", 1, 2) + jUtil.getRandomNumber();
		String INDUSTRYTYPE = eUtil.readDataFromExcelSheet("Organization", 4, 3);
		String TYPE = eUtil.readDataFromExcelSheet("Organization", 7, 4);

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

		// Step 4: Click on Organizations Link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 5: click on create organization lookup image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		// Step 6: Create new organization with mandatory information and save
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		Select industry = new Select(driver.findElement(By.name("industry")));
		industry.selectByValue(INDUSTRYTYPE);
		Select type = new Select(driver.findElement(By.name("accounttype")));
		type.selectByValue(TYPE);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

//		Thread.sleep(3000);
//		wUtil.waitForElementToBePresent(driver, 10);
//		driver.switchTo().alert().accept();

//		logout
		Thread.sleep(4000);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		//driver.manage().window().minimize();
		driver.quit();
	}
}
