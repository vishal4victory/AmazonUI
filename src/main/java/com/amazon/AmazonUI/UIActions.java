package com.amazon.AmazonUI;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class UIActions {

    protected WebDriver driver;
    protected ExtentTest extentTest;
    WebDriverWait wait;
    
    final static Logger logger = Logger.getLogger(UIActions.class);

    public UIActions(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, 10);
    }
    public void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }
    
    public boolean waitUntilDisappear(By elementKey, int timeout) {
    	
    	int secondsPassed = 0;
		WebElement ele;
		while (secondsPassed < timeout) {
			try {
				ele = driver.findElement(elementKey);

			} catch (NoSuchElementException ex) {
				//logger.error("Caught exception: " + ex);
				ele=null;
			}
			if (ele!=null ) {
				if(!ele.isDisplayed()) {
					logger.info("element not present : " + elementKey + "");
					extentTest.log(LogStatus.PASS, "element not present : " + elementKey + "");
					return true;
				
			}}else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				secondsPassed=secondsPassed+2;
				logger.info("Wait for element " + elementKey + " to disappear");
				//return true;
			}
		}

		logger.info("Element does not disappear: " + elementKey + "");
		extentTest.log(LogStatus.FAIL,"Element does not disappear: " + elementKey + "");
		return false;

    }

    
    public boolean clickOnElement(WebElement webElement) {
    	
    	if(System.getProperty("browser").contains("chrome")) {
    	webElement.click();
    	}else {
    		try { 
    			((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    			webElement.click();
    		}catch (Exception e) {
				// TODO: handle exception
    			webElement.sendKeys(Keys.ENTER);
			}
    		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    	}
    	
    	return true;
    	
    }
    
    public WebElement findElement(By element) {
    	WebElement elementOfInterest = null;
    	try {
			elementOfInterest = driver.findElement(element);

		} catch (NoSuchElementException ex) {
			logger.error("Caught exception: " + ex);
		}
    	
		return elementOfInterest;
    	
    }
    
    public List<WebElement> findElementList(By element) {
    	List<WebElement> elementOfInterest = null;
    	try {
			elementOfInterest = (List<WebElement>) driver.findElements(element);

		} catch (NoSuchElementException ex) {
			logger.error("Caught exception: " + ex);
		}
    	
		return elementOfInterest;
    	
    }
    
	protected boolean isElementPresent(By by) {

		try {
			WebElement elementOfInterest = driver.findElement(by);

		} catch (NoSuchElementException ex) {
			logger.error("Caught exception: " + ex);
			return false;
		}

		return true;
	}
	
	protected boolean isElementEnabled(By by) {

		try {
			if(isElementPresent(by))
				return driver.findElement(by).isEnabled();

		} catch (NoSuchElementException ex) {
			logger.error("Caught exception: " + ex);
			return false;
		}
		return false;
	}
	
	protected boolean isElementSelected(By by) {

		try {
			if(isElementPresent(by))
				return driver.findElement(by).isSelected();

		} catch (NoSuchElementException ex) {
			logger.error("Caught exception: " + ex);
			return false;
		}
		return false;
	}
	
	public void waitInSeconds(int sec) {

		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean selectFromDropDown(By element, String item) {
		//SearchDropdown
		
		if(System.getProperty("browser").contains("firefox")) {
			
		}
		Select dropDown = new Select(findElement(element));
		dropDown.selectByVisibleText(item);
		return true;
	}
    
   
}
