package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberUpdateDeleteService;
import vo.ActionForward;

public class AdminMemberLeaveProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		MemberUpdateDeleteService memberDelete = new MemberUpdateDeleteService();
		
		boolean memberDeleteSuccess = memberDelete.getMemberLeave(id);
		
		ActionForward forward = null;
		
		if(!memberDeleteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('탈퇴실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			request.setAttribute("id", id);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("adminMemberLeaveSuccess.bg");
		}
		
		return forward;
	}

}
