package genericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility {
	  public WebDriver driver;
	/**
	 * This method will maximize the window when it called
	 * 
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method will minimize the window
	 * 
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method will wait for entire page to load for 20 seconds
	 * 
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS);
	}

	/**
	 * This method is used to wait for a element to be visible
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will wait for element to be Clickable
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will wait for alert is present
	 * 
	 * @param driver
	 * @param element
	 * @param seconds
	 */
	public void waitForElementToBePresent(WebDriver driver, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * This is a custom wait for wait for a second to wait for element to become
	 * Clickable
	 * 
	 * @param element
	 * @throws InterruptedException
	 */
	public void customWaitForSecond(WebElement element) throws InterruptedException {
		int count = 0;
		while (count < 5) {
			try {
				element.click();
				break;
			} catch (Exception e) {
				Thread.sleep(1000);
				count++;
			}
		}
	}

	/**
	 * This method will handle drop down based index value.
	 * 
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method will handle drop down based value.
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method will handle drop down based visibleText.
	 * 
	 * @param visibleText
	 * @param element
	 */
	public void handleDropDown(String visibleText, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	/**
	 * This method will perform mouse hover action
	 * 
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	/**
	 * This method will perform mouse hover action based on offset
	 * 
	 * @param driver
	 * @param element
	 * @param x
	 * @param y
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element, int x, int y) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element, x, y).perform();
	}

	/**
	 * This method will perform right click randomly on webpage
	 * 
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}

	/**
	 * This metho will perform right click on particular web element
	 * 
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
		;
	}

	/**
	 * This method will perform ouble click randomly on webpage
	 * 
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.doubleClick().perform();
	}

	/**
	 * This method will perform double click on particular webelement
	 * 
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();

	}

	/**
	 * This method will drag and drop from source element to target element
	 * 
	 * @param driver
	 * @param sourceEle
	 * @param tarEle
	 */
	public void dragAndDropAction(WebDriver driver, WebElement sourceEle, WebElement tarEle) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(sourceEle, tarEle).perform();
	}

	/**
	 * This method will press and release the enter key
	 * 
	 * @throws AWTException
	 */
	public void preesEnterKey() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * This method will switch to frame based on index
	 * 
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method will switch to frame based on name or id
	 * 
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	/**
	 * This method will switch to frame based on Webelemet element
	 * 
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method will switch to defaultContent frame
	 * 
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method will scroll down for 500 Seconds
	 * 
	 * @param driver
	 */
	public void scrollAction(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 500)", "");
	}

	/**
	 * This method will scroll until a particular element
	 * 
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		jse.executeScript("window.scrollBy(0, " + y + ")", element);
	}

	/**
	 * This method will take screenshot and save it in errorsshots folder
	 * 
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public String takestheScreenshot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		File dstFile = new File("./errorshots/" + screenshotName + ".png");
		Files.copy(srcFile, dstFile);

		return dstFile.getAbsolutePath();	// used for extent reports
	}

	/**
	 * This method will switch to window based on partial window title
	 * 
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver, String partialWinTitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			String currentWinTitle = driver.switchTo().window(windowId).getTitle();
			if (currentWinTitle.contains(partialWinTitle)) {
				break;
			}
		}
	}
}
