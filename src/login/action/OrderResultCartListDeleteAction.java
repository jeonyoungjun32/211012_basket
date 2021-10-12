package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OrderService;
import vo.ActionForward;

public class OrderResultCartListDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ListDelete[] =  request.getParameterValues("ListDelete");
		
		OrderService orderService = new OrderService();
		ActionForward forward = null;
		if (ListDelete != null) {
		orderService.cartRemove(request,ListDelete);
		forward = new ActionForward("orderResult.bk", false);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 상품이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		return forward;
	}

}
