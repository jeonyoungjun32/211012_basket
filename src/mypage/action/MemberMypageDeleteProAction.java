package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberUpdateDeleteService;
import vo.ActionForward;
import vo.Member;

public class MemberMypageDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();
		
		MemberUpdateDeleteService memberDelete = new MemberUpdateDeleteService();
		
		boolean memberDeleteSuccess = memberDelete.getMemberLeave(id);
		
		ActionForward forward = new ActionForward();
		
		if(!memberDeleteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('탈퇴실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			session.removeAttribute("member");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("memberMypageDeleteSuccess.bg");
		}
		
		return forward;
	}

}
