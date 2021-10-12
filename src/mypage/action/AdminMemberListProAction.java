package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.Member;
import vo.PageInfo;

public class AdminMemberListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		String viewCount = request.getParameter("view_Count");
		int IntviewCount =0;
		if(viewCount == null || viewCount.equals("5")) {
			viewCount="5";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("10")) {
			viewCount="10";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("25")) {
			viewCount="25";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("50")) {
			viewCount="50";
			IntviewCount = Integer.parseInt(viewCount);
		}
		
		MemberInfoService memberInfoService =new MemberInfoService();
		
		int MemberListCount = memberInfoService.getMemberListCount();
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int maxPage = (int)((double)MemberListCount/IntviewCount+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if(endPage > maxPage) endPage=maxPage;
		
		PageInfo memberPageInfo = new PageInfo();
		
		memberPageInfo.setPage(page);
		memberPageInfo.setMemberListCount(MemberListCount);
		memberPageInfo.setMaxPage(maxPage);
		memberPageInfo.setStartPage(startPage);
		memberPageInfo.setEndPage(endPage);
		
		ArrayList<Member> memberList = memberInfoService.getListMember(page,IntviewCount);
		
		ActionForward forward = null;
		
		if(!member.getAuthor().equalsIgnoreCase("2") && !member.getAuthor().equalsIgnoreCase("3")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('관리자가 아닙니다');");
			out.print("history.back();");
			out.print("</script>");
		} else if(member.getAuthor().equalsIgnoreCase("2") || member.getAuthor().equalsIgnoreCase("3")) {
			request.setAttribute("memberPageInfo", memberPageInfo);
			request.setAttribute("memberList", memberList);
			request.setAttribute("viewCount", viewCount);
			forward = new ActionForward();
			forward.setPath("mypage/adminMemberList.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
