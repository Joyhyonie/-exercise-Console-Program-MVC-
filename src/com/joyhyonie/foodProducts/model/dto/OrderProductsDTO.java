package com.joyhyonie.foodProducts.model.dto;

public class OrderProductsDTO {

	private int prodCode;
	private String prodName;
	private int price;
	private String supplierName;
	
	public OrderProductsDTO() {}

	public OrderProductsDTO(int prodCode, String prodName, int price, String supplierName) {
		super();
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.price = price;
		this.supplierName = supplierName;
	}

	public int getProdCode() {
		return prodCode;
	}

	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Override
	public String toString() {
		return "-----------------------\n" + "|" + prodCode + "| " + prodName + " | " + price
				+ "원 | [" + supplierName + "]";
	}

}
