package mypage.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderService;
import vo.ActionForward;
import vo.MemberOrder;
import vo.MemberOrderListPageInfo;

public class AdminOrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		OrderService orderService = new OrderService();
		ActionForward forward = null;

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

		/* 전체 리스트 카운터 */
		int allListOrderCount = orderService.allLIstOrderCount();

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int maxPage = (int) ((double) allListOrderCount / IntviewCount + 0.95);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage)
			endPage = maxPage;

		MemberOrderListPageInfo memberOrderListPageInfo = new MemberOrderListPageInfo();

		memberOrderListPageInfo.setPage(page);
		memberOrderListPageInfo.setOrderListCount(allListOrderCount);
		memberOrderListPageInfo.setMaxPage(maxPage);
		memberOrderListPageInfo.setStartPage(startPage);
		memberOrderListPageInfo.setEndPage(endPage);

		/* 전체 리스트 */
		ArrayList<MemberOrder> memberAllOrderList = orderService.allListOrder(page, IntviewCount);
		
		request.setAttribute("memberOrderListPageInfo", memberOrderListPageInfo);
		request.setAttribute("viewCount", viewCount);
		request.setAttribute("memberAllOrderList", memberAllOrderList);
		
		forward = new ActionForward("mypage/memberAllOrder.jsp", false);

		return forward;
	}

}
