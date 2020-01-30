package com.amazon.AmazonUI;

import java.util.EnumSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class FormatSelection extends UIActions {

	String formatElement = "//div[@id='filters']/ul[@aria-labelledby='p_n_binding_browse-bin-title']";
	String checkBoxElement = "//i[@class='a-icon a-icon-checkbox']";

	private Logger logger = Logger.getLogger(FormatSelection.class);
	
	public FormatSelection(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean select(BookFormat format) {

		if (format == BookFormat.All) {
			EnumSet.allOf(BookFormat.class).forEach(BookFormat -> selectFormat(BookFormat));
		}

		return true;
	}

	private boolean selectFormat(BookFormat format) {

		if (!format.equals(BookFormat.All)) {
			By forElement = By.xpath(formatElement + "/li[@aria-label=\"" + format.levelCode + "\"]"+checkBoxElement);
			if (wait.until(ExpectedConditions.elementToBeClickable(forElement)).isEnabled()) {
				
				if(!clickOnElement(findElement(forElement))){
		            extentTest.log(LogStatus.FAIL, "Unable to select the book format"+format);
		            logger.info("Unable to select the book format"+format);
					return false;
				}
				waitInSeconds(3);
		        extentTest.log(LogStatus.PASS, "format selected:"+format);
		        logger.info("format selected:"+format);
				return true;
			}
		}
		return true;
	}

	enum BookFormat {
		All("All"), Paperback("Paperback"), Hardcover("Hardcover"), KindleeBooks("Kindle eBooks"), Audiobook(
				"Audiobook"), AudibleAudioEdition("Audible Audio Edition");

		private final String levelCode;

		BookFormat(String levelCode) {
			this.levelCode = levelCode;
		}

		public String getLevelCode() {
			return this.levelCode;
		}

	}

}
