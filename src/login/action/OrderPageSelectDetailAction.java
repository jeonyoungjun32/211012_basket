package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OrderService;
import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class OrderPageSelectDetailAction implements Action {

	/*수정중*/
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String order_Code[] = request.getParameterValues("orderCheck");
		ActionForward forward = null;

		int i =0;
		
		if (order_Code == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('구입할 상품을 체크해주세요');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		int count=0;
		for(i=0; i<order_Code.length; i++) {
			count++;
		}
			
		int IntCode[] = new int[count];
		for(i =0; i<order_Code.length; i++) {
			IntCode[i] =Integer.parseInt(order_Code[i]);		
		}
			
		ProductService productService = new ProductService();
		/*체크한 물건 코드 정보 담음*/
		ArrayList<Product> productList = productService.getListProduct(IntCode);
		
		request.setAttribute("productList", productList);
		
		/*장바구니에 담는 서비스*/
		OrderService orderService = new OrderService();
				
		orderService.addCartList(request,productList);
		
		forward = new ActionForward("orderPageCartList.bk", true);
		
		return forward;
	}

}
