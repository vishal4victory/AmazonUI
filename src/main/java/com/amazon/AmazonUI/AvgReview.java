package com.amazon.AmazonUI;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

/**
 * @author skumar
 * 
 *         Use this to search based on customer review
 *
 */
public class AvgReview extends UIActions {

	By avgReviewElement = By.xpath("//div[@id='reviewsRefinements']//a");

	private Logger logger = Logger.getLogger(AvgReview.class);

	public AvgReview(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * pass the ReviewLevel enum for selection
	 * 
	 * @param review
	 * @return
	 */
	public boolean select(ReviewLevel review) {

		if (!clickOnElement(findElementList(avgReviewElement).get(review.getLevelCode()))) {
			extentTest.log(LogStatus.FAIL, "Unable to click avgReviewElement");
			logger.info("Unable to click avgReviewElement");
			return false;
		}
		waitInSeconds(3);
		extentTest.log(LogStatus.PASS, "avgReviewElement clicked");
		logger.info("avgReviewElement clicked");
		return true;

	}

	enum ReviewLevel {
		HIGH(0), MEDIUM(1), LOW(2), LOWEST(3); // semicolon needed when fields / methods follow

		private final int levelCode;

		ReviewLevel(int levelCode) {
			this.levelCode = levelCode;
		}

		public int getLevelCode() {
			return this.levelCode;
		}

	}
}
