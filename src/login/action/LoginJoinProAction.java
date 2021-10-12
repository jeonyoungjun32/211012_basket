package login.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinService;
import vo.ActionForward;
import vo.Member;

public class LoginJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pw1 = request.getParameter("pw1");
		String pw = request.getParameter("pw");
		
		ActionForward forward = new ActionForward();
		if(!pw1.equalsIgnoreCase(pw)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			MemberJoinService memberJoinService = new MemberJoinService();
			
//			/*지금시간으로부터 7세 이상만 가입가능*/  이거 공부하면 ㅇ주 좋다  
			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date); //날짜,시간만 
			cal.add(Calendar.YEAR, -7); //YEAR=년도만 -7하면  , 월,일 다 뺼수있다    
			
			String strYear14 = null;
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
			String birth =request.getParameter("birth");
			//날짜로 형변환
			Date birthday = dateformat.parse(birth);
			
			//format = 데이터형식을 변경했다
			strYear14 = dateformat.format(cal.getTime());
			Date dateYear14 = dateformat.parse(strYear14);
			
			if(!dateYear14.after(birthday)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('7세 이상만 가입할수 있습니다.');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				Member member = new Member();

				member.setId(request.getParameter("id"));
				member.setPw(pw);
				member.setName(request.getParameter("name"));
				member.setAddress_number(request.getParameter("address_number"));
				member.setAddress(request.getParameter("address"));
				member.setAddress_contents(request.getParameter("address_contents"));
				member.setEmail(request.getParameter("email"));
				member.setBirth(request.getParameter("birth"));
				member.setGender(request.getParameter("gender"));

				boolean isInsertmember = memberJoinService.JoinMember(member);

				if (!isInsertmember) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('등록실패');");
					out.print("history.back();");
					out.print("</script>");
				} else {
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("joinSuccess.bk");
				}
			}
		}
		return forward;
	}
}
