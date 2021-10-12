package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.MyPageDAO;
import vo.Member;

public class MemberMoneyAndPointService {
	
	//MemberMypageMoneyProAction : 돈 충전
	public boolean setMoney(String money, String id) throws Exception {
		
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int MemberSetMoneyCount = myPageDAO.MemberSetMoney(money, id);
		
		boolean MemeberSetMoneySuccess = false;
		if(MemberSetMoneyCount> 0) {
			commit(con);
			MemeberSetMoneySuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return MemeberSetMoneySuccess;
	}

	//AdminOrderCancelProAction : 환불후 정보 새로고침
	//MemberMypageMoneyProAction : 돈 충전 한 아이디정보를 새로고침을 통해 member에 다시 담기위한 서비스
	public Member setMember(String id) throws Exception {
		
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		Member member = myPageDAO.MemberSelect(id);
		
		close(con);
		
		return member;
	}
}
