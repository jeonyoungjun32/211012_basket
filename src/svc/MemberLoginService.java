package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class MemberLoginService {
	
	//LoginProAction 로그인화면
	public Member getMember(String id, String pw) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		Member member = memberDAO.memberLogin(id, pw);
		
		close(con);
		
		return member;
	}
	
	//LoginProAction 맞는 id정보 가져오기
		public String getMemberID(String id) throws Exception {
			Connection con = getConnection();
			
			MemberDAO memberDAO = MemberDAO.getInstance();
			
			memberDAO.setConnection(con);
			
			String getId = memberDAO.memberGetID(id);
			close(con);
			
			return getId;
		}
	
	//LoginFindIDProAction : 아이디 검색
	public String getMemberId(String member_name, String member_email) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		String getId = memberDAO.memberIDFind(member_name, member_email);
		
		close(con);
		
		return getId;
	}
	
	//LoginIDFindPWReviseProAction : 아이디 검색후 비밀번호 수정
	public boolean getMemberIDRevise(String id, String pw) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		int memberIDReviseCtn = memberDAO.memberIDRevise(id, pw);
		
		boolean memberIDReviseSuccess = false;
		if(memberIDReviseCtn > 0) {
			commit(con);
			memberIDReviseSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return memberIDReviseSuccess;
	}
	
	//LoginFindPWProAction : 비밀번호 찾을 아이디 검색
	public String getMemberFind(String id, String email) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		String pw = memberDAO.memberIDFindPW(id, email);
		
		close(con);
		
		return pw;
	}
	
	//LoginIDFindPWReviseProAction
	//최종관리자 막는 서비스
	public String getAuthor(String id) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		String authorNum = memberDAO.getAuthor(id);
		
		close(con);
		
		return authorNum;
	}

}
