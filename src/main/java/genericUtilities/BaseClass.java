package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import PomPages.HomePage;
import PomPages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class consists of basic configuration annotations.
 * 
 * @author Dell
 *
 */
public class BaseClass {

//	Inherit all the Generic Utilities
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public static WebDriver sdriver = null;
	
	public WebDriver driver = null;

//	BeforeSuite Generally used to Start the DataBase Connection
	@BeforeSuite(groups = "SmokeTesting")
	public void bsConfig() {
		System.out.println("=========DataBase Connection Successfull==========");
	}

//	BeforeClass is used to Launch the Browser, maximize, Implicit Wait, URL
	@BeforeClass(groups = "SmokeTesting")
	public void bcConfig() throws IOException {
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("=========" + BROWSER + "=========");
		} else if (BROWSER.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("=========" + BROWSER + "=========");
		} else {
			System.out.println("======Invalid Browser Details");
		}

		sdriver = driver;
		wUtil.maximizeWindow(driver);
		System.out.println("=========maximized=========");
		wUtil.waitForPageLoad(driver, 15);
		System.out.println("=========WebDriverWait=========");
		driver.get(URL);
		System.out.println("=========URL=========");
	}

//	BeforeMethod is used to Login to application
	@BeforeMethod(groups = "SmokeTesting")
	public void bmConfg() throws IOException {
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("=========login to app is successful=========");
	}

//	AfterMethod is used to generally LogOut from The Application
	@AfterMethod(groups = "SmokeTesting")
	public void amConfg() {
		HomePage hp = new HomePage(driver);
		hp.logoutOfApp(driver);
		System.out.println("========Logout of app is successful==========");
	}

//	AfterClass Is used to Minimize and quit the Browser
	@AfterClass(groups = "SmokeTesting")
	public void acConfg() {
		wUtil.minimizeWindow(driver);
		System.out.println("=========Browser is minimized=========");
		driver.quit();
		System.out.println("=========quit=========");
	}

//	AferSuite is used to Stop/close the DataBase Connection
	@AfterSuite(groups = "SmokeTesting")
	public void asConfg() {
		System.out.println("=========DataBase Is closed============");
	}
}
