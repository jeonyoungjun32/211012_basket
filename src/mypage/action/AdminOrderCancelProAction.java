package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import svc.MemberMoneyAndPointService;
import svc.OrderService;
import svc.ProductService;
import vo.ActionForward;
import vo.Member;
import vo.Product;

public class AdminOrderCancelProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String order_code = request.getParameter("order_code");
		String order_code_name = request.getParameter("order_code_name");
		String get_id = request.getParameter("id");
		int price = Integer.parseInt(request.getParameter("price"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		HttpSession session = request.getSession();
		
		MemberInfoService memberInfoService = new MemberInfoService();
		
		/*주문 취소할 회원 정보 얻기*/
		Member member = memberInfoService.getMember(get_id);
		
		/*주문 취소할 상품 얻기*/
		ProductService productService = new ProductService();
		
		Product product = productService.getProductInfo(order_code_name);
		
		OrderService orderService = new OrderService();
		
		boolean refundSuccess = orderService.refundOrder(order_code,member,product,price,count);
		
		ActionForward forward = null;

		/*정보 새로고침*/
		MemberMoneyAndPointService memberMoneyAndPointService = new MemberMoneyAndPointService();
		
		Member updateMember = memberMoneyAndPointService.setMember(member.getId());
		
		if(refundSuccess) {
			session.setAttribute("member", updateMember);
			forward = new ActionForward("refundSuccess.jsp", false);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('주문취소가 실패했습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		return forward;
	}

}
