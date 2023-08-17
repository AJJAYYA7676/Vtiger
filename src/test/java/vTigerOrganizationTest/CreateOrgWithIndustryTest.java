package vTigerOrganizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PomPages.CreateNewOrganizationPage;
import PomPages.HomePage;
import PomPages.LoginPage;
import PomPages.OrganizationInfoPage;
import PomPages.OrganizationsPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithIndustryTest {

	// Step 1: Create all the Objects
	PropertyFileUtility pUtil = new PropertyFileUtility();
	JavaUtility jUtil = new JavaUtility();
	ExcelFileUtility eUtil = new ExcelFileUtility();
	WebDriverUtility wUtil = new WebDriverUtility();

	@Test(dataProvider = "OrgData")
	public void createMultipleOrgTest(String ORG, String INDUSTRY) throws IOException {

		/* Property File - Common Data */
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");

		String ORGNAME = ORG + jUtil.getRandomNumber();

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
		wUtil.waitForPageLoad(driver, 15);
		driver.get(URL);

		// Step 3: Login To Application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		// Step 4: Click on Organizations Link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgationLink();

		// Step 5: click on create organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookUpImg();

		// Step 6: Create new organization with mandatory information and save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME, INDUSTRY);

		// Step 7: Validate:
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getOrganizationHeaderText();
//		if (OrgHeader.contains(ORGNAME)) {
//			System.out.println(OrgHeader);
//			System.out.println("pass");
//		} else {
//			System.out.println("fail");
//		}
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		// Step 8: logout
		hp.logoutOfApp(driver);

		wUtil.minimizeWindow(driver);
		driver.quit();
	}

	@DataProvider(name = "OrgData")
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		return eUtil.readMultipleDataIntoDataProvider("MultipleOrganization");
	}

}
