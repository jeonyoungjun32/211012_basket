package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.Member;

public class AdminMemberDeletePointProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		ArrayList<Member> memberList =(ArrayList<Member>) session.getAttribute("memberListPoint");
		
		MemberInfoService memberInfoService = new MemberInfoService(); 
		
		boolean arrMemberrPointDeleteSuccess = memberInfoService.getArrMemberPoint(memberList);
		
		ActionForward forward = null;
		
		if(!arrMemberrPointDeleteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 회원이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			session.removeAttribute("memberListPoint");
			forward = new ActionForward("adminMemberListDeleteSuccess.bg", false);
		}
		
		return forward;
	}

}
