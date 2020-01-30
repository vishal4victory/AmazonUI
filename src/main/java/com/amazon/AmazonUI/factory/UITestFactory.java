package com.amazon.AmazonUI.factory;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;

import com.amazon.AmazonUI.AvgReview;
import com.amazon.AmazonUI.Department;
import com.amazon.AmazonUI.FormatSelection;
import com.amazon.AmazonUI.PriceCriteria;
import com.amazon.AmazonUI.SearchResult;
import com.amazon.AmazonUI.Search;
import com.amazon.AmazonUI.SearchDropDown;
import com.amazon.AmazonUI.SortResult;
import com.amazon.AmazonUI.validation.ValidateResults;
import com.relevantcodes.extentreports.ExtentTest;

public class UITestFactory {
	private WebDriver driver;
	private ExtentTest extentTest;
	private SearchDropDown searchDropDown;
	private Search search;
	private Department department;
	private FormatSelection format;
	private PriceCriteria priceCriteria;
	private SortResult sort;
	private AvgReview avgCustomerReview;
	private SearchResult result;
	private ValidateResults validateResults;
	

	public UITestFactory(WebDriver driver, ExtentTest extentTest) {
		this.driver = driver;
		this.extentTest = extentTest;
	}

	public SearchDropDown getSearchDropDown() {
		if (searchDropDown == null) {
			searchDropDown = new SearchDropDown(driver);
			searchDropDown.setExtentTest(extentTest);
		}
		return searchDropDown;
	}

	public void setSearchDropDown(SearchDropDown searchDropDown) {
		this.searchDropDown = searchDropDown;
	}

	public Search getSearch() {
		if (search == null) {
			search = new Search(driver);
			search.setExtentTest(extentTest);
		}
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public Department getDepartment() {
		if (department == null) {
			department = new Department(driver);
			department.setExtentTest(extentTest);
		}
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public FormatSelection getFormat() {
		if (format == null) {
			format = new FormatSelection(driver);
			format.setExtentTest(extentTest);
		}
		return format;
	}

	public void setFormat(FormatSelection format) {
		if(format == null) {
			format = new FormatSelection(driver);
			format.setExtentTest(extentTest);
		}
		this.format = format;
	}

	public PriceCriteria getPriceCriteria() {
		if(priceCriteria == null) {
			priceCriteria = new PriceCriteria(driver);
			priceCriteria.setExtentTest(extentTest);
		}
		return priceCriteria;
	}

	public void setPriceCriteria(PriceCriteria priceCriteria) {
		this.priceCriteria = priceCriteria;
	}

	public SortResult getSort() {
		if(sort == null) {
			sort = new SortResult(driver);
			sort.setExtentTest(extentTest);
		}
		return sort;
	}

	public void setSort(SortResult sort) {
		this.sort = sort;
	}
	
	

	public SearchResult getResult() {
		if(result == null) {
			result = new SearchResult(driver);
			result.setExtentTest(extentTest);
		}
		return result;
	}

	public void setResult(SearchResult result) {
		this.result = result;
	}

	public AvgReview getAvgCustomerReview() {
		if(avgCustomerReview == null) {
			avgCustomerReview = new AvgReview(driver);
			avgCustomerReview.setExtentTest(extentTest);
		}
		return avgCustomerReview;
	}

	public void setAvgCustomerReview(AvgReview avgCustomerReview) {
		this.avgCustomerReview = avgCustomerReview;
	}


	
	public ValidateResults getValidateResults() {
		return validateResults;
	}

	public void setValidateResults(ValidateResults validateResults) {
		this.validateResults = validateResults;
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(Class<T> validator) throws Exception {

		Field[] fs = this.getClass().getDeclaredFields();
		fs[0].setAccessible(true);
		for (Field property : fs) {
			if (property.getType().isAssignableFrom(validator)) {
				if (property.get(this) == null)
					property.set(this, validator.getConstructor(WebDriver.class).newInstance(driver));
				return (T) property.get(this);
			}

		}
		return null;
	}

}
