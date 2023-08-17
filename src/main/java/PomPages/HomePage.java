package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {

//	declaration
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationsLink;

	@FindBy(linkText = "Contacts")
	private WebElement ContactsLink;

	@FindBy(linkText = "Opportunities")
	private WebElement OpportunitiesLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdiministorImg;

	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;

//	Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//	Utililization
	public WebElement getOrganizationsLink() {
		return OrganizationsLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getOpportunitiesLink() {
		return OpportunitiesLink;
	}

	public WebElement getAdiministorImg() {
		return AdiministorImg;
	}

	public WebElement getSignOutLink() {
		return SignOutLink;
	}

//	Business Library
	/**
	 * This method will check on Organizations link in Home Page
	 */
	public void clickOnOrgationLink() {
		OrganizationsLink.click();
	}

	/**
	 * This method will click on contacts link in home page
	 */
	public void clickOnContectsLink() {
		ContactsLink.click();
	}

	/**
	 * This method will sign out of app
	 * 
	 * @param driver
	 */
	public void logoutOfApp(WebDriver driver) {
		mouseHoverAction(driver, AdiministorImg);
		SignOutLink.click();
	}
}
