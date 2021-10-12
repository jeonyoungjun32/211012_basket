package svc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MyPageDAO;
import dao.ProductDAO;
import oracle.net.aso.s;

import static db.Jdbcutil.*;

import vo.Cart;
import vo.Member;
import vo.MemberOrder;
import vo.Product;

public class OrderService {

	// OrderResultProAction
	public boolean insertOrder(ArrayList<Product> productList, int[] Count, Member member, String howchoice,
			int resultMoney, int resultPoint) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int insertCount = productDAO.insertOrder(productList, Count, member, howchoice);

		int ReturnValue = productDAO.insertIO_Out(productList, Count);

		int moneyUpdateCount = productDAO.moneyUpdateMember(member, resultMoney, resultPoint);

		boolean insertSuccess = false;

		if (insertCount > 0) {
			if (ReturnValue > 0) {
				if (moneyUpdateCount > 0) {
					commit(con);
					insertSuccess = true;
				} else {
					rollback(con);
				}
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);

		return insertSuccess;
	}

	// AdminOrderProAction
	// 회원 전체 조회
	public ArrayList<MemberOrder> allListOrder(int page, int intviewCount) throws Exception {
		Connection con = getConnection();

		MyPageDAO myPageDAO = MyPageDAO.getInstance();

		myPageDAO.setConnection(con);

		ArrayList<MemberOrder> memberAllOrderList = myPageDAO.allListOrder(page,intviewCount);

		close(con);

		return memberAllOrderList;
	}
	
	// AdminOrderProAction
	public int allLIstOrderCount() throws Exception {
		Connection con = getConnection();

		MyPageDAO myPageDAO = MyPageDAO.getInstance();

		myPageDAO.setConnection(con);
		
		int allListOrderCount = myPageDAO.allListOrderCount();
		
		close(con);
		
		return allListOrderCount;
	}

	//OrderResultPointProAction
	public boolean insertPointOrder(ArrayList<Product> productList, int[] count, Member member, String howchoice,
			int resultMoney, int resultPoint) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int insertCount = productDAO.insertOrder(productList, count, member, howchoice);

		int ReturnValue = productDAO.insertIO_Out(productList, count);

		int moneyUpdateCount = productDAO.moneyAndPointUpdateMember(member, resultMoney, resultPoint);

		boolean insertSuccess = false;

		if (insertCount > 0) {
			if (ReturnValue > 0) {
				if (moneyUpdateCount > 0) {
					commit(con);
					insertSuccess = true;
				} else {
					rollback(con);
				}
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);

		return insertSuccess;
	}

	//AdminOneMemberOrderProAction
	//회원 개인 조회
	public ArrayList<MemberOrder> searchIDListOrder(int page, int intviewCount, String searchID) throws Exception {
		
		Connection con = getConnection();

		MyPageDAO myPageDAO = MyPageDAO.getInstance();

		myPageDAO.setConnection(con);

		ArrayList<MemberOrder> memberSearchIDOrderList = myPageDAO.searchIDListOrder(page, intviewCount, searchID);

		close(con);

		return memberSearchIDOrderList;
		
	}

	//AdminOneMemberOrderProAction
	//회원 검색을 통한 개인 조회 카운터
	public int searchIDListOrderCount(String searchID) throws Exception {
		Connection con = getConnection();

		MyPageDAO myPageDAO = MyPageDAO.getInstance();

		myPageDAO.setConnection(con);

		int searchIDListOrderCount = myPageDAO.searchIDListOrderCount(searchID);

		close(con);

		return searchIDListOrderCount;
	}

	//AdminOrderCancelProAction
	public boolean refundOrder(String order_code, Member member, Product product, int price, int count) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		/*주문 내역 삭제*/
		int deleteCount = productDAO.deleteOrder(order_code);

		/*상품 환불요청으로인해 감소된 상품 다시 수량 계산*/
		int ReturnValue = productDAO.deleteIO_Out(product, count);
		
		/*돈 계산 파트*/
		int moneyRefundCount = productDAO.moneyRefundMember(member, price);

		boolean refundSuccess = false;

		if (deleteCount > 0) {
			if (ReturnValue > 0) {
				if (moneyRefundCount > 0) {
					commit(con);
					refundSuccess = true;
				} else {
					rollback(con);
				}
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);

		return refundSuccess;
	}

	//OrderPageSelectDetailAction
	public void addCartList(HttpServletRequest request, ArrayList<Product> productList) throws Exception {
		HttpSession session = request.getSession();
		
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		boolean isNewCart = true;
		
		
		for(int i =0; i<cartList.size(); i++) {
			for(int j =0; j<productList.size(); j++) {
				if(cartList.get(i).getName().equals(productList.get(j).getName())) {
					isNewCart = false;
					break;
				}			
			}
		}
		
		for(int i =0; i<productList.size(); i++) {
			if(isNewCart == true) {
				Cart cart = new Cart();
				
				cart.setSerial_code(productList.get(i).getSerial_code());
				cart.setName(productList.get(i).getName());
				cart.setCount(productList.get(i).getCount());
				cart.setPrice(productList.get(i).getPrice());
				cart.setKinds(productList.get(i).getChoice());
				
				cartList.add(cart);
			}
		}
		
	}

	/*session에 있는 장바구니 가져옴*/
	//OrderPageCartListAction
	public ArrayList<Cart> getCartList(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList =(ArrayList<Cart>)session.getAttribute("cartList");
		
		return cartList;
	}

	/*session에 있는 장바구니 삭제*/
	//OrderResultCartListDeleteAction
	public void cartRemove(HttpServletRequest request, String[] listDelete) throws Exception {
		HttpSession session = request.getSession();
		
		ArrayList<Cart> cartList =(ArrayList<Cart>)session.getAttribute("cartList");
		
		for(int i =0; i<listDelete.length; i++) {
			for(int j =0; j<cartList.size(); j++) {
				
				if(cartList.get(j).getName().equals(listDelete[i])) {
					cartList.remove(cartList.get(j));
				}
			}
		}
	}

}
