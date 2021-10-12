package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class IcecreamListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<Product> icecreamList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		icecreamList = productService.getListIcecream();
		
		ActionForward forward = null;
		
		request.setAttribute("icecreamList", icecreamList);
		forward = new ActionForward();
		forward.setPath("icecreamList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
