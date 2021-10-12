package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BaskeyCartAddService;
import vo.ActionForward;
import vo.Member;
import vo.Product;

public class BaskeyCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		Member member =(Member)session.getAttribute("member");
		ActionForward forward = null;
		if(member == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인을 해주세요');");
			out.print("location='login.bk'");
			out.print("</script>");
		} else {
		
		BaskeyCartAddService baskeyCartAddService = new BaskeyCartAddService(); 
		
		int serial_code = Integer.parseInt(request.getParameter("serial_code")); //여서 null값인데 왜?!왜!!
		
		Product baskeyCart = baskeyCartAddService.geteCartBaskey(serial_code);
				
		baskeyCartAddService.addCart(request, baskeyCart);
		
		if(baskeyCart == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('장바구니 목록리스트에 없습니다');");
			out.print("history.back();");
			out.print("</script>");
		}
		forward = new ActionForward("baskeyCartList.bk", true);
		}
		return forward;
	}
}
