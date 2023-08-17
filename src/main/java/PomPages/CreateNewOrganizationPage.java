package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {

	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;

	@FindBy(name = "industry")
	private WebElement industryDropDown;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

//	Business Library
	/**
	 * This method will create Oraganization with Mandatory details
	 * 
	 * @param OrgName
	 */
	public void createNewOrganization(String OrgName) {
		OrgNameEdt.sendKeys(OrgName);
		saveBtn.click();
	}

	/**
	 * This method will create new Organization with industry Drop down
	 * 
	 * @param OrgName
	 * @param industryType
	 */
	public void createNewOrganization(String OrgName, String industryType) {
		OrgNameEdt.sendKeys(OrgName);
		handleDropDown(industryDropDown, industryType);
		saveBtn.click();
	}
}
