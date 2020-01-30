package com.amazon.AmazonUI;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amazon.AmazonUI.Resultdata.ProductDetail;
import com.amazon.AmazonUI.validation.ProductDetails;

public class SearchResult extends UIActions{
	String searchResultsElement = "//div[@class='s-result-list s-search-results sg-row']/div";
	String priceElement = "//a[@class='a-size-base a-link-normal a-text-bold']/parent::div/following-sibling::div//a/span[@class='a-price']";
	String formatElement = "//a[@class='a-size-base a-link-normal a-text-bold']/parent::div";
	String titleElement = "//a/span[@class='a-size-medium a-color-base a-text-normal']";
	String subTitleElement = "//div/div[@class='a-row a-size-base a-color-secondary']//span[contains(text(),'by ')]/following-sibling::*[contains(@class,'a-size-base')]";
	
	private Logger logger = Logger.getLogger(PriceCriteria.class);
	
	public SearchResult(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public Resultdata getProductResult(int position) {
		
		String title =  findElement(By.xpath(searchResultsElement+"["+position+"]"+titleElement)).getText();
		List<WebElement> results =  findElementList(By.xpath(searchResultsElement+"["+position+"]"+subTitleElement));
		StringBuffer subTitle = new StringBuffer();
		for (WebElement webElement : results) {
			subTitle.append(webElement.getText());
		}


		StringBuffer author = new StringBuffer();
		StringBuffer publishDate = new StringBuffer();
		String[] sutTitl = subTitle.toString().split("\\|");
		author.append(sutTitl[0]);
		if(sutTitl.length>1)
			publishDate.append(sutTitl[1]);
		

		List<WebElement> priceDetails = findElementList(By.xpath(searchResultsElement+"["+position+"]"+priceElement));
		List<WebElement> formatDetails = findElementList(By.xpath(searchResultsElement+"["+position+"]"+formatElement));

		//List<ProductDetails> productsDetails = new ArrayList<ProductDetails>();
		Resultdata resData = new Resultdata();
		List<ProductDetail> products = new ArrayList<ProductDetail>();
		int size = priceDetails.size();
		for (int i = 0; i < size; i++) {
			
			StringBuffer price = new StringBuffer();
			price = price.append(priceDetails.get(i).getText());
			String priceSt = price.toString().replace("\n", ".");
			StringBuffer format = new StringBuffer(formatDetails.get(i).getText());
			ProductDetails priceFormat = new ProductDetails(format.toString(), price.toString());
			//products.add(priceFormat);
			ProductDetail proDetail = new ProductDetail();
			proDetail.getFormat().setName(format.toString());
			proDetail.getPrice().setName(priceSt);
			products.add(proDetail);
			//productsDetails.add(priceFormat);
			
		}

		//Product productResult = new Product(title, author, publishDate, productsDetails);
		
		
		//System.out.println(productResult);
		resData.getTitle().setName(title);
		resData.getAuthor().setName(author.toString());
		resData.getPublishDate().setName(publishDate.toString());
		resData.getProductDetail().addAll(products);

		return resData;

	}

	
	
}
