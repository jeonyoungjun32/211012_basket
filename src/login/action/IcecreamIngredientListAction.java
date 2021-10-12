package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class IcecreamIngredientListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String serial_code =request.getParameter("serial_code");
		
		ActionForward forward = null;
		
		if (serial_code != null) {
			
		ProductService productService = new ProductService();
		
		int intSerial_code = Integer.parseInt(serial_code);
		
		Product product = productService.getProduct(intSerial_code);
		
		request.setAttribute("product", product);
		}
		
		ArrayList<Product> icecreamList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		icecreamList = productService.getListIcecream();
		
		request.setAttribute("icecreamList", icecreamList);
		forward = new ActionForward();
		forward.setPath("icecreamIngredientList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
