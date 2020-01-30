package com.amazon.AmazonUI.validation;

import java.util.List;

public class Product {
	private String title;
	private String author;
	private String PublishDate;
	private List<ProductDetails> productsDetails;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishDate() {
		return PublishDate;
	}
	public void setPublishDate(String publishDate) {
		PublishDate = publishDate;
	}
	public List<ProductDetails> getProductsDetails() {
		return productsDetails;
	}
	public void setProductDetails(List<ProductDetails> productsDetails) {
		this.productsDetails = productsDetails;
	}
	public Product(String title, String author, String publishDate, List<ProductDetails> productsDetails) {
		super();
		this.title = title;
		this.author = author;
		PublishDate = publishDate;
		this.productsDetails = productsDetails;
	}
	@Override
	public String toString() {
		return "Product [title=" + title + ", author=" + author + ", PublishDate=" + PublishDate + ", productsDetails="
				+ productsDetails + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PublishDate == null) ? 0 : PublishDate.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((productsDetails == null) ? 0 : productsDetails.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (PublishDate == null) {
			if (other.PublishDate != null)
				return false;
		} else if (!PublishDate.equals(other.PublishDate))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (productsDetails == null) {
			if (other.productsDetails != null)
				return false;
		} else if (!productsDetails.equals(other.productsDetails))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}