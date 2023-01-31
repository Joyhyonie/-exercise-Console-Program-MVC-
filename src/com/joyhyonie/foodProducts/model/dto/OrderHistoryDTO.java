package com.joyhyonie.foodProducts.model.dto;

import java.util.List;

public class OrderHistoryDTO {
	
	private int orderCode;
	private String orderDate;
	private String orderTime;
	private int totalPrice;
	private List<StorageDTO> storageDTO; // 주문내역과 주문한 재료를 하나의 트랜잭션 안에서 INSERT하기 위해 List<StorageDTO> 필드 생성
	
	public OrderHistoryDTO() {}
	
	public OrderHistoryDTO(int orderCode, String orderDate, String orderTime, int totalPrice,
			List<StorageDTO> storageDTO) {
		super();
		this.orderCode = orderCode;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
		this.storageDTO = storageDTO;
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

	public List<StorageDTO> getStorageDTO() {
		return storageDTO;
	}

	public void setStorageDTO(List<StorageDTO> storageDTO) {
		this.storageDTO = storageDTO;
	}

	@Override
	public String toString() {
		return "|" + orderCode + "|주문일자 " + orderDate + "|주문시각 " + orderTime
				+ "|총액 " + totalPrice + "원";
	}
}
