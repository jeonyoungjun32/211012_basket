package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProductService;
import vo.ActionForward;
import vo.Member;
import vo.Product;

public class AdminProductInserProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String product_choice = request.getParameter("choice");
		
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("member");
		
		String id = member.getId();
		
		ActionForward forward = null;
		
		if(product_choice == "" || product_choice == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('물건 등록 종류를 선택하지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		String name = request.getParameter("name");
		
		ProductService productService = new ProductService();
		/*등록 이름이 동일한게 있는지 찾기*/
		String resultName = productService.getProduct(name);
		
		if(resultName != null && resultName != "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('동일한 이름의 상품이 있습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			
			Product product = new Product();
			
			product.setName(name);
			product.setKcal(Integer.parseInt(request.getParameter("kcal")));
			product.setAllergy(request.getParameter("allergy"));
			product.setPrice(Integer.parseInt(request.getParameter("price")));
			product.setChoice(product_choice);
			
			boolean insertSuccess = productService.insertProduct(product,id);
			
			if(!insertSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('등록실패');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("adminProductInsertSuccessForm.bg");
			}
		}
		return forward;
	}

}
