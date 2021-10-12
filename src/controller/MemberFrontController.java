package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.action.Action;
import login.action.BaskeyCartAddAction;
import login.action.BaskeyCartListAction;
import login.action.BaskeyCartRemoveAction;
import login.action.BeverageIngredientListAction;
import login.action.BeverageListAction;
import login.action.CoffeeIngredientListAction;
import login.action.CoffeeListAction;
import login.action.DessertIngredientListAction;
import login.action.DessertListAction;
import login.action.IceCakeIngredientListAction;
import login.action.IceCakeListAction;
import login.action.IcecreamIngredientListAction;
import login.action.IcecreamListAction;
import login.action.LogOutProAction;
import login.action.LoginFindIDProAction;
import login.action.LoginFindPWProAction;
import login.action.LoginIDFindPWReviseProAction;
import login.action.LoginIdCheckProAction;
import login.action.LoginJoinProAction;
import login.action.LoginProAction;
import login.action.OrderPageAction;
import login.action.OrderPageCartListAction;
import login.action.OrderPageSelectAction;
import login.action.OrderPageSelectDetailAction;
import login.action.OrderResultCartListDeleteAction;
import login.action.OrderResultPointProAction;
import login.action.OrderResultProAction;
import vo.ActionForward;

/**
 * Servlet implementation class BKFrontController
 */
@WebServlet("*.bk")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String RequestURI = request.getRequestURI();
		String conextPath = request.getContextPath();
		String command = RequestURI.substring(conextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		System.out.println(command);
		if(command.equals("/login.bk")) {//로그인 화면
			forward = new ActionForward();
			forward.setPath("/login/login.jsp");
		} else if(command.equals("/loginFindIDForm.bk")) {//아이디 찾기 창
			forward = new ActionForward();
			forward.setPath("/login/loginFindIDForm.jsp");
		} else if (command.equals("/loginFindIDPro.bk")) {//아이디 찾기 요청
			action = new LoginFindIDProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginIDFindPWReviseForm.bk")) {//아이디 찾은 후 비밀번호 변경 창
			forward = new ActionForward();
			forward.setPath("/login/loginIDFindPWReviseForm.jsp");
		} else if (command.equals("/loginIDFindPWRevisePro.bk")) {//아이디 찾은 후 비밀번호 변경 요쳥
			action = new LoginIDFindPWReviseProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginFindPWForm.bk")) {//비밀번호 찾을 아이디 찾기 창
			forward = new ActionForward();
			forward.setPath("/login/loginFindPWForm.jsp");
		} else if (command.equals("/loginFindPWPro.bk")) {//비밀번호 찾을 아이디 찾기 요청
			action = new LoginFindPWProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginIDFindPWReviseForm2.bk")) {//비밀번호 찾은 후 비밀번호 변경 창
			forward = new ActionForward();
			forward.setPath("/login/loginIDFindPWReviseForm2.jsp");
		} else if(command.equals("/loginReviseSuccess.bk")) {//비밀번호 변경 성공
			forward = new ActionForward();
			forward.setPath("/login/loginReviseSuccess.jsp");
		} else if (command.equals("/loginIdCheck.bk")) {//id 중복체크 폼
			forward = new ActionForward();
			forward.setPath("/login/joinCheckForm.jsp");
		} else if (command.equals("/idCheckFail.bk")) {//id 중복체크 성공 페이지
			forward = new ActionForward();
			forward.setPath("/login/idCheckFailForm.jsp");
		} else if (command.equals("/idCheckSuccess.bk")) {//id 중복체크 실패 페이지
			forward = new ActionForward();
			forward.setPath("/login/idCheckSuccessForm.jsp");
		} else if (command.equals("/loginIdCheckPro.bk")) {//id 중복체크 요청
			action = new LoginIdCheckProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginJoinForm.bk")) {//회원 가입 창
			forward = new ActionForward();
			forward.setPath("/login/joinForm.jsp");
		} else if (command.equals("/loginjoinPro.bk")) {//회원 가입 요청
			action = new LoginJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/joinSuccess.bk")) {//회원 가입 성공 폼
			forward = new ActionForward();
			forward.setPath("/login/joinSuccess.jsp");
		} else if (command.equals("/loginPro.bk")) {//로그인 요청
			action = new LoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/logOutPro.bk")) {//로그아웃 요청
			action = new LogOutProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/index.bk")) {//내 메인 페이지 이동
			forward = new ActionForward();
			forward.setPath("index.jsp");
		} 
		/*메뉴 네비*/
		else if (command.equals("/icecreamList.bk")) {//아이스크림 리스트 폼 + 요청
			action = new IcecreamListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/iceCakeList.bk")) {//아이스케이크 리스트 폼 + 요청
			action = new IceCakeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/beverageList.bk")) {//음료 리스트 폼 + 요청
			action = new BeverageListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/coffeeList.bk")) {//커피 리스트 폼 + 요청
			action = new CoffeeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/dessertList.bk")) {//디저트 리스트 폼 + 요청
			action = new DessertListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*영양소 네비*/
		else if (command.equals("/icecreamIngredientList.bk")) {//아이스크림 리스트 폼 + 요청
			action = new IcecreamIngredientListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/iceCakeIngredientList.bk")) {//아이스케이크 리스트 폼 + 요청
			action = new IceCakeIngredientListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/beverageIngredientList.bk")) {//음료 리스트 폼 + 요청
			action = new BeverageIngredientListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/coffeeIngredientList.bk")) {//커피 리스트 폼 + 요청
			action = new CoffeeIngredientListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/dessertIngredientList.bk")) {//디저트 리스트 폼 + 요청
			action = new DessertIngredientListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderPage.bk")) {//주문 페이지 폼 요청
			action = new OrderPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderPageSelect.bk")) {//주문 페이지 선택한 종류 요청
			action = new OrderPageSelectAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderPageSelectDetail.bk")) {//주문페이지 선택한 상품 선택 요청
			action = new OrderPageSelectDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderPageCartList.bk")) {//주문페이지 선택한 장바구니에 담은걸 다시 가져오기
			action = new OrderPageCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderResultCartListDelete.bk")) {//주문페이지 장바구니에 담은거 선택하여 삭제
			action = new OrderResultCartListDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderResult.bk")) {//주문페이지 선택한 상품 수량 입력 폼
			forward = new ActionForward();
			forward.setPath("/orderResult.jsp");
		} else if (command.equals("/orderResultPro.bk")) {//주문페이지 선택한 상품 수량 입력 요청
			action = new OrderResultProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderResultPointPro.bk")) {//주문페이지 수량 + 포인트 사용
			action = new OrderResultPointProAction();
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/* 장바구니 */
		else if (command.equals("/cartAdd.bk")) {// 장바구니 저장
			action = new BaskeyCartAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/baskeyCartList.bk")) {// 장바구니 리스트
			action = new BaskeyCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/basketCartRemove.bk")) {//장바구니 삭제 요청이면
			action = new BaskeyCartRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		if (forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
}
