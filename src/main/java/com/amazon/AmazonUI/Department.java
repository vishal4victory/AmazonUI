package com.amazon.AmazonUI;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

/**
 * Search based on department
 * @author skumar
 *
 */
public class Department extends UIActions {
	By department = By.xpath("//*[@id='departments']//a");
	
    private Logger logger = Logger.getLogger(Department.class);

	public Department(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * pass the dept to select
	 * @param deptText
	 * @return
	 */
	public boolean select(String deptText) {
		//Get Sub Departments and select a Department "Sciences, Technology & Medicine"
		List<WebElement> departments = findElementList(department);
		
		for (int i = 0; i < departments.size();i++) {
			if(departments.get(i).getText().contains(deptText)) {
				//departments.get(i).click();
				if(!clickOnElement(departments.get(i))) {
		            extentTest.log(LogStatus.FAIL, "Unable to select the department"+deptText);
		            logger.info("Unable to click department"+department);
					return false;
				}
				waitInSeconds(3);
		        extentTest.log(LogStatus.PASS, "department clicked:"+deptText);
		        logger.info("department clicked"+deptText);
		        return true;
				
			}
			if(departments.get(i).getText().contains("See more")) {
				if(!clickOnElement(departments.get(i))) {
		            extentTest.log(LogStatus.FAIL, "Failed to click see more");
		            logger.info("Failed to click see more");
					return false;
				}
				waitInSeconds(3);
				departments = findElementList(department);
			}
			
		}
        extentTest.log(LogStatus.FAIL, "dept not found ");
        logger.info("dept not found ");
        
		return false;
	}

}
