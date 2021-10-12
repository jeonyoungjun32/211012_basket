package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinService;
import vo.ActionForward;

public class LoginIdCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String checkId = request.getParameter("checkid");
		
		ActionForward forward = null;
		
		MemberJoinService memberJoinService = new MemberJoinService();
		
		int idCheckCtn = memberJoinService.IdCheck(checkId);
		
		if(idCheckCtn == 1) {
			forward = new ActionForward("idCheckSuccess.bk", false);
		} else {
			forward = new ActionForward("idCheckFail.bk", false);
		}
		
		return forward;
	}

}
