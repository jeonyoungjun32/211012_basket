package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BaskeyCartRemoveService;
import vo.ActionForward;

public class BaskeyCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cartDelete = request.getParameter("serial_code");
		ActionForward forword = null;

		BaskeyCartRemoveService cartDeleteService = new BaskeyCartRemoveService();

		if (cartDelete != null) {
			cartDeleteService.cartRemove(request, cartDelete);
			forword = new ActionForward("baskeyCartList.bk", true);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 상품이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}

		return forword;
	}

}
