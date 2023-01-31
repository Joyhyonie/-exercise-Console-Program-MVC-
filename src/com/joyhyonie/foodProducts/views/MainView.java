package com.joyhyonie.foodProducts.views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.joyhyonie.common.SearchCriteria;
import com.joyhyonie.foodProducts.controller.FoodProductsController;
import com.joyhyonie.foodProducts.model.dto.FoodProductsDTO;
import com.joyhyonie.foodProducts.model.dto.OrderHistoryDTO;
import com.joyhyonie.foodProducts.model.dto.OrderProductsDTO;
import com.joyhyonie.foodProducts.model.dto.StorageDTO;

public class MainView {
	
	private static Scanner sc = new Scanner(System.in);
	private static FoodProductsController fpController = new FoodProductsController();

	public void displayView() {

		do {
			System.out.println("");
			System.out.println("=====[ 재료 주문 및 관리 프로그램 ]=====");
			System.out.println("[A] 재료 주문하기");
			System.out.println("[B] 재료 관리하기");
			System.out.println("[C] 원하는 재료 판매 제안하기");
			System.out.println("[D] 업체 관리하기");
			System.out.println("[E] 주문내역 조회하기");
			System.out.println("[Z] 프로그램 종료하기");
			System.out.print("메뉴 선택 : ");
			char ch = sc.nextLine().toUpperCase().charAt(0);
			
			switch(ch) {
			case 'A': orderNewProduct(); break;
			case 'B': manageStorage(); break; 
			case 'C': suggestNewProduct(); break;
			case 'D': manageSupplier(); break;
			case 'E': fpController.selectOrderHistory(manageOrderHistory()); break;
			case 'Z': System.out.println("프로그램이 종료되었습니다 :D"); return;
			default : System.out.println("잘못 된 번호입니다! 다시 입력해주세요 :(");
			}
			
		} while(true);	
	}

	/* [1] 재료 주문하기 */
	private static void orderNewProduct() {
		
		do {
			System.out.println("");
			System.out.println("=====[ 재료 주문하기 ]=====");
			System.out.println("[A] 오늘의 재료 먼저 추천받기");
			System.out.println("[B] 각 업체마다의 재료 조회하기");
			System.out.println("[C] 주문 시작하기");
			System.out.println("[Z] ◀ 돌아가기");
			System.out.print("메뉴 선택 : ");
			char ch = sc.nextLine().toUpperCase().charAt(0);
			System.out.println("잠시만 기다려주세요 :)");
			
			switch(ch) {
			case 'A' : fpController.selectRandomProducts(pickRandomProducts()); break;
			case 'B' : fpController.selectProductsBySupplier(pickSupplier()); break;
			case 'C' : fpController.insertOrderedProducts(OrderedProducts()); break;
			case 'Z' : return;
			}
			
		} while(true);
	}

	/* [1-1] 오늘의 재료 추천받기 */
	private static List<Integer> pickRandomProducts() {
		
		Set<Integer> set = new HashSet<>();
		while(set.size() < 3) {
			int temp = ((int) (Math.random() * 50)) + 100;
			set.add(temp);
		}
		
		List<Integer> list = new ArrayList<>(set); 
		
		return list;
	}

	/* [1-2] 각 업체마다의 재료 조회하기 */
	private static SearchCriteria pickSupplier() {
		
		System.out.println("");
		System.out.println("=====[ 업체 선택하기 ]=====");
		System.out.println("[A] 굽네정육");
		System.out.println("[B] 바다회사랑");
		System.out.println("[C] SING싱채소");
		System.out.println("[D] 베지상회");
		System.out.println("[E] 치즈FARM");
		System.out.println("[F] 얼음땡푸드");
		System.out.println("[G] 짱구는말려");
		System.out.println("[H] 꼬순내방앗간");
		System.out.println("[I] EGG머니나");
		System.out.print("메뉴 선택 : ");
		String value = sc.nextLine().toUpperCase();

		return new SearchCriteria(value);
	}
	
	/* [1-3] 주문 시작하기 */
	private static OrderHistoryDTO OrderedProducts() {
		
		/* [1-3A] 전체 재료 조회하여 리스트에 담기 */
		List<FoodProductsDTO> allProductsList = fpController.selectAllProducts();
		
		/* 주문들을 담기 위한 리스트 */
		List<OrderProductsDTO> orderProductsList = new ArrayList<>(); 
		int totalPrice = 0; 
		
		/* 주문한 재료들 담기용 */
		List<StorageDTO> productsToStorageList = new ArrayList<>();
		
		do {
			System.out.println("");
			System.out.println("=====[ 주문중... ]=====");
			System.out.print("주문할 재료명 입력 : ");
			
			String prodName = sc.nextLine();
			
			int prodCode = 0;
			int prodPrice = 0;
			String suppName = null;
			for(FoodProductsDTO prod : allProductsList) {
				if(prod.getProdName().equals(prodName)) {
					prodCode = prod.getProdCode();
					prodPrice = prod.getPrice();
					suppName = prod.getSupplierName();
				} 
			}

			/* 주문한 재료(들) 저장 */
			OrderProductsDTO orderProducts = new OrderProductsDTO();
			orderProducts.setProdName(prodName);
			orderProducts.setProdCode(prodCode);
			orderProducts.setPrice(prodPrice);
			orderProducts.setSupplierName(suppName);
			
			orderProductsList.add(orderProducts); // orderProductsList에 주문한 것들 추가
			totalPrice += prodPrice; // 총액 계산
			
			System.out.print("계속 주문하시겠어요? :)\n(Y/N) : ");
			boolean isContinue = sc.nextLine().toUpperCase().equals("Y");
			
			/* [1-3C] 주문한 재료 저장고에 추가하기 */
			java.util.Date orderDate = new java.util.Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
			String incomingDate = dateFormat.format(orderDate);
			
			StorageDTO storage = new StorageDTO();
			storage.setProdCode(prodCode);
			storage.setProdName(prodName);
			storage.setIncomingDate(incomingDate);
			productsToStorageList.add(storage);
			
			if(!isContinue) break;
			
		} while(true);
		
//		fpController.insertProductsToStorage(productsToStorageList);
//		재료를 추가하는 것은 주문내역과 함께 INSERT 되어야함
		
		for(OrderProductsDTO orderMenu : orderProductsList) {
			System.out.println(orderMenu);
			
		}
		System.out.println("-----------------------\n총액 : " + totalPrice + "원");
		
		/* [1-3B] 주문한 내역 저장하기 */
		java.util.Date orderTime = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd"); 
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String date = dateFormat.format(orderTime);
		String time = timeFormat.format(orderTime);
		
		OrderHistoryDTO order = new OrderHistoryDTO();
		order.setOrderDate(date);
		order.setOrderTime(time);
		order.setTotalPrice(totalPrice);
		order.setStorageDTO(productsToStorageList); 
		/* 주문내역과 주문한 재료를 하나의 트랜잭션 안에서 INSERT하기 위해 (둘 중에 하나라도 문제가 있다면, 나머지 하나도 작동되어선 안됨)
		 * StorageDTO를 OrderHistoryDTO의 필드에 선언한 뒤,
		 * productsToStorageList를 StorageDTO에 set하여 함께 인자로 전달 */
		
		return order;
	}

	/*--------------------------------------------------------------------------*/
	
	/* [2] 재료 관리하기 */
	private static void manageStorage() {
		
		do {
			System.out.println("");
			System.out.println("=====[ 재료 관리하기 ]=====");
			System.out.println("[A] 재료 파악하기");
			System.out.println("[B] 재료 사용하기");
			System.out.println("[C] 상한 재료 교환받기");
			System.out.println("[D] 오래된 재료 모두 폐기하기");			
			System.out.println("[Z] ◀ 돌아가기");
			System.out.print("메뉴 선택 : ");
			char ch = sc.nextLine().toUpperCase().charAt(0);
			System.out.println("잠시만 기다려주세요 :)");
			
			switch(ch) {
			case 'A' : fpController.selectProductsBySomething(inputSearchCriteria()); break;
			case 'B' : fpController.deletePickedProduct(useProduct()); break;
			case 'C' : fpController.updatePickedProduct(changeProduct()); break;
			case 'D' : fpController.deleteOldProducts(throwAwayOldProducts()); break;
			case 'Z' : return;
			}
			
		} while(true);
	}

	/* [2-1] 재료 파악하기 */
	private static SearchCriteria inputSearchCriteria() {
		
		System.out.println("");
		System.out.println("=====[ 재료 파악하기 ]=====");
		System.out.println("|   전체   |   재료명별   |");
		System.out.print("재료 목록 기준 선택 : ");
		String condition = sc.nextLine();
		
		String value = null;
		if(condition.equals("재료명별")) {
			System.out.print("재료명 입력 : ");
			value = sc.nextLine();
		}
		
		System.out.println("잠시만 기다려주세요 :)");
		
		return new SearchCriteria(condition, value);
	}
	
	/* [2-2] 재료 사용하기 */
	private static Map<String, String> useProduct() {
		
		System.out.println("");
		System.out.println("=====[ 재료 사용하기 ]====="); 
		System.out.print("사용할 재료명 입력 : ");
		String prodName = sc.nextLine();
		System.out.println("잠시만 기다려주세요 :)");
		
		Map<String, String> param = new HashMap<>();
		param.put("prodName", prodName);
		
		return param;
	}
	
	/* [2-3] 상한 재료 교환받기 */
	private static Map<String, String> changeProduct() {
		
		System.out.println("");
		System.out.println("=====[ 상한 재료 교환받기 ]====="); 
		System.out.print("상한 재료명 입력 : ");
		String prodName = sc.nextLine();
		System.out.println("잠시만 기다려주세요 :)");
		
		Map<String, String> param = new HashMap<>();
		param.put("prodName", prodName);
		
		return param;
	}

	/* [2-4] 오래된 재료 모두 폐기하기 */
	private static Map<String, String> throwAwayOldProducts() {
		
		System.out.println("");
		System.out.println("=====[ 오래된 재료 모두 폐기하기 ]====="); 
		System.out.println("며칠 이상 지난 재료를 폐기하시겠습니까?");
		System.out.print("일수 입력 : ");
		String days = sc.nextLine();
		System.out.println("잠시만 기다려주세요 :)");
		
		Map<String, String> param = new HashMap<>();
		param.put("days", days);
		
		return param;
	}
	
	/*--------------------------------------------------------------------------*/
	
	/* [3] 원하는 재료 판매 제안하기 */
	private static void suggestNewProduct() {
		
		System.out.println("");
		System.out.println("=====[ 원하는 재료 판매 제안하기 ]=====");
		System.out.print("판매를 제안할 업체명을 입력해주세요 :)\n : ");
		String supplier = sc.nextLine();
		System.out.print("원하는 재료를 입력해주세요 :)\n : ");
		String product = sc.nextLine();
		System.out.print("원하는 가격을 제시해주세요 :)\n : ");
		String price = sc.nextLine();
		System.out.println("잠시만 기다려주세요 :)");
			
		int intPrice = Integer.parseInt(price);
		if(intPrice > 5000) {
			Map<String, String> param = new HashMap<>();
			param.put("supplier", supplier);
			param.put("product", product);
			param.put("price", price);
				
			fpController.insertNewProduct(param);
				
		} else {
			System.out.println("제시하신 금액이 너무 적어 업체가 거절했습니다 :(");
		}
		
	}
	
	/*--------------------------------------------------------------------------*/
	
	/* [4] 업체 관리하기 */
	private static void manageSupplier() {

		do {
			System.out.println("");
			System.out.println("=====[ 업체 관리하기 ]=====");
			System.out.println("[A] 모든 업체 조회하기");
			System.out.println("[B] 정보 변경하기"); 
			System.out.println("[C] 계약 해지하기");		
			System.out.println("[Z] ◀ 돌아가기");
			System.out.print("메뉴 선택 : ");
			char ch = sc.nextLine().toUpperCase().charAt(0);
			System.out.println("잠시만 기다려주세요 :)");
			
			switch(ch) {
			case 'A' : fpController.selectAllSupplier(); break;
			case 'B' : fpController.updateSupplierInfo(modifySupplier()); break;
			case 'C' : System.out.println("------------[WARNING]------------\n계약을 해지할 시, 재계약은 불가능합니다. 계속 진행하시겠습니까?\n(해당 업체의 재료 또한 주문불가)");
					   System.out.print("(Y/N) : ");
					   char yn = sc.nextLine().toUpperCase().charAt(0);
					   if(yn == 'N') {
						   return;
					   } else
					   System.out.println("안전한 계약 해지를 위해 아래의 문자를 알맞은 순서로 입력해주세요 :)");
					   System.out.print("[ ofdo ]\n : ");
					   String word = sc.nextLine();
					   if(word.equals("food")) {
						   fpController.deleteSupplier(cancelSupplier()); break;
					   } else {
						   System.out.println("문자의 순서가 올바르지 않아요 :( 메인 페이지로 이동합니다 !"); return;
					   }
			case 'Z' : return;
			}
			
		} while(true);
		
	}
	/* [4-2] 정보 변경하기 */
	private static Map<String, String> modifySupplier() {
		
		System.out.println("");
		System.out.println("=====[ 정보 변경하기 ]====="); 
		System.out.print("변경할 업체명 입력 : ");
		String suppName = sc.nextLine();
		System.out.println("*변경하고자 하는 정보만 입력하셔도 됩니다 !* ▶ ENTER");
		sc.nextLine();
		System.out.print("새로운 주소 입력 : ");
		String newAddress = sc.nextLine();
		System.out.print("새로운 연락처 입력 : ");
		String newContactNumber = sc.nextLine();
		System.out.print("거래가능여부 입력 : ");
		String newOrderYn = sc.nextLine();
		System.out.println("잠시만 기다려주세요 :)");
		
		Map<String, String> param = new HashMap<>();
		param.put("suppName", suppName);
		param.put("newAddress", newAddress);
		param.put("newContactNumber", newContactNumber);
		param.put("newOrderYn", newOrderYn);
		
		return param;
	}

	/* [4-3] 계약 해지하기 */
	private static Map<String, String> cancelSupplier() {
		
		System.out.println("");
		System.out.println("=====[ 계약 해지하기 ]====="); 
		System.out.print("업체명 입력 : ");
		String suppName = sc.nextLine();
		System.out.println("잠시만 기다려주세요 :)");
		
		Map<String, String> param = new HashMap<>();
		param.put("suppName", suppName);
		
		return param;
	}

	/*--------------------------------------------------------------------------*/
	
	/* [5] 주문내역 조회하기 */
	private static Map<String, String> manageOrderHistory() {

			System.out.println("");
			System.out.println("=====[ 주문내역 조회하기 ]=====");
			System.out.println("|    최신순    |    과거순    |");
			System.out.print(" : ");
			String range = sc.nextLine();
			System.out.println("잠시만 기다려주세요 :)");
			
			Map<String, String> param = new HashMap<>();
			param.put("range", range);
			
			return param;
	}
}
