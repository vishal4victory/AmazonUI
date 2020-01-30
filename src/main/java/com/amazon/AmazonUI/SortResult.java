package com.amazon.AmazonUI;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Sort the results
 * @author skumar
 *
 */
public class SortResult extends UIActions {
	
	By sortResultElement = By.xpath("//*[@id='s-result-sort-select']");

	private Logger logger = Logger.getLogger(SortResult.class);

	public SortResult(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Enter the sortCriteria to search
	 * @param criteria
	 * @return
	 */
	public boolean select(SortCriteria criteria) {
		if(!selectFromDropDown(sortResultElement, criteria.getLevelCode())) {
        	return false;
        }
        return true;
	}
	
	
	 enum SortCriteria {
		 PriceLowToHigh  ("Price: Low to High"),
		 PriceHighToLow("Price: High to Low"),
		 AvgCustomerReview   ("Avg. Customer Review"),
		 PublicationDate ("Publication Date")
	    ; // semicolon needed when fields / methods follow


	    private final String levelCode;

	    SortCriteria(String levelCode) {
	        this.levelCode = levelCode;
	    }
	    
	    public String getLevelCode() {
	        return this.levelCode;
	    }
	    
	}
}
