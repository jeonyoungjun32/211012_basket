package svc;
import static db.Jdbcutil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MyPageDAO;
import vo.Member;
import vo.MemberOrder;

public class MemberInfoService {
	
	//AdminMemberListProAction : 회원 전체 목록
	public ArrayList<Member> getListMember(int page, int viewCount) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		ArrayList<Member> memberList = myPageDAO.getListMember(page,viewCount);
		
		close(con);
		
		return memberList;
	}

	//AdminOrderCancelProAction : 주문 취소를 위한 엑션
	//AdminMemberProAction : 회원 아이디를 통해 검색
	public Member getMember(String id) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		Member member = myPageDAO.getMember(id);
		
		close(con);
		
		return member;
	}

	//AdminMemberDeleteProAction
	//AdminMemberUpdateProAction
	//관리자 회원(개인) 조회
	public ArrayList<Member> getArrMember(String[] memberCheckId) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		ArrayList<Member> memberList = myPageDAO.adminGetMember(memberCheckId);
		
		close(con);
		
		return memberList;
	}
	
	//AdminMemberDeleteProAction : 관리자 회원 체크박스 선택시 삭제
	public boolean getArrMemberDelete(Member members) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int arrMemberDeleteCount = myPageDAO.arrMemberDelete(members);
		
		boolean arrMemberDeleteSuccess = false;
		if(arrMemberDeleteCount>0) {
			commit(con);
			arrMemberDeleteSuccess=true;
		} else {
			rollback(con);
		}
		close(con);
		return arrMemberDeleteSuccess;
	}

	//AdminMemberDeletePointProAction : 포인트 있음에도 불구하고 삭제하는 요청
	public boolean getArrMemberPoint(ArrayList<Member> memberList) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int arrMemberrPointDeleteCount = myPageDAO.arrMemberPointDelete(memberList);
		
		boolean arrMemberrPointDeleteSuccess = false;
		if(arrMemberrPointDeleteCount>0) {
			commit(con);
			arrMemberrPointDeleteSuccess=true;
		} else {
			rollback(con);
		}
		close(con);
		return arrMemberrPointDeleteSuccess;
	}
	
	//AdminMemberListProAction : 회원 전체 카운터 숫자로 반환
	public int getMemberListCount() throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int MemberListCount = myPageDAO.selectMemberListCount();
		
		close(con);
		
		return MemberListCount;
	}

	//AdminMemberUpdateProAction : 관리자 조회 - 회원 권한 수정
	public boolean getArrmemberAthorUpdate(Member members, String memberUpdateAthor) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int updateMemberAuthorCount = myPageDAO.updateMemberAuthor(members,memberUpdateAthor);
		boolean updateMemberAuthorSuccess = false;
		
		if(updateMemberAuthorCount >0) {
			updateMemberAuthorSuccess=true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return updateMemberAuthorSuccess;
	}

	//AdminMemberUpdateProAction : 관리자 조회 - 회원 등급 수정
	public boolean getArrmemberGradeUpdate(Member members, String memberUpdateGrade) {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int updateMemberGradeCount = myPageDAO.updateMemberGrade(members,memberUpdateGrade);
		boolean updateMemberGradeSuccess = false;
		
		if(updateMemberGradeCount >0) {
			updateMemberGradeSuccess=true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return updateMemberGradeSuccess;
	}
	
	//MemberOrderResultProAction
	public ArrayList<MemberOrder> getListMyOrder(int page, int intviewCount, String id) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		ArrayList<MemberOrder> myOrderList = myPageDAO.getListMyOrder(page,intviewCount,id);
		
		close(con);
		
		return myOrderList;
	}
	
	//AdminServerMoneyAction
	public int getMemberMoney() throws Exception {
		Connection con = getConnection();
	
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
	
		myPageDAO.setConnection(con);
		
		int totalMoney = myPageDAO.getMemberMoney();
		
		close(con);
		
		return totalMoney;
	}

	//AdminServerMoneyAction
	public int getMemberPoint() throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
	
		myPageDAO.setConnection(con);
		
		int totalPoint = myPageDAO.getMemberPoint();
		
		close(con);
		
		return totalPoint;
	}

	//MemberOrderProAction
	public int getListMyOrderCount(String id) throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
	
		myPageDAO.setConnection(con);
		
		int ListMyOrderCount = myPageDAO.getListMyOrderCount(id);
		
		close(con);
		
		return ListMyOrderCount;
	}
	
	
}
