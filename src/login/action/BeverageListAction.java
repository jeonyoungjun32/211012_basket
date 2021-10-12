package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class BeverageListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Product> beverageList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		beverageList = productService.getListBeverage();
		
		ActionForward forward = null;
		
		request.setAttribute("beverageList", beverageList);
		forward = new ActionForward();
		forward.setPath("beverageList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
