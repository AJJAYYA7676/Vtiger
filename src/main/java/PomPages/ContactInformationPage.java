package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {

	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderTxt;
	
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getContactHeaderTxt() {
		return contactHeaderTxt;
	}
//	Business Library
	/**
	 * This method will give the text of Header (Conformation message)
	 * 
	 * @return
	 */
	public String getContactHeaderText() {
		return contactHeaderTxt.getText();
	}
}
