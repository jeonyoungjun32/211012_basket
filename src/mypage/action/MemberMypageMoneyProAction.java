package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberMoneyAndPointService;
import vo.ActionForward;
import vo.Member;

public class MemberMypageMoneyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String money = request.getParameter("money");
		
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();
		
		MemberMoneyAndPointService memberMoneyAndPointService = new MemberMoneyAndPointService();

		boolean MemeberSetMoneySuccess = memberMoneyAndPointService.setMoney(money, id);
		
		ActionForward forward = new ActionForward();
		if(!MemeberSetMoneySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('돈충전 실패');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		member = memberMoneyAndPointService.setMember(id);
		
		session.setAttribute("member", member);
		
		forward = new ActionForward("memberMypageMoneySuccess.bg", false);
		return forward;
	}

}
