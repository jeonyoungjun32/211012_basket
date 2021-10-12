package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberLoginService;
import vo.ActionForward;

public class LoginFindIDProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String member_name = request.getParameter("name");
		String member_email = request.getParameter("email");
		
		ActionForward forward = new ActionForward();
		
		if(member_email == null || member_email == "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('이메일을 입력해주세요.');");
			out.print("history.back();");
			out.print("</script>");
		} else if (member_name == null || member_name == "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('이름을 잘못하셨습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			MemberLoginService memberLoginService = new MemberLoginService();
			
			String getId = memberLoginService.getMemberId(member_name, member_email);
		
			if(getId == null || getId == "") {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('입력을 잘못하셨습니다.');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				request.setAttribute("id", getId);
				forward.setPath("/login/loginFindIDResultForm.jsp");
			}
		}
		return forward;
	}
}
