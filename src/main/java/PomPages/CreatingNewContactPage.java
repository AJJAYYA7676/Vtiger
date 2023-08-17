package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	@FindBy(name = "lastname")
	private WebElement lastnameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastnameFT() {
		return lastnameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

//	Business Library
	/**
	 * This method will create Contact with Mandatory details
	 * 
	 * @param lastname
	 */
	public void createNewContact(String lastname) {
		lastnameEdt.sendKeys(lastname);
		saveBtn.click();

	}
}
