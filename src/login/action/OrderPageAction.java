package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;
import vo.Member;

public class OrderPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		ActionForward forward = null;
		
		Member member = (Member)session.getAttribute("member");
		
		if (member == null) {
			forward = new ActionForward("orderPageFail.jsp", false);
		} else {
			forward = new ActionForward("orderPage.jsp", false);
		}
		
		return forward;
	}

}
