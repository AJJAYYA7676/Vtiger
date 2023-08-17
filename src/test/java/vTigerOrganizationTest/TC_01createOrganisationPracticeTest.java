package vTigerOrganizationTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_01createOrganisationPracticeTest {
	@SuppressWarnings("unused")
	@Test
	public void createOrganisationPracticeTest() throws IOException, InterruptedException {

		// Step 1: Read the necessary data
		/* Property File - Common Data */
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		/* Excel File - Test Data */
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Organization");
		String orgName = sh.getRow(1).getCell(2).getStringCellValue();

		WebDriver driver = null;

		// Step 2: Launch Browser - run time polymorphism - driver
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid browser name");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
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
		driver.findElement(By.name("accountname")).sendKeys("abv12");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

//		logout
//		Thread.sleep(5000);

		Thread.sleep(4000);
		WebElement signOutLink = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signOutLink).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		//driver.manage().window().minimize();
		driver.quit();
	}
}
