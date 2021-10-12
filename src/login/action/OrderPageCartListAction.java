package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderService;
import vo.ActionForward;
import vo.Cart;

public class OrderPageCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OrderService orderService = new OrderService();
		
		ArrayList<Cart> cartList = orderService.getCartList(request);
		
		ActionForward forward = new ActionForward("orderResult.bk", false);
		
		return forward;
	}

}
