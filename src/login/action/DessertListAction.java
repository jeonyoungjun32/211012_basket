package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class DessertListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Product> dessertList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		dessertList = productService.getListDessert();
		
		ActionForward forward = null;
		
		request.setAttribute("dessertList", dessertList);
		forward = new ActionForward();
		forward.setPath("dessertList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
