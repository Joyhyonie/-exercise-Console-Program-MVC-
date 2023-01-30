package com.joyhyonie.foodProducts.model.dto;

public class FoodCategoryDTO {

	private int categoryCode;
	private String category;
	
	public FoodCategoryDTO() {}

	public FoodCategoryDTO(int categoryCode, String category) {
		super();
		this.categoryCode = categoryCode;
		this.category = category;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return category;
	}
}
