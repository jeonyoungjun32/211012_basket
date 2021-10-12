package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberMoneyAndPointService;
import svc.OrderService;
import svc.ProductService;
import vo.ActionForward;
import vo.Cart;
import vo.Member;
import vo.Product;

public class OrderResultProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		OrderService orderService = new OrderService();
		
		String count[] = request.getParameterValues("count");
		
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		for(Cart test : cartList) {
			System.out.println(test.getName());
		}
		
		ProductService productService = new ProductService();
		
		ArrayList<Product> productList = productService.getProduct(cartList);
		
		String inOut_how = request.getParameter("how");
		
		if(count == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('구매하는 제품의 수량을 입력해주세요.');");
			out.print("history.back();");
			out.print("</script>");
		} else if(cartList == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('구매하는 제품이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		ActionForward forward = null;
		int i=0;
		for(i=0; i<productList.size(); i++) {
			i++;
		}
		
		boolean resultSuccess = true;
		int Count[] = new int[i];
		int resultMoney = 0;
		for(i=0; i<productList.size(); i++) {
			if(count[i].equals("")) {
				forward = new ActionForward("orderPageCountFail.jsp", false);
				resultSuccess= false;
				break;
			} else {
				try {
					Count[i] = Integer.parseInt(count[i]);
				}catch (Exception e) {
					forward = new ActionForward("orderPageCountStrFail.jsp", false);
					resultSuccess= false;
					break;
				}
				if(Count[i] <0) {
					forward = new ActionForward("orderPageCountFail.jsp", false);
					resultSuccess= false;
					break;
				}
				if(Count[i] >productList.get(i).getCount()) {
					forward = new ActionForward("orderPageCountFail.jsp", false);
					resultSuccess= false;
					break;
				}
			}
		}
		
		if(resultSuccess) {
			for(i=0; i<productList.size(); i++) {
				resultMoney += Count[i]*productList.get(i).getPrice();
			}
			int resultPoint = 0;
			resultPoint = resultMoney/100;
			
			String Howchoice = "";
			boolean insertSuccess = false;
			if(inOut_how.equalsIgnoreCase("")) {
				resultSuccess= false;
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('주문방법을 선택해주세요.');");
				out.print("history.back();");
				out.print("</script>");
			} else if ((member.getMoney() - resultMoney) < 0){
				forward = new ActionForward("orderPageMoneyFail.jsp", false);
				resultSuccess= false;
			} else {
				
				if(inOut_how.equalsIgnoreCase("listIN")) {
					Howchoice = "1";
				} else if (inOut_how.equalsIgnoreCase("listOut")) {
					Howchoice = "2";
				}
				insertSuccess = orderService.insertOrder(productList,Count,member,Howchoice,resultMoney,resultPoint);
				
			}
			MemberMoneyAndPointService memberMoneyAndPointService = new MemberMoneyAndPointService();
			
			member = memberMoneyAndPointService.setMember(member.getId());
			
			if(resultSuccess) {
				if(insertSuccess) {
					session.setAttribute("member", member);
					session.removeAttribute("cartList");
					forward = new ActionForward("orderSuccess.jsp", true);
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('주문 실패했습니다.');");
					out.print("history.back();");
					out.print("</script>");
				}
			}		
		}
		return forward;
	}

}
