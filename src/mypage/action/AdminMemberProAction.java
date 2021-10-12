package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.Member;

public class AdminMemberProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		MemberInfoService memberInfoService = new MemberInfoService();
		
		Member member = memberInfoService.getMember(id);
		
		request.setAttribute("getMember", member);
		
		ActionForward forward = new ActionForward("mypage/adminMemberInfo.jsp", false);
		
		return forward;
	}

}
