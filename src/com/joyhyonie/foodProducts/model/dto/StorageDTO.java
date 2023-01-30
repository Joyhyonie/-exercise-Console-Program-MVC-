package com.joyhyonie.foodProducts.model.dto;

public class StorageDTO {
	
	private int prodCode;
	private String prodName;
	private String incomingDate;
	
	public StorageDTO() {}

	public StorageDTO(int prodCode, String prodName, String incomingDate) {
		super();
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.incomingDate = incomingDate;
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

	public String getIncomingDate() {
		return incomingDate;
	}

	public void setIncomingDate(String incomingDate) {
		this.incomingDate = incomingDate;
	}

	@Override
	public String toString() {
		return "[" + incomingDate + "입고] " + "|" + prodCode + "| " + prodName;
	}
}
