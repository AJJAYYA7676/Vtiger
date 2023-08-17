package vTigerContactTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PomPages.ContactInformationPage;
import PomPages.ContactsPage;
import PomPages.CreatingNewContactPage;
import PomPages.HomePage;
import PomPages.LoginPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(genericUtilities.ListenerImplementationClass.class)
public class CreateContactTest {
	@Test
	public void createContactTest() throws IOException {

//		Step1 : Create all objects
		PropertyFileUtility pUtil = new PropertyFileUtility();
		JavaUtility jUtil = new JavaUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

//		Property file - Common Data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String URL = pUtil.readDataFromPropertyFile("url");

//		Excel File - TestData
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 1, 2) + jUtil.getRandomNumber();

		WebDriver driver = null;

//		Step2 : Launch Browser - run time polymorphism - driver
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid browser name");
		}

		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver, 15);
		driver.get(URL);

//		Step 3: Login to Application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

//		Step 4: Click on create ContactLink
		HomePage hp = new HomePage(driver);
		hp.clickOnContectsLink();

//		Step 5: Click on create contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();

//		Step 6: Create new Contact with mandatory information an save
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createNewContact(LASTNAME);

//		Step 7: Validate
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeaderTxt = cip.getContactHeaderText();
//		if (contactHeaderTxt.contains(LASTNAME)) {
//			System.out.println(contactHeaderTxt);
//			System.out.println("pass");
//		} else {
//			System.out.println("fail");
//		}
		Assert.assertEquals(contactHeaderTxt.contains(LASTNAME), true);

//		Step 8: logout
		hp.logoutOfApp(driver);

		//driver.manage().window().minimize();
		driver.quit();
	}
}
