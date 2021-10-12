package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProductService;
import vo.ActionForward;
import vo.Member;
import vo.Product;
import vo.ProductListPageInfo;

public class AdminProductListFormAction implements Action {
	
	/*수정 중*/
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Product> productList = new ArrayList<Product>();
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		ProductService productService = new ProductService();
		
		String viewSelect = request.getParameter("Product_Select");
		int IntViewSelect =0;
		int choice = 0;
		if(viewSelect == null || viewSelect.equals("all")) {
			choice= 0;
		} else if (viewSelect.equals("icecake")) {
			choice =1;
			IntViewSelect=1;
		} else if (viewSelect.equals("beverage")) {
			choice =1;
			IntViewSelect=2;
		} else if (viewSelect.equals("coffee")) {
			choice =1;
			IntViewSelect=3;
		} else if (viewSelect.equals("dessert")) {
			choice =1;
			IntViewSelect=4;
		} else if (viewSelect.equals("icecream")) {
			choice =1;
			IntViewSelect=5;
		}
		
		ActionForward forward = null;
		
		ProductListPageInfo productListPageInfo = new ProductListPageInfo();
		
		if(choice == 1) {
			
			//각 상품 총 갯수
			int productListCount = productService.getProductListCount(IntViewSelect);
			
			int page = 1;
			int IntviewCount =20;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int maxPage = (int)((double)productListCount/IntviewCount+0.95);
			int startPage = (((int)((double)page/10+0.9))-1)*10+1;
			int endPage = startPage+10-1;
			
			if(endPage > maxPage) endPage=maxPage;
			
			productListPageInfo = new ProductListPageInfo();
			
			productListPageInfo.setPage(page);
			productListPageInfo.setProductListCount(productListCount);
			productListPageInfo.setMaxPage(maxPage);
			productListPageInfo.setStartPage(startPage);
			productListPageInfo.setEndPage(endPage);
			
			/*페이지 뷰 처리 완료*/
			productList = productService.getProductListSelect(page,IntviewCount,IntViewSelect);
			
			if (!member.getAuthor().equalsIgnoreCase("2") && !member.getAuthor().equalsIgnoreCase("3")) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('관리자가 아닙니다');");
				out.print("history.back();");
				out.print("</script>");
			} else if (member.getAuthor().equalsIgnoreCase("2") || member.getAuthor().equalsIgnoreCase("3")) {
				request.setAttribute("productList", productList);
				request.setAttribute("productListPageInfo", productListPageInfo);
				request.setAttribute("viewSelect", viewSelect);
				forward = new ActionForward();
				forward.setPath("mypage/adminProductList.jsp");
				forward.setRedirect(false);
			}
		} else if (choice == 0) {
			
			//상품 총 갯수
			int allListCount = productService.getAllProductListCount();
			
			int page = 1;
			int IntviewCount =20;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int maxPage = (int)((double)allListCount/IntviewCount+0.95);
			int startPage = (((int)((double)page/10+0.9))-1)*10+1;
			int endPage = startPage+10-1;
			
			if(endPage > maxPage) endPage=maxPage;
			
			productListPageInfo = new ProductListPageInfo();
			
			productListPageInfo.setPage(page);
			productListPageInfo.setProductListCount(allListCount);
			productListPageInfo.setMaxPage(maxPage);
			productListPageInfo.setStartPage(startPage);
			productListPageInfo.setEndPage(endPage);
			
			/*페이지 뷰 처리 완료*/
			productList = productService.getProductAllListSelect(page,IntviewCount);
			
			if (!member.getAuthor().equalsIgnoreCase("2") && !member.getAuthor().equalsIgnoreCase("3")) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('관리자가 아닙니다');");
				out.print("history.back();");
				out.print("</script>");
			} else if (member.getAuthor().equalsIgnoreCase("2") || member.getAuthor().equalsIgnoreCase("3")) {
				request.setAttribute("productList", productList);
				request.setAttribute("productListPageInfo", productListPageInfo);
				forward = new ActionForward();
				forward.setPath("mypage/adminProductList.jsp");
				forward.setRedirect(false);
			}
		}
		
		return forward;
	}

}
