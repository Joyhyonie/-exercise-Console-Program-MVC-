package com.joyhyonie.foodProducts.model.dto;

public class SupplierDTO {
	
	private int categoryCode;
	private String supplierCode;
	private String supplierName;
	private String address;
	private String contactNumber;
	private String orderYn;
	private FoodCategoryDTO foodCategory; // TBL_FOOD_CATEGORY의 category를 함께 출력하기 위해 추가 <association>
	
	public SupplierDTO() {}

	public SupplierDTO(int categoryCode, String supplierCode, String supplierName, String address, String contactNumber,
			String orderYn, FoodCategoryDTO foodCategory) {
		super();
		this.categoryCode = categoryCode;
		this.supplierCode = supplierCode;
		this.supplierName = supplierName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.orderYn = orderYn;
		this.foodCategory = foodCategory;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getOrderYn() {
		return orderYn;
	}

	public void setOrderYn(String orderYn) {
		this.orderYn = orderYn;
	}

	public FoodCategoryDTO getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodCategoryDTO foodCategory) {
		this.foodCategory = foodCategory;
	}

	@Override
	public String toString() {
		return "[" + supplierName + "]-----------------------\n* " + foodCategory + "업체(" + categoryCode + ")\n* 업체코드 : " + supplierCode + "\n* 주소 : " + address +
				"\n* 연락처 : " + contactNumber + "\n* 거래가능여부 : " + orderYn + "\n";
	}
}
