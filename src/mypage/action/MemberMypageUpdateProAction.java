package mypage.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberUpdateDeleteService;
import vo.ActionForward;
import vo.Member;

public class MemberMypageUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		
		MemberUpdateDeleteService memberUpdate = new MemberUpdateDeleteService();
		
		ActionForward forward = null;
		
		/*지금시간으로부터 14세 이상만 가입가능*/
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -7);
		
		String strYear14 = null;
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
		String birth =request.getParameter("birth");
		//날짜로 형변환
		Date birthday = dateformat.parse(birth);
		
		strYear14 = dateformat.format(cal.getTime());
		Date dateYear14 = dateformat.parse(strYear14);
		
		if(!dateYear14.after(birthday)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('생일이 잘못 입력되셨습니다.(7세이상 이용가능).');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			member.setPw(request.getParameter("pw"));
			member.setAddress_number(request.getParameter("address_number"));
			member.setAddress(request.getParameter("address"));
			member.setAddress_contents(request.getParameter("address_contents"));
			member.setEmail(request.getParameter("email"));
			member.setBirth(request.getParameter("birth"));
			member.setGender(request.getParameter("gender"));

			boolean isUpdateMember = memberUpdate.getMemberDetail(member);

			if (!isUpdateMember) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('수정실패');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memberMypageUpdateSuccess.bg");
			}
		}
		return forward;
	}

}
