package com.joyhyonie.foodProducts.views;

import java.util.List;

import com.joyhyonie.foodProducts.model.dto.FoodProductsDTO;
import com.joyhyonie.foodProducts.model.dto.OrderHistoryDTO;
import com.joyhyonie.foodProducts.model.dto.SupplierDTO;

public class ResultView {

	/* 재료 리스트 출력 */
	public void printProductsList(List<FoodProductsDTO> ProductsList) {
		
		for(FoodProductsDTO o : ProductsList) {
			System.out.println(o);
		}	
	}
	
	/* 업체 리스트 출력 */
	public void printSupplierList(List<SupplierDTO> allSupplierList) {
		
		for(SupplierDTO o : allSupplierList) {
			System.out.println(o);
		}	
	}
	
	/* 주문내역 리스트 출력 */
	public void printOrderHistoryList(List<OrderHistoryDTO> orderHistoryList) {
		
		for(OrderHistoryDTO o : orderHistoryList) {
			System.out.println(o);
		}	
	}
	
	/* CompleteMessage */
	public void printCompleteMessage(String completeCode) {
		
		String completeMessage = "";
		switch(completeCode) {
		case "successInsertOrderedProducts" : completeMessage = "성공적으로 주문이 완료되어 재료가 즉시 입고되었어요 :D"; break;
		case "successDeletePickedProduct" : completeMessage = "재료를 사용하였습니다 :D"; break;
		case "successUpdatePickedProduct" : completeMessage ="싱싱한 재료로 교환 완료되었습니다 :D"; break;
		case "successDeleteOldProducts" : completeMessage = "오래된 재료를 모두 폐기 완료하였습니다 :D"; break;
		case "successInsertNewProduct" : completeMessage = "업체가 판매 제안을 받아들여 이제부터 주문이 가능합니다 :D"; break;
		case "successUpdateSupplierInfo" : completeMessage = "업체 정보가 정상적으로 변경되었습니다 :D"; break;
		case "successDeleteSupplier" : completeMessage = "계약이 정상적으로 해지되었습니다 :D"; break;
		}
		
		System.out.println(completeMessage);
	}
 
	/* ErrorMessages */
	public void printErrorMessage(String errorCode) {
		
		String errorMessage = "";
		switch(errorCode) {
		case "failSelectRandomProducts" : errorMessage = "오늘의 재료를 찾지 못 했어요 :("; break;
		case "failSelectProductsBySupplier" : errorMessage = "재료들을 불러오지 못 했어요 :("; break;
		case "failInsetOrderedProducts" : errorMessage = "주문하신 내역이 전달되지 못 했어요 :("; break;
		case "failInsertProductsToStorage" : errorMessage = "입고 또한 실패했습니다 :( "; break;
		case "failSelectProductsBySomething" : errorMessage = "재료 파악에 실패했습니다 :("; break;
		case "failDeletePickedProduct" : errorMessage = "재료를 사용하지 못 했습니다 :("; break;
		case "failUpdatePickedProduct" : errorMessage = "재료를 교환받지 못 했습니다 :("; break;
		case "failDeleteOldProducts" : errorMessage = "해당 일수가 지난 재료가 없습니다 :("; break;
		case "failInsertNewProduct" : errorMessage = "끝내 제안이 거절 당했습니다 :("; break;
		case "failSelectAllSupplier" : errorMessage = "업체 정보를 불러오지 못 했습니다 :("; break;
		case "failUpdateSupplierInfo" : errorMessage = "업체명이 올바르지 않아 정보 변경에 실패했습니다 :("; break;
		case "failDeleteSupplier" : errorMessage = "업체명이 올바르지 않아 계약 해지에 실패했습니다 :("; break;
		case "failSelectOrderHistory" : errorMessage = "주문내역을 불러오지 못 했습니다 :("; break;
		}
		
		System.out.println(errorMessage);
	}
}
