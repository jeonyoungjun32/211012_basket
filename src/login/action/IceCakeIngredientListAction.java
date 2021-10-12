package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class IceCakeIngredientListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<Product> iceCakeList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		iceCakeList = productService.getListIceCake();
		
		ActionForward forward = null;
		
		request.setAttribute("iceCakeList", iceCakeList);
		forward = new ActionForward();
		forward.setPath("iceCakeIngredientList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
