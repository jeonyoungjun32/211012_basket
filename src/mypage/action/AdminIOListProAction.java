package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProductService;
import vo.ActionForward;
import vo.IO;
import vo.Member;
import vo.PageInfo;

public class AdminIOListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		String IO_select = request.getParameter("IO_select");
		
		ActionForward forward = null;
		
		ProductService productService = new ProductService();
		
		if(IO_select == "" || IO_select == null || IO_select.equals("all")) {
			/*총 갯수 값*/
			int IOListCount = productService.getIOListCount();
			
			int page = 1;
			int viewCount =20;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int maxPage = (int)((double)IOListCount/viewCount+0.95);
			int startPage = (((int)((double)page/10+0.9))-1)*10+1;
			int endPage = startPage+10-1;
					
			if(endPage > maxPage) endPage=maxPage;
			
			PageInfo IoPageInfo = new PageInfo();
			
			IoPageInfo.setPage(page);
			IoPageInfo.setMemberListCount(IOListCount);
			IoPageInfo.setMaxPage(maxPage);
			IoPageInfo.setStartPage(startPage);
			IoPageInfo.setEndPage(endPage);
			
			ArrayList<IO> ioList = productService.getIOList(page,viewCount);
			
			if(!member.getAuthor().equalsIgnoreCase("2") && !member.getAuthor().equalsIgnoreCase("3")) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('관리자가 아닙니다');");
				out.print("history.back();");
				out.print("</script>");
			} else if (member.getAuthor().equalsIgnoreCase("2") || member.getAuthor().equalsIgnoreCase("3")) {
				request.setAttribute("IoPageInfo", IoPageInfo);
				request.setAttribute("ioList", ioList);
				request.setAttribute("viewCount", viewCount);
				forward = new ActionForward("mypage/adminIOList.jsp", false);
			}
		} else if (IO_select != null && IO_select != "") {
			
			/*입출고 선택에 따른 총 갯수 값*/
			int IOListCount = productService.getIOSelectListCount(IO_select);
			
			int page = 1;
			int viewCount=20;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int maxPage = (int)((double)IOListCount/viewCount+0.95);
			int startPage = (((int)((double)page/10+0.9))-1)*10+1;
			int endPage = startPage+10-1;
					
			if(endPage > maxPage) endPage=maxPage;
			
			PageInfo IoPageInfo = new PageInfo();
			
			IoPageInfo.setPage(page);
			IoPageInfo.setMemberListCount(IOListCount);
			IoPageInfo.setMaxPage(maxPage);
			IoPageInfo.setStartPage(startPage);
			IoPageInfo.setEndPage(endPage);
			
			ArrayList<IO> ioList = productService.getIOChoiceList(page,viewCount,IO_select);
			
			if(!member.getAuthor().equalsIgnoreCase("2") && !member.getAuthor().equalsIgnoreCase("3")) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('관리자가 아닙니다');");
				out.print("history.back();");
				out.print("</script>");
			} else if (member.getAuthor().equalsIgnoreCase("2") || member.getAuthor().equalsIgnoreCase("3")) {
				request.setAttribute("IoPageInfo", IoPageInfo);
				request.setAttribute("ioList", ioList);
				request.setAttribute("viewCount", viewCount);
				request.setAttribute("IO_select", IO_select);
				forward = new ActionForward("mypage/adminIOList.jsp", false);
			}
		}
		return forward;
	}

}
