package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.Member;

public class AdminMemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String memberCheckId[] = request.getParameterValues("memberCheck");

		HttpSession session = request.getSession();

		if (memberCheckId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('id가 체크되지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}

		MemberInfoService memberInfoService = new MemberInfoService();

		ArrayList<Member> memberList = memberInfoService.getArrMember(memberCheckId);

		ActionForward forward = null;
		
		for (Member members : memberList) {
			if (members.getMoney() != 0) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원의 정보에는 입금된 돈이 있습니다.');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				if (members.getPoint() != 0) {
					session.setAttribute("memberListPoint", memberList);

					forward = new ActionForward("adminMemberListDeleteConfirm.bg", false);
				} else {
					boolean arrMemberDeleteSuccess = memberInfoService.getArrMemberDelete(members);

					if (!arrMemberDeleteSuccess) {
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.print("<script>");
						out.print("alert('삭제가 실패 되었습니다');");
						out.print("history.back();");
						out.print("</script>");
					} else {
						forward = new ActionForward("adminMemberListDeleteSuccess.bg", false);
					}
				}
			}
		}

		return forward;
	}

}
