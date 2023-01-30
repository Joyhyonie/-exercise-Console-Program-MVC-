package com.joyhyonie.foodProducts.controller;

import java.util.List;
import java.util.Map;

import com.joyhyonie.common.SearchCriteria;
import com.joyhyonie.foodProducts.model.dto.FoodProductsDTO;
import com.joyhyonie.foodProducts.model.dto.OrderHistoryDTO;
import com.joyhyonie.foodProducts.model.dto.StorageDTO;
import com.joyhyonie.foodProducts.model.dto.SupplierDTO;
import com.joyhyonie.foodProducts.model.service.FoodProductsService;
import com.joyhyonie.foodProducts.views.ResultView;

public class FoodProductsController {

	private final FoodProductsService fpService;
	private final ResultView rView;
	
	// 위의 필드들을 생성자에서 초기화
	public FoodProductsController() {
		fpService = new FoodProductsService();
		rView = new ResultView();
	}
	
	/* [1-1] 오늘의 재료 추천받기 */
	public void selectRandomProducts(List<Integer> list) {

		List<FoodProductsDTO> RandomProductsList = fpService.selectRandomProducts(list);
		
		/* 사용자에게 보여줄 Result View */
		if(RandomProductsList != null) {
			rView.printProductsList(RandomProductsList);
		} else {
			rView.printErrorMessage("failSelectRandomProducts");
		}
	}
	
	/* [1-2] 유통업체 선택하기 */
	public void selectProductsBySupplier(SearchCriteria searchCriteria) {
		
		List<FoodProductsDTO> ProductsListBySupplier = fpService.selectProductsBySupplier(searchCriteria);
		
		if(ProductsListBySupplier != null) {
			rView.printProductsList(ProductsListBySupplier);
		} else {
			rView.printErrorMessage("failSelectProductsBySupplier");
		}
	}

	/* [1-3A] 전체 재료 조회하여 리스트에 담기 */
	public List<FoodProductsDTO> selectAllProducts() {
		
		List<FoodProductsDTO> allProductsList = fpService.selectAllProducts();
		
		return allProductsList;
	}

	/* [1-3B] 주문한 내역 저장하기 */
	public void insertOrderedProducts(OrderHistoryDTO order) {
		
		if(fpService.insertOrderedProducts(order)) {
			rView.printCompleteMessage("successInsertOrderedProducts");
		} else {
			rView.printErrorMessage("failInsetOrderedProducts");
		}
	}

	/* [1-3C] 주문한 재료 저장고에 추가하기 */
	public void insertProductsToStorage(List<StorageDTO> productsToStorageList) {
	
		if(!fpService.insertProductsToStorage(productsToStorageList))
			rView.printErrorMessage("failInsertProductsToStorage");
	}

	/* [2-1] 재료 파악하기 */
	public void selectProductsBySomething(SearchCriteria searchCriteria) {
		
		List<StorageDTO> myProductsList = fpService.selectProductsBySomething(searchCriteria);
		
		if(myProductsList != null && !myProductsList.isEmpty()) {
			for(StorageDTO products : myProductsList) {
				System.out.println(products);
			}
		} else {
			rView.printErrorMessage("failSelectProductsBySomething");
		}
		
	}

	/* [2-2] 재료 사용하기 */
	public void deletePickedProduct(Map<String, String> param) {
		
		String prodName = param.get("prodName");
		
		if(fpService.deletePickedProduct(prodName)) {
			rView.printCompleteMessage("successDeletePickedProduct");
		} else {
			rView.printErrorMessage("failDeletePickedProduct");
		}
		
	}

	/* [2-3] 상한 재료 교환받기 */
	public void updatePickedProduct(Map<String, String> param) {

		String prodName = param.get("prodName");
		
		if(fpService.updatePickedProduct(prodName)) {
			rView.printCompleteMessage("successUpdatePickedProduct");
		} else {
			rView.printErrorMessage("failUpdatePickedProduct");
		}
		
	}

	/* [2-4] 오래된 재료 모두 폐기하기 */
	public void deleteOldProducts(Map<String, String> param) {
		
		int days = Integer.parseInt(param.get("days"));
		
		if(fpService.deleteOldProducts(days)) {
			rView.printCompleteMessage("successDeleteOldProducts");
		} else {
			rView.printErrorMessage("failDeleteOldProducts");
		}
		
	}

	/* [3] 원하는 재료 판매 제안하기 */
	public void insertNewProduct(Map<String, String> param) {
		
		FoodProductsDTO foodProducts = new FoodProductsDTO();
		foodProducts.setProdName(param.get("product"));
		foodProducts.setPrice(Integer.parseInt(param.get("price")));
		foodProducts.setSupplierName(param.get("supplier"));
		
		if(fpService.insertNewProduct(foodProducts)) { 
			rView.printCompleteMessage("successInsertNewProduct");
		} else {
			rView.printErrorMessage("failInsertNewProduct");
		}
	}

	/* [4-1] 모든 업체 조회하기 */
	public void selectAllSupplier() {
	
		List<SupplierDTO> allSupplierList = fpService.selectAllSupplier();
		
		if(allSupplierList != null) {
			rView.printSupplierList(allSupplierList);
		} else {
			rView.printErrorMessage("failSelectAllSupplier");
		}
	}

	/* [4-2] 정보 변경하기 */
	public void updateSupplierInfo(Map<String, String> param) {
		
		SupplierDTO supplier = new SupplierDTO();
		supplier.setSupplierName(param.get("suppName"));
		supplier.setAddress(param.get("newAddress"));
		supplier.setContactNumber(param.get("newContactNumber"));
		supplier.setOrderYn(param.get("newOrderYn"));
		
		if(fpService.updateSupplierInfo(supplier)) {
			rView.printCompleteMessage("successUpdateSupplierInfo");
		} else {
			rView.printErrorMessage("failUpdateSupplierInfo");
		}
	}

	/* [4-3] 계약 해지하기 */
	public void deleteSupplier(Map<String, String> param) {
		
		String suppName = param.get("suppName");
		
		if(fpService.deleteSupplier(suppName)) {
			rView.printCompleteMessage("successDeleteSupplier");
		} else {
			rView.printErrorMessage("failDeleteSupplier");
		}
		
	}

	/* [5] 주문내역 조회하기 */
	public void selectOrderHistory(Map<String, String> param) {
		
		String range = param.get("range");
		
		List<OrderHistoryDTO> orderHistoryList = fpService.selectOrderHistory(range);
		
		if(orderHistoryList != null) {
			rView.printOrderHistoryList(orderHistoryList);
		} else {
			rView.printErrorMessage("failSelectOrderHistory");
		}
		
	}


	

	
	
	
	

}
