package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberLoginService;
import vo.ActionForward;

public class LoginFindPWProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		MemberLoginService memberLoginService = new MemberLoginService();
		
		ActionForward forward = new ActionForward();
		
		if(id.equalsIgnoreCase("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('최종 관리자는 비밀번호 검색이 안됩니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			if (id == null || id == "") {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('찾을 아이디를 잘못하셨습니다.');");
				out.print("history.back();");
				out.print("</script>");
			} else if (email == null || email == "") {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('이메일을 잘못하셨습니다.');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				String pw = memberLoginService.getMemberFind(id, email);

				if (pw == null || pw == "") {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('입력을 잘못하셨습니다.');");
					out.print("history.back();");
					out.print("</script>");
				} else {
					request.setAttribute("pw", pw);
					forward.setPath("/login/loginFindPWResultForm.jsp");
				}
			}
		}
		return forward;
	}

}
