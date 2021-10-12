package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class AdminProductUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int serial_code = Integer.parseInt(request.getParameter("serial_code"));
		
		ProductService productService = new ProductService();
		
		Product product = productService.getProduct(serial_code);
		
		request.setAttribute("product", product);
		
		ActionForward forward = new ActionForward("mypage/adminProductUpdateForm.jsp", false);
		
		return forward;
	}

}
