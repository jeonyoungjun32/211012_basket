package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberLoginService;
import vo.ActionForward;

public class LoginIDFindPWReviseProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pw1 = request.getParameter("pw1");
		ActionForward forward = null;
		
		if(id == null || id == "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('변경할 아이디가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else if(!pw.equalsIgnoreCase(pw1)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			MemberLoginService memberLoginService = new MemberLoginService();
			
			String authorNum = memberLoginService.getAuthor(id);
			
			if(authorNum.equals("3")) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('최종 관리자는 변경 불가능합니다.');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				boolean memberIDReviseSuccess = memberLoginService.getMemberIDRevise(id, pw);

				if (!memberIDReviseSuccess) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('비밀번호 변경실패');");
					out.print("history.back();");
					out.print("</script>");
				} else {
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("loginReviseSuccess.bk");
				}
			}
		}
		return forward;
	}

}
