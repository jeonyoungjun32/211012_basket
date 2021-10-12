package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class BaskeyCartRemoveService {

	/*BaskeyCartRemoveAction*/
	public void cartRemove(HttpServletRequest request, String cartDelete) {
		HttpSession session = request.getSession();

		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		int serial_code = Integer.parseInt(cartDelete);
		
		for (int i = 0; i < cartList.size(); i++) {

			if (cartList.get(i).getSerial_code() == serial_code) {
				cartList.remove(cartList.get(i));
				break;
			}
		}
	}
}
