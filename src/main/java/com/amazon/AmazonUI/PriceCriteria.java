package com.amazon.AmazonUI;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class PriceCriteria extends UIActions {
	
	By lowPriceElement = By.xpath("//input[@id='low-price']");
	By highPriceElement = By.xpath("//input[@id='high-price']");
	By searchPriceElement = By.xpath("//input[@class='a-button-input']");
	
	private Logger logger = Logger.getLogger(PriceCriteria.class);

	public PriceCriteria(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean selectPriceBetween(String low, String high ) {
		//Filter Price
		findElement(lowPriceElement).sendKeys(low);
		findElement(highPriceElement).sendKeys(high);
		if(!clickOnElement(findElement(searchPriceElement))) {
            extentTest.log(LogStatus.FAIL, "failed to searchBased on SearchCriteria");
            logger.info("failed to searchBased on SearchCriteria");
			return false;
		}
        extentTest.log(LogStatus.PASS, "Search based on Price Criteria Success");
        logger.info( "Search based on Price Criteria Success");
		return true;
	}
	
}
