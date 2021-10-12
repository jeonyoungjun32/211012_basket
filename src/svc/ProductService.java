package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.Cart;
import vo.IO;
import vo.Product;

public class ProductService {

	//IceCakeIngredientListAction
	// IcecreamListAction - 아이스크림
	public ArrayList<Product> getListIcecream() throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		ArrayList<Product> icecreamList = productDAO.getListIcecream();

		close(con);

		return icecreamList;
	}
	
	//IceCakeIngredientListAction
	//IceCakeListAction - 아이스케이크
	public ArrayList<Product> getListIceCake() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> iceCakeList = productDAO.getListIceCake();
		
		close(con);
		
		return iceCakeList;
	}

	//DessertIngredientListAction
	//DessertListAction - 디저트
	public ArrayList<Product> getListDessert() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> dessertList = productDAO.getListDessert();
		
		close(con);
		
		return dessertList;
	}

	//BeverageIngredientListAction
	//BeverageListAction - 음료
	public ArrayList<Product> getListBeverage() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> beverageList = productDAO.getListBeverage();
		
		close(con);
		
		return beverageList;
	}
	
	//CoffeeIngredientListAction
	//CoffeeListAction - 커피
	public ArrayList<Product> getListCoffee() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> coffeeList = productDAO.getListCoffee();
		
		close(con);
		
		return coffeeList;
	}

	// AdminProductInserProAction : 상품 등록
	public boolean insertProduct(Product product, String id) throws Exception {

		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int insertCount = productDAO.insertProduct(product, id);

		boolean insertSuccess = false;
		if (insertCount > 0) {
			commit(con);
			insertSuccess = true;
		} else {
			rollback(con);
		}
		close(con);

		return insertSuccess;
	}

	//AdminProductFormAction
	//AdminProductUpdateFormAction
	//IcecreamIngredientListAction
	//상품 코드로 상품 찾기
	public Product getProduct(int serial_code) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		Product product = productDAO.getFindCode(serial_code);

		close(con);

		return product;
	}
	
	// AdminProductDeleteProAction
	// AdminIOFormAction
	// 체크된 물건 찾기
	public ArrayList<Product> getProduct(String[] productCheckCode) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		ArrayList<Product> productList = productDAO.adminGetProduct(productCheckCode);

		close(con);

		return productList;
	}
	
	// AdminProductDeleteProAction - 상품 삭체 요청
	public boolean productAllDelete(Product products) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int productAllDeleteCount = productDAO.ProductAllDelete(products);

		boolean productDeleteSuccess = false;
		if (productAllDeleteCount > 0) {
			commit(con);
			productDeleteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);

		return productDeleteSuccess;
	}

	// AdminProductListFormAction
	public int getProductListCount(int intViewSelect) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int productListCount = productDAO.getProductListCount(intViewSelect);

		close(con);

		return productListCount;
	}
	
	//AdminProductListFormAction
	public int getAllProductListCount() throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int allListCount = productDAO.getAllListCount();

		close(con);

		return allListCount;
	}

	// AdminProductListFormAction
	public ArrayList<Product> getProductListSelect(int page, int intviewCount, int intViewSelect) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		ArrayList<Product> productList = productDAO.getProductListSelect(page,intviewCount,intViewSelect);

		close(con);
		
		return productList;
	}
	
	// AdminProductListFormAction
	public ArrayList<Product> getProductAllListSelect(int page, int intviewCount) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		ArrayList<Product> productList = productDAO.getProductAllListSelect(page,intviewCount);
		
		close(con);

		return productList;
	}

	// OrderPageSelectDetailAction
	// 주문에서 선택한 상품을 찾아서 상세내역에 돌려주기
	public ArrayList<Product> getListProduct(int[] intCode) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		ArrayList<Product> productList = productDAO.getProductListDetail(intCode);

		close(con);

		return productList;
	}
	
	// AdminProductUpdateProAction
	public boolean getProductUpdate(int serial_code, String name, String allergy, int kcal, int price) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int productUpdateCount = productDAO.productUpdate(serial_code, name, allergy, kcal, price);

		boolean productUpdateSuccess = false;
		if (productUpdateCount > 0) {
			productUpdateSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return productUpdateSuccess;
	}
	
	// AdminIOProAction
	public boolean adminIOUpdate(int[] intSerial_code, int[] product_count) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int IOValue = productDAO.AdminIOinsert(intSerial_code, product_count);

		boolean IOSuccess = false;
		if (IOValue > 0) {
			commit(con);
			IOSuccess = true;
		} else {
			rollback(con);
		}
		close(con);

		return IOSuccess;
	}
	
	// AdminIOListProAction
	public int getIOListCount() throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int IOListCount = productDAO.IOListCount();

		close(con);

		return IOListCount;
	}

	// AdminIOListProAction
	public ArrayList<IO> getIOList(int page, int intviewCount) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		ArrayList<IO> ioList = productDAO.IOList(page, intviewCount);

		close(con);

		return ioList;
	}

	//AdminIOListProAction
	public int getIOSelectListCount(String iO_select) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);
		
		int IOListCount = productDAO.selectIOListCount(iO_select);

		close(con);
		
		return IOListCount;
	}
	
	//AdminIOListProAction
	public ArrayList<IO> getIOChoiceList(int page, int intviewCount, String iO_select) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);
		
		ArrayList<IO> ioList = productDAO.selectIOList(page,intviewCount,iO_select);
		
		close(con);

		return ioList;
	}

	//AdminOrderCancelProAction
	//order_code_name으로 상품 정보 얻기
	public Product getProductInfo(String order_code_name) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);
		
		Product product = productDAO.getProductInfo(order_code_name);
		
		close(con);
		
		return product;
	}

	//OrderResultProAction
	public ArrayList<Product> getProduct(ArrayList<Cart> cartList) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);
		
		ArrayList<Product> productList = productDAO.getProductInfo(cartList);
		
		close(con);
		
		return productList;
	}

	//AdminProductInserProAction
	public String getProduct(String name) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);
		
		String resultName = productDAO.getProductConfirmInfo(name);
		
		close(con);
		
		return resultName;
	}
	
}
