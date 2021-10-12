package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class OrderPageSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String order_select_name = request.getParameter("order_select");
		
		ArrayList<Product> productList = null;
		
		ProductService productService = new ProductService();
		
		if(order_select_name.equalsIgnoreCase("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('종류가 선택되지 않았습니다');");
			out.print("history.back();");
			out.print("</script>");
		} else if (order_select_name.equalsIgnoreCase("ice_cream")) {
			productList = productService.getListIcecream();
			request.setAttribute("productList", productList);
			
		} else if (order_select_name.equalsIgnoreCase("ice_cake")) {
			productList = productService.getListIceCake();
			request.setAttribute("productList", productList);
			
		} else if (order_select_name.equalsIgnoreCase("beverage")) {
			productList = productService.getListBeverage();
			request.setAttribute("productList", productList);
			
		} else if (order_select_name.equalsIgnoreCase("coffee")) {
			productList = productService.getListCoffee();
			request.setAttribute("productList", productList);
			
		} else if (order_select_name.equalsIgnoreCase("dessert")) {
			productList = productService.getListDessert();
			request.setAttribute("productList", productList);
		}
		
		ActionForward forward = new ActionForward("orderPage.bk", false);
		
		return forward;
	}

}
