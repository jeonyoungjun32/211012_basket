package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberInfoService;
import svc.OrderService;
import vo.ActionForward;

public class AdminServerMoneyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		MemberInfoService memberInfoService = new MemberInfoService();
		//가입한 전체 회원 수
		int MemberListCount = memberInfoService.getMemberListCount();
		//회원 보유 금액
		int totalMoney = memberInfoService.getMemberMoney();
		
		//회원 보유 포인트
		int totalPoint = memberInfoService.getMemberPoint();
		
		OrderService OrderService = new OrderService();
		//주문 내역 출력(7일간)
		
		ActionForward forward = null;
		
		System.out.println(totalMoney);
		
//		int weekdayMoney =0;

//		for (int i = 0; i < memberAllOrderList.size(); i++) {
//			weekdayMoney += memberAllOrderList.get(i).getOrder_price();
//		}
//
//		request.setAttribute("weekdayMoney", weekdayMoney);
		request.setAttribute("MemberListCount", MemberListCount);
		request.setAttribute("totalPoint", totalPoint);
		request.setAttribute("totalMoney", totalMoney);
		forward = new ActionForward("mypage/ServerMoneyAndPoint.jsp", false);
		

		return forward;
	}

}
