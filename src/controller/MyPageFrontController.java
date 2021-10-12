package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.action.Action;
import mypage.action.AdminIOFormAction;
import mypage.action.AdminIOListProAction;
import mypage.action.AdminIOProAction;
import mypage.action.AdminMemberDeletePointProAction;
import mypage.action.AdminMemberDeleteProAction;
import mypage.action.AdminMemberLeaveProAction;
import mypage.action.AdminMemberListProAction;
import mypage.action.AdminMemberProAction;
import mypage.action.AdminMemberUpdateProAction;
import mypage.action.AdminOneMemberOrderProAction;
import mypage.action.AdminOrderCancelProAction;
import mypage.action.AdminOrderProAction;
import mypage.action.AdminProductDeleteProAction;
import mypage.action.AdminProductFormAction;
import mypage.action.AdminProductInserProAction;
import mypage.action.AdminProductListFormAction;
import mypage.action.AdminProductUpdateFormAction;
import mypage.action.AdminProductUpdateProAction;
import mypage.action.AdminServerMoneyAction;
import mypage.action.MemberMypageDeleteProAction;
import mypage.action.MemberMypageMoneyProAction;
import mypage.action.MemberMypageUpdateProAction;
import mypage.action.MemberOrderProAction;
import vo.ActionForward;

/**
 * Servlet implementation class BKFrontController
 */
@WebServlet("*.bg")
public class MyPageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPageFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String RequestURI = request.getRequestURI();
		String conextPath = request.getContextPath();
		String command = RequestURI.substring(conextPath.length());
		ActionForward forward = null;
		Action action = null;

		System.out.println(command);
		if (command.equals("/index.bg")) {// 메인화면
			forward = new ActionForward();
			forward.setPath("index.jsp");
		} else if (command.equals("/memberMypage.bg")) {// 내 정보 요청
			forward = new ActionForward();
			forward.setPath("/mypage/mypage.jsp");
		} else if (command.equals("/memberMypageForm.bg")) {// 내 정보 수정 폼
			forward = new ActionForward();
			forward.setPath("/mypage/mypageupdate.jsp");
		} else if (command.equals("/memberMypageUpdatePro.bg")) {// 내 정보 수정 '요청'
			action = new MemberMypageUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberMypageUpdateSuccess.bg")) {// 내 정보 수정 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/memberMypageUpdateSuccess.jsp");
		} else if (command.equals("/memberLeaveForm.bg")) {// 내 정보 탈퇴 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypageLeave.jsp");
		} else if (command.equals("/memberLeavePro.bg")) {// 내 정보 탈퇴 요청
			action = new MemberMypageDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberMypageDeleteSuccess.bg")) {// 내 정보 탈퇴 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/memberMypageDeleteSuccess.jsp");
		} else if (command.equals("/memberLeaveForm.bg")) {// 내 포인트 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypageLeave.jsp");
		} else if (command.equals("/memberMoneyForm.bg")) {// 돈 입금 페이지 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypageMoney.jsp");
		} else if (command.equals("/memberMoneyPro.bg")) {// 돈 입금 페이지 요청
			action = new MemberMypageMoneyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberMypageMoneySuccess.bg")) {// 돈 입금 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/memberMypageMoneySuccess.jsp");
		} else if (command.equals("/memberPointForm.bg")) {// 돈 포인트 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypagePoint.jsp");
		} else if (command.equals("/memberOrderPro.bg")) {// 내 주문 내역 리스트 보기 페이지 처리
			action = new MemberOrderProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/* 관리자 페이지 */
		else if (command.equals("/adminInfo.bg")) {// 관리자 정보 폼 요청
			forward = new ActionForward();
			forward.setPath("/mypage/adminInfo.jsp");
		} else if (command.equals("/adminMemberListPro.bg")) {// 관리자 회원 전체 리스트 조회 폼 + 요청
			action = new AdminMemberListProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminMemberPro.bg")) {// 회원 개인 폼 + 요청
			action = new AdminMemberProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminMemberDeletePro.bg")) {// 관리자 회원 전체 리스트 조회 삭제 요청
			action = new AdminMemberDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminMemberListDeleteConfirm.bg")) {// 관리자 회원 전체 리스트 조회 삭제 요청 후 포인트 소지 회원 삭제 질문 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminMemberListDeleteConfirm.jsp");
		} else if (command.equals("/adminMemberDeletePointPro.bg")) {// 관리자 회원 전체 리스트 조회 삭제 요청 후 포인트 소지 회원 삭제 질문에 대한 답이 Y
			action = new AdminMemberDeletePointProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminMemberListDeleteSuccess.bg")) {// 관리자 회원 전체 리스트 조회 삭제 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminMemberListDeleteSuccess.jsp");
		} else if (command.equals("/adminMemberUpdatePro.bg")) {// 관리자 회원 전체 리스트 권한 수정 요청
			action = new AdminMemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminMemberListUpdateAthorSuccess.bg")) {// 관리자 회원 리스트 권한 수정 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminMemberListUpdateAthorSuccess.jsp");
		} else if (command.equals("/adminMemberListUpdateGradeSuccess.bg")) {// 관리자 회원 리스트 권한 수정 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminMemberListUpdateGradeSuccess.jsp");
		} else if (command.equals("/adminMemberLeavePro.bg")) {// 관리자가 회원정보에서 탈퇴 요청
			action = new AdminMemberLeaveProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminMemberLeaveSuccess.bg")) {// 관리자가 회원정보에서 탈퇴 요청 성공
			forward = new ActionForward();
			forward.setPath("mypage/adminMemberLeaveSuccess.jsp");
		} else if (command.equals("/memberMypageDeleteSuccess.bg")) {// 내 정보 탈퇴 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/memberMypageDeleteSuccess.jsp");
		} else if (command.equals("/adminServerMoney.bg")) {// 홈페이지 내에 있는 돈 관리 폼
			action = new AdminServerMoneyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminMoneyForm.bg")) {// 돈 입금 페이지 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminMypageMoney.jsp");
		} else if (command.equals("/adminPointForm.bg")) {// 돈 포인트 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminMypagePoint.jsp");
		}

		/*상품 관련*/
		else if (command.equals("/adminProductInserForm.bg")) {// 물건 등록 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminProductInsertForm.jsp");
		} else if (command.equals("/adminProductInsertPro.bg")) {// 물건 등록 요청
			action = new AdminProductInserProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductInsertSuccessForm.bg")) {// 물건 등록 요청 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminProductInsertSuccess.jsp");
		} else if (command.equals("/adminIOForm.bg")) {// 입고 폼
			action = new AdminIOFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminIOPro.bg")) {// 입고 요청
			action = new AdminIOProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminIOProSuccess.bg")) {// 입고 요청 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminIOProSuccess.jsp");
		} else if (command.equals("/adminIOProFail.bg")) {// 입고 요청 실패 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminIOProFail.jsp");
		} else if (command.equals("/adminIOListpro.bg")) {// 입고 내역 페이지 폼 요청
			action = new AdminIOListProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductListForm.bg")) {// 전체 폼 + 페이지 뷰 처리
			action = new AdminProductListFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductForm.bg")) {// 전체 물건 리스트에서 한개의 물품 상세내역보기
			action = new AdminProductFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductUpdateForm.bg")) {// 물건 등록 수정 폼
			action = new AdminProductUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductUpdatePro.bg")) {// 물건 등록 수정 요청
			action = new AdminProductUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductDeletePro.bg")) {// 물건 삭제 요청
			action = new AdminProductDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductListDeleteSuccess.bg")) {// 물건 삭제 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminProductListDeleteSuccess.jsp");
		} else if (command.equals("/adminOrderPro.bg")) {//총 주문 내역 조회 + 페이지 뷰 처리
			action = new AdminOrderProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminOrderCancelPro.bg")) {//주문 취소 요청
			action = new AdminOrderCancelProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminOneMemberOrderPro.bg")) {//개인 주문 내역 조회 + 페이지 뷰 처리
			action = new AdminOneMemberOrderProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
}
