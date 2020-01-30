package com.amazon.AmazonUI;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class SearchDropDown extends UIActions{
	
	By searchDropDown = By.xpath("//*[@id='searchDropdownBox']");

	private Logger logger = Logger.getLogger(SearchDropDown.class);
	
	public SearchDropDown(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean select(String criteria) {
		//SearchDrobdown
		if(!selectFromDropDown(searchDropDown, criteria)) {
            extentTest.log(LogStatus.FAIL, "Unable to selectsearch dropDown"+criteria);
            logger.info( "Unable to selectsearch dropDown"+criteria);
		}
		
        extentTest.log(LogStatus.PASS, "selectsearch dropDown selected"+criteria);
        logger.info("selectsearch dropDown selected"+criteria);
		
		return true;
	}
}
