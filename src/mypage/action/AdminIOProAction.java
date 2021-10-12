package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;

public class AdminIOProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String Serial_code[] = request.getParameterValues("code");
		
		if(Serial_code == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('입고할 상품이 없습니다');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		int i = 0;
		int j =0;
		String strProduct_count[] = request.getParameterValues("IO_count");
		
		for(i = 0; i<Serial_code.length; i ++) {
			j++;
		}
		
		int product_count[] = new int[j];
		int intSerial_code[] = new int[j];
		ActionForward forward = null;
		boolean IOinsert = true;
		
		for(i =0; i<Serial_code.length; i++) {
			try {
				product_count[i] = Integer.parseInt(strProduct_count[i]);
				intSerial_code[i] = Integer.parseInt(Serial_code[i]);
			}  catch (Exception e) {
				IOinsert = false;
				forward = new ActionForward("adminIOProFail.bg", false);
				break;
			}
		}
		if(IOinsert) {
			
			ProductService productService = new ProductService();
			
			boolean IOSuccess = productService.adminIOUpdate(intSerial_code,product_count);
			
			if (!IOSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('입고가 실패되었습니다.');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				forward = new ActionForward("adminIOProSuccess.bg", false);
			}
		}
		return forward;
	}

}
