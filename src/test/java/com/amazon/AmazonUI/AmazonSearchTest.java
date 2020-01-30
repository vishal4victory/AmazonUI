package com.amazon.AmazonUI;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.AmazonUI.AvgReview.ReviewLevel;
import com.amazon.AmazonUI.FormatSelection.BookFormat;
import com.amazon.AmazonUI.SortResult.SortCriteria;
import com.amazon.AmazonUI.validation.ValidateResults;

/**
 * This TestNG class is used to validate the Search result based on the search criteria
 * @author skumar
 *
 *
 */
public class AmazonSearchTest extends UITest{

	private SearchDropDown searchDropDown;
	private Search search;
	private Department department;
	private FormatSelection format;
	private PriceCriteria priceCriteria;
	private SortResult sort;
	private AvgReview avgCustomerReview;
	private SearchResult searchresult;
	private ValidateResults validateResults;
	private static Logger logger = Logger.getLogger(AmazonSearchTest.class);

	
	/**
	 * This testMethd is used to Store/Validate the results based on search criteria. it does the following
	 *  1) Launch the http://www.amazon.in 
	 *  2) Select "Books" as search filter
	 *  3) Search of "artificial intelligence"
	 *  4) Search of department - "Sciences, Technology & Medicine"
	 *  5) Search of sub-department - "Engineering & Technology"
	 *  6) Search based on highest Avg.Customer Review ranking - use the enum to make the Review Level selection
	 *  7) Sort the results based on PublicationDate - use the enum to make the SortCriteria selection	
	 *  8) 	Filter the price range b/w Rs1000 to Rs1500/-
	 *  9) Select All book format, use the enum to make the format selection	
	 *  10) Save and Store the Result for later validation - 1 time capture
	 *  11) This validates the 1st product result against the stored result
	 *  
	 *  Note: for Storing results of 2,3..n etc you can make selection using searchresult.getProductResult(n)
	 *  n is the result item from the top on a page. 
	 */
	@Test
	public void bookSearch() {

		boolean result = true;
		try {
			//Launch amazon.in and move to full screen
			result = result && start("http://www.amazon.in");
			
			//Select "Books" as search filter
			result = result && searchDropDown.select("Books");
			
			//Search of artificial intelligence
			result = result && search.searchFor("artificial intelligence");
			
			//Search of department - Sciences, Technology & Medicine
			result = result && department.select("Sciences, Technology & Medicine");
			
			//Search of department - Engineering & Technology
			result = result && department.select("Engineering & Technology");
			
			//Search based on highest Avg.Customer Review ranking - use the enum to make the Review Level selection	
			result = result && avgCustomerReview.select(ReviewLevel.HIGH);
			
			//Sort the results based on PublicationDate - use the enum to make the SortCriteria selection	
			result = result && sort.select(SortCriteria.PublicationDate);
			
			// Filter the price range b/w Rs1000 to Rs1500/-
			result = result && priceCriteria.selectPriceBetween("1000", "1500");
			
			//Select All book format, use the enum to make the format selection	
			result = result && format.select(BookFormat.All);
			
			//Uncomment be to Save and Store the Result for later validation - 1 time capture
			//result = result && ValidateResults.saveResult(searchresult.getProductResult(1), "AIBookSearch.xml");
			
			//This validates the 1st product result against the stored result
			result = result && validateResults.isEqual(searchresult.getProductResult(1), "AIBookSearch.xml");
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Here is an exception" + ex);
			
			result = false;
		}	
		Assert.assertTrue(result, "TEST: Result is " + result  );
	}
}
