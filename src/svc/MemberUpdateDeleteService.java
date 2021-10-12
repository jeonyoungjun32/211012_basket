package svc;

import java.sql.Connection;

import dao.MyPageDAO;

import static db.Jdbcutil.*;

import vo.Member;

public class MemberUpdateDeleteService {
	
	//MemberMypageUpdateProAction : 회원 수정 상세내역
	public boolean getMemberDetail(Member member) throws Exception{
		Connection con =getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
					
		int memberUpdate = myPageDAO.memberUpdate(member);
		
		boolean memberUpdateSuccess = false;
		if(memberUpdate > 0) {
			commit(con);
			memberUpdateSuccess = true;
			
		} else {
			rollback(con);
		}
		close(con);
		return memberUpdateSuccess;
	}
	
	//AdminMemberLeaveProAction
	//MemberMypageDeleteProAction 
	//회원 탈퇴
	public boolean getMemberLeave(String id) throws Exception {
		Connection con =getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int memberDeleteCount = myPageDAO.memberDelete(id);
		
		boolean memberDeleteSuccess = false;
		if(memberDeleteCount > 0) {
			commit(con);
			memberDeleteSuccess=true;
		} else {
			rollback(con);
		}
		close(con);
		return memberDeleteSuccess;
	}

	//MemberMypageUpdateProAction
	//수정된 정보 새로 불러오기
	public Member selectMyInfo(String id) throws Exception {
		Connection con =getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		Member member = myPageDAO.MemberSelect(id);
		
		close(con);
		
		return member;
	}
}
