package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BaskeyCartListService;
import vo.ActionForward;
import vo.Cart;

public class BaskeyCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BaskeyCartListService baskeyCartListService = new BaskeyCartListService();

		ArrayList<Cart> cartList = baskeyCartListService.getCartList(request);

		ActionForward forward = null;
		
		int totalMoney = 0;
		int money = 0;

		if(cartList == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('장바구니가 비어있습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			for (int i = 0; i < cartList.size(); i++) {
				money = cartList.get(i).getPrice();
				totalMoney += money;
			}
	
			request.setAttribute("totalMoney", totalMoney);
			request.setAttribute("baskeyCartListService", baskeyCartListService);
	
			request.setAttribute("cartList", cartList);
			forward = new ActionForward("baskeyCartList.jsp", false);
			}
		return forward;
	}

}
