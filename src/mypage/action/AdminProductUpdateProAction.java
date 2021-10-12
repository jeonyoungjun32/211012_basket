package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;

public class AdminProductUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int serial_code = Integer.parseInt(request.getParameter("serial_code"));
		
		String name = request.getParameter("name");
		int kcal = Integer.parseInt(request.getParameter("kcal"));
		String allergy = request.getParameter("allergy");
		int price = Integer.parseInt(request.getParameter("price"));

		ProductService productService = new ProductService();

		boolean productUpdateSuccess = productService.getProductUpdate(serial_code, name, allergy, kcal, price);

		ActionForward forward = null;
		
		if (!productUpdateSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('상품 정보가 수정되지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			request.setAttribute("product_name", name);
			forward = new ActionForward("mypage/adminProductUpdateSuccess.jsp", false);
		}

		return forward;
	}

}
