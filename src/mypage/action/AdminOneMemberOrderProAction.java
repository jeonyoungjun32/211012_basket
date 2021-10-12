package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderService;
import vo.ActionForward;
import vo.MemberOrder;
import vo.MemberOrderListPageInfo;

public class AdminOneMemberOrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		String searchID = (String)request.getParameter("searchID");
		
		System.out.println("아이디 검색 값 : "+searchID);
		
		String viewCount = request.getParameter("view_Count");
		
		int IntviewCount =0;
		if(viewCount == null || viewCount == "") {
			viewCount="10";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("5")) {
			viewCount="5";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("10")) {
			viewCount="10";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("20")) {
			viewCount="20";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("30")) {
			viewCount="30";
			IntviewCount = Integer.parseInt(viewCount);
		}
		
		OrderService orderService = new OrderService();

		int searchIDListOrderCount = orderService.searchIDListOrderCount(searchID);

		
		
		int page =1 ;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int maxPage = (int)((double)searchIDListOrderCount/IntviewCount+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if(endPage > maxPage) endPage=maxPage;
		
		MemberOrderListPageInfo memberOrderListPageInfo = new MemberOrderListPageInfo(); 
		memberOrderListPageInfo.setOrderListCount(searchIDListOrderCount);
		memberOrderListPageInfo.setPage(page);
		memberOrderListPageInfo.setMaxPage(maxPage);
		memberOrderListPageInfo.setStartPage(startPage);
		memberOrderListPageInfo.setEndPage(endPage);
		ArrayList<MemberOrder> memberSearchIDOrderList = null;
	
		memberSearchIDOrderList = orderService.searchIDListOrder(page, IntviewCount, searchID);		
		
		if (memberSearchIDOrderList == null || memberSearchIDOrderList.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('검색한 회원이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			request.setAttribute("memberOrderListPageInfo", memberOrderListPageInfo);
			request.setAttribute("viewCount", viewCount);
			request.setAttribute("memberSearchIDOrderList", memberSearchIDOrderList);
			request.setAttribute("searchID", searchID);
			forward = new ActionForward("mypage/memberOneOrder.jsp", false);
		}
		return forward;
	}

}
