package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Product;

public class AdminProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String productCheckCode[] = request.getParameterValues("productCheck");
		
		if(productCheckCode == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('상품이 체크되지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		ProductService productService = new ProductService();
		
		ArrayList<Product> productList = productService.getProduct(productCheckCode);
		
		if(productList.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 리스트가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		ActionForward forward = null;
		for(Product products: productList) {
			boolean productDeleteSuccess = false;

			productDeleteSuccess = productService.productAllDelete(products);
			
			System.out.println("test");
			
			if(!productDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('삭제가 실패 되었습니다');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				forward = new ActionForward("adminProductListDeleteSuccess.bg", false);
			}
		}
		
		return forward;
	}

}
