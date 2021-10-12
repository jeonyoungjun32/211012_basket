package svc;

import static db.Jdbcutil.*;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import vo.Cart;
import vo.Product;

public class BaskeyCartAddService {

	/*BaskeyCartAddAction*/
	public Product geteCartBaskey(int serial_code) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);

		Product product = productDAO.selectBaskeyCart(serial_code);

		close(con);

		return product;
	}

	/* BaskeyCartAddAction */
	public void addCart(HttpServletRequest request, Product baskeyCart) {

		HttpSession session = request.getSession();

		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");

		if (cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}

		boolean isNewCart = true;

		for (int i = 0; i < cartList.size(); i++) {
			if (baskeyCart.getName().equals(cartList.get(i).getName())) {
				isNewCart = false;
				break;
			}
		}
		
		

		if (isNewCart == true) {
			Cart cart = new Cart();

			cart.setSerial_code(baskeyCart.getSerial_code());
			cart.setName(baskeyCart.getName());
			cart.setCount(baskeyCart.getCount());
			cart.setPrice(baskeyCart.getPrice());
			cart.setKinds(baskeyCart.getChoice());

			cartList.add(cart);
		}
	}
}
