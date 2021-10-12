package mypage.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.Member;
import vo.MemberOrder;
import vo.MemberOrderListPageInfo;

public class MemberOrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		Member member = (Member) session.getAttribute("member");

		String viewCount = request.getParameter("view_Count");

		int IntviewCount = 0;
		if (viewCount == null) {
			viewCount = "10";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("5")) {
			viewCount = "5";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("10")) {
			viewCount = "10";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("20")) {
			viewCount = "20";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("30")) {
			viewCount = "30";
			IntviewCount = Integer.parseInt(viewCount);
		}

		MemberInfoService memberInfoService = new MemberInfoService();

		/*내 주문 내역 카운터*/
		int ListMyOrderCount = memberInfoService.getListMyOrderCount(member.getId());
		
		int page =1 ;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int maxPage = (int)((double)ListMyOrderCount/IntviewCount+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if(endPage > maxPage) endPage=maxPage;
		
		MemberOrderListPageInfo memberOrderListPageInfo = new MemberOrderListPageInfo(); 
		
		memberOrderListPageInfo.setPage(page);
		memberOrderListPageInfo.setOrderListCount(ListMyOrderCount);
		memberOrderListPageInfo.setMaxPage(maxPage);
		memberOrderListPageInfo.setStartPage(startPage);
		memberOrderListPageInfo.setEndPage(endPage);
		
		ArrayList<MemberOrder> myOrderList = memberInfoService.getListMyOrder(page, IntviewCount, member.getId());
		
		request.setAttribute("memberOrderListPageInfo", memberOrderListPageInfo);
		request.setAttribute("viewCount", viewCount);
		request.setAttribute("myOrderList", myOrderList);

		ActionForward forward = new ActionForward("mypage/myOrder.jsp", false);

		return forward;
	}

}
