package com.amazon.AmazonUI;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class Search extends UIActions {
	By searchTextBox = By.xpath("//*[@id='twotabsearchtextbox']");
	By submit = By.xpath("//div[@class='nav-search-submit nav-sprite']//input[@class='nav-input']");

	private Logger logger = Logger.getLogger(Search.class);

	public Search(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean searchFor(String item) {

		findElement(searchTextBox).sendKeys(item);
		// Search
		return clickSearch();

	}

	private boolean clickSearch() {
		if (!clickOnElement(findElement(submit))) {
			extentTest.log(LogStatus.FAIL, "Unable to Search");
			logger.info("Unable to Search");
			return false;
		}
        extentTest.log(LogStatus.PASS, "Searched the specific item");
        logger.info( "Searched the specific item");
		return true;

	}

}
