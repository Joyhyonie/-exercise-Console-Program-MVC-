package com.joyhyonie.foodProducts.mapper;

import java.util.List;
import java.util.Map;

import com.joyhyonie.common.SearchCriteria;
import com.joyhyonie.foodProducts.model.dto.FoodProductsDTO;
import com.joyhyonie.foodProducts.model.dto.OrderHistoryDTO;
import com.joyhyonie.foodProducts.model.dto.StorageDTO;
import com.joyhyonie.foodProducts.model.dto.SupplierDTO;

public interface FoodProductsMapper {

	/* [1-1] 오늘의 재료 추천받기 */
	List<FoodProductsDTO> selectRandomProducts(Map<String, List<Integer>> criteria);

	/* [1-2] 유통업체 선택하기 */
	List<FoodProductsDTO> selectProductsBySupplier(SearchCriteria searchCriteria);

	/* [1-3A] 전체 메뉴 조회하여 리스트에 담기 */
	List<FoodProductsDTO> selectAllProducts();

	/* [1-3B] 주문한 내역 저장하기 */
	int insertOrderedProducts(OrderHistoryDTO order);

	/* [1-3C] 주문한 재료 저장고에 추가하기 */
	int insertProductsToStorage(StorageDTO storageDTO);

	/* [2-1] 재료 파악하기 */
	List<StorageDTO> selectProductsBySomething(SearchCriteria searchCriteria);

	/* [2-2] 재료 사용하기 */
	int deletePickedProduct(String prodName);

	/* [2-3] 상한 재료 교환받기 */
	int updatePickedProduct(String prodName);

	/* [2-4] 오래된 재료 모두 폐기하기 */
	int deleteOldProducts(int days);

	/* [3] 원하는 재료 판매 제안하기 */
	int insertNewProduct(FoodProductsDTO foodProducts);

	/* [4-1] 모든 업체 조회하기 */
	List<SupplierDTO> selectAllSupplier();

	/* [4-2] 정보 변경하기 */
	int updateSupplierInfo(SupplierDTO supplier);

	/* [4-3] 계약 해지하기 */
	int deleteSupplier(String suppName);

	/* [5] 주문내역 조회하기 */
	List<OrderHistoryDTO> selectOrderHistory(String range);

}
