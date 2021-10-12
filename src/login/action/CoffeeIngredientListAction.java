package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class CoffeeIngredientListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Product> coffeeList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		coffeeList = productService.getListCoffee();
		
		ActionForward forward = null;
		
		request.setAttribute("coffeeList", coffeeList);
		forward = new ActionForward();
		forward.setPath("coffeeIngredientList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
