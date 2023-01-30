package com.joyhyonie.foodProducts.model.dto;

public class OrderHistoryDTO {
	
	private int orderCode;
	private String orderDate;
	private String orderTime;
	private int totalPrice;
	
	public OrderHistoryDTO() {}

	public OrderHistoryDTO(int orderCode, String orderDate, String orderTime, int totalPrice) {
		super();
		this.orderCode = orderCode;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "|" + orderCode + "|주문일자 " + orderDate + "|주문시각 " + orderTime
				+ "|총액 " + totalPrice + "원";
	}

	
}
