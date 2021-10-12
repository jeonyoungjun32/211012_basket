package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.Member;

public class AdminMemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String memberCheckId[] = request.getParameterValues("memberCheck");
		
		//권한 변경
		String memberUpdateAthor = request.getParameter("MemberUpdateAthor");
		
		//등금 변경
		String memberUpdateGrade = request.getParameter("MemberUpdateGrade");
		
		MemberInfoService memberInfoService = new MemberInfoService();

		ArrayList<Member> memberList = memberInfoService.getArrMember(memberCheckId);
		
		if (memberList.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('id가 체크되지 않았습니다.');");
			out.print("location='adminMemberListPro.bg';");
			out.print("</script>");
		}
		
		ActionForward forward = null;
		if(memberUpdateAthor=="" && memberUpdateGrade=="") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('권한 또는 등급 설정이되지 않았습니다.');");
			out.print("location='adminMemberListPro.bg';");
			out.print("</script>");
		} else {
			for(Member members : memberList) {
				
				if(memberUpdateAthor != null && memberUpdateAthor != "") {
					boolean updateMemberAuthorSuccess = memberInfoService.getArrmemberAthorUpdate(members,memberUpdateAthor);
					
					if(!updateMemberAuthorSuccess) {
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.print("<script>");
						out.print("alert('권한 수정이 실패되었습니다');");
						out.print("history.back();");
						out.print("</script>");
					} else {
						request.setAttribute("memberUpdateAthor", memberUpdateAthor);
						forward = new ActionForward("adminMemberListUpdateAthorSuccess.bg", false);
					}
				}
				
				if(memberUpdateGrade != null && memberUpdateGrade != "") {
					boolean updateMemberGradeSuccess = memberInfoService.getArrmemberGradeUpdate(members,memberUpdateGrade);
					
					if(!updateMemberGradeSuccess) {
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.print("<script>");
						out.print("alert('등급 수정이 실패되었습니다');");
						out.print("history.back();");
						out.print("</script>");
					} else {
						request.setAttribute("memberUpdateGrade", memberUpdateGrade);
						forward = new ActionForward("adminMemberListUpdateGradeSuccess.bg", false);
					}
				}
			}
		}
		return forward;
	}

}
