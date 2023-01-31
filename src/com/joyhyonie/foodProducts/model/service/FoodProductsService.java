package com.joyhyonie.foodProducts.model.service;

import static com.joyhyonie.common.Template.getSqlSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

import com.joyhyonie.common.SearchCriteria;
import com.joyhyonie.foodProducts.mapper.FoodProductsMapper;
import com.joyhyonie.foodProducts.model.dto.FoodProductsDTO;
import com.joyhyonie.foodProducts.model.dto.OrderHistoryDTO;
import com.joyhyonie.foodProducts.model.dto.StorageDTO;
import com.joyhyonie.foodProducts.model.dto.SupplierDTO;

public class FoodProductsService {
	
	private FoodProductsMapper mapper;

	/* [1-1] 오늘의 재료 추천받기 */
	public List<FoodProductsDTO> selectRandomProducts(List<Integer> randomProductsList) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* Map에 집어넣기 */
		Map<String, List<Integer>> criteria = new HashMap<>();
		criteria.put("randomProductsList", randomProductsList);
		
		List<FoodProductsDTO> productsList = mapper.selectRandomProducts(criteria);
		
		sqls.close();
		
		return productsList;
	}

	/* [1-2] 유통업체 선택하기 */
	public List<FoodProductsDTO> selectProductsBySupplier(SearchCriteria searchCriteria) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		List<FoodProductsDTO> productsList = mapper.selectProductsBySupplier(searchCriteria);
		
		sqls.close();
		
		return productsList;
	}

	/* [1-3A] 전체 판매 재료 조회하여 리스트에 담기 */
	public List<FoodProductsDTO> selectAllProducts() {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		List<FoodProductsDTO> allproductsList = mapper.selectAllProducts();

		sqls.close();
		
		return allproductsList;
	}
	
	/* [1-3B] 주문한 내역 저장하기 / [1-3C] 주문한 재료 저장고에 추가하기 */
	public boolean insertOrderedProducts(OrderHistoryDTO order) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* 주문한 내역 저장하기 */
		int result1 = mapper.insertOrderedProducts(order);
		
		/* 주문한 재료 저장고에 추가하기 */
		int result2 = 0;
		for(StorageDTO storage : order.getStorageDTO()) {
			result2 += mapper.insertProductsToStorage(storage);			
		}
		
		if(result1 > 0 && result2 > 0) { // 주문 내역과 재료들이 모두 정상적으로 INSERT되었을 때, commit (하나의 트랜잭션 안에서 수행)
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result1 > 0 && result2 > 0 ? true : false;
	}

	/* [1-3C] 주문한 재료 저장고에 추가하기 */
	public boolean insertProductsToStorage(List<StorageDTO> productsToStorageList) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* INSERT이므로 int로 반환받기 */
		int result = 0;
		for(StorageDTO storage : productsToStorageList) {
			result += mapper.insertProductsToStorage(storage);
		}
		
		if(result > 0) {
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result > 0 ? true : false;
	}

	/* [2-1] 재료 파악하기 */
	public List<StorageDTO> selectProductsBySomething(SearchCriteria searchCriteria) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		List<StorageDTO> myProductsList = mapper.selectProductsBySomething(searchCriteria);

		sqls.close();
		
		return myProductsList;
	}

	/* [2-2] 재료 사용하기 */
	public boolean deletePickedProduct(String prodName) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* DELETE이므로 int로 반환받기 */
		int result = mapper.deletePickedProduct(prodName);
		
		if(result > 0) {
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result > 0 ? true : false;
	}
	/* [2-3] 상한 재료 교환받기 */
	public boolean updatePickedProduct(String prodName) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* UPDATE이므로 int로 반환받기 */
		int result = mapper.updatePickedProduct(prodName);
		
		if(result > 0) {
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result > 0 ? true : false;
	}

	/* [2-4] 오래된 재료 모두 폐기하기 */
	public boolean deleteOldProducts(int days) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* DELETE이므로 int로 반환받기 */
		int result = mapper.deleteOldProducts(days);
		
		if(result > 0) {
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result > 0 ? true : false;
	
	}

	/* [3] 원하는 재료 판매 제안하기 */
	public boolean insertNewProduct(FoodProductsDTO foodProducts) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* INSERT이므로 int로 반환받기 */
		int result = mapper.insertNewProduct(foodProducts);
		
		if(result > 0) {
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result > 0 ? true : false;
	}

	/* [4-1] 모든 업체 조회하기 */
	public List<SupplierDTO> selectAllSupplier() {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		List<SupplierDTO> allSupplierList = mapper.selectAllSupplier();
		
		sqls.close();
		
		return allSupplierList;
	}
	
	/* [4-2] 정보 변경하기 */
	public boolean updateSupplierInfo(SupplierDTO supplier) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* UPDATE이므로 int로 반환받기 */
		int result = mapper.updateSupplierInfo(supplier);
		
		if(result > 0) {
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result > 0 ? true : false;
	}

	/* [4-3] 계약 해지하기 */
	public boolean deleteSupplier(String suppName) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		
		/* DELETE이므로 int로 반환받기 */
		int result = mapper.deleteSupplier(suppName);
		
		if(result > 0) {
			sqls.commit();
		} else {
			sqls.rollback();
		}
		
		sqls.close();
		
		return result > 0 ? true : false;
	}
	
	/* [5] 주문내역 조회하기 */
	public List<OrderHistoryDTO> selectOrderHistory(String range) {
		
		SqlSession sqls = getSqlSession();
		mapper = sqls.getMapper(FoodProductsMapper.class);
		List<OrderHistoryDTO> orderHistoryList = mapper.selectOrderHistory(range);
		
		sqls.close();
		
		return orderHistoryList;
	}
}
