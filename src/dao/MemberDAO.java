package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

import static db.Jdbcutil.*;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
//	private MemberDAO() {}
	
	private static MemberDAO memberDAO;
	
	public static MemberDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//회원 등록
	public int insertMember(Member member) {
		sql = "select concat(date_format(sysdate(),'%y%m%d%i%S')) from member";
		int insertmember=0;
		int id_code = 0;
		String grade = "1";
		String author = "1";
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) id_code = rs.getInt(1);
			
			sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
				
			pstmt.setString(1,member.getId());
			pstmt.setString(2,member.getPw());
			pstmt.setString(3,member.getName());
			pstmt.setString(4,member.getAddress_number());
			pstmt.setString(5,member.getAddress());
			pstmt.setString(6,member.getAddress_contents());
			pstmt.setString(7,member.getEmail());
			pstmt.setString(8,member.getBirth());
			pstmt.setString(9,member.getGender());
			pstmt.setString(10,grade);
			pstmt.setInt(11,id_code);
			pstmt.setInt(12,0);
			pstmt.setInt(13,0);
			pstmt.setString(14,author);
			
			insertmember = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertMember 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertmember;
	}
	
	/*아이디 중복 확인*/
	public int idCheckMember(String id) {
		sql = "select id from member where id = ?";
		int IdCheck = -1;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				IdCheck = 0;
			} else {
				IdCheck = 1;
			}
			
		} catch (Exception e) {
			System.out.println("IdCHheckMember 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return IdCheck;
	}
	public String memberGetID(String id) {
		sql = "select id from member where id =?";
		String getId ="";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) getId = rs.getString(1);
			
		} catch (Exception e) {
			System.out.println("memberLogin 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return getId;
	}
	
	//회원 로그인
	public Member memberLogin(String id, String pw) {
		//회원 찾기
		sql = "select * from member where id =? and pw = ?";
		Member member = null;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				member = new Member(
						rs.getString("id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getString("address_number"),
						rs.getString("address"),
						rs.getString("address_contents"),
						rs.getString("email"),
						rs.getString("birth"),
						rs.getString("gender"),
						rs.getString("grade"),
						rs.getInt("id_code"),
						rs.getString("join_member"),
						rs.getInt("money"),
						rs.getInt("point"),
						rs.getString("author"));
			}
		} catch (Exception e) {
			System.out.println("memberLogin 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return member;
	}
	
	/*로그인 아이디 찾기*/
	public String memberIDFind(String member_name, String member_email) {
		sql = "select id from member where name = ? and email = ?";
		String id = "";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member_name);
			pstmt.setString(2, member_email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("id");
			}			
		} catch (Exception e) {
			System.out.println("memberIDFind 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}
	
	/*로그인 찾은 아이디 비밀번호 바꾸기*/
	public int memberIDRevise(String id, String pw) {
		sql="update member set pw = ?  where id = ?";
		int memberIDReviseSuccess = 0;
		try {
			sql="update member set pw = ?  where id = ?";
				
			pstmt=con.prepareStatement(sql);
				
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
				
			memberIDReviseSuccess = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("memberIDRevise 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberIDReviseSuccess;
	}
	
	/*로그인 비밀번호 찾을 아이디 검색*/
	public String memberIDFindPW(String id, String email) {
		sql = "select pw from member where id = ? and email = ?";
		String pw = "";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pw = rs.getString("pw");
			}			
		} catch (Exception e) {
			System.out.println("memberIDFind 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return pw;
	}
	
	/*회원 정보 수정*/
	public int memberUpdate(Member member) {
		sql = "update member set pw = ?, address_number =?, address=?, address_contents=?,"
			+ " email = ?, birth=?, gender = ? where id = ? ";
		int memberUpdateCount=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,member.getPw());
			pstmt.setString(2,member.getAddress_number());
			pstmt.setString(3,member.getAddress());
			pstmt.setString(4,member.getAddress_contents());
			pstmt.setString(5,member.getEmail());
			pstmt.setString(6,member.getBirth());
			pstmt.setString(7,member.getGender());
			pstmt.setString(8,member.getId());
			
			memberUpdateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("memberUpdate 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return memberUpdateCount;
	}

	/*최종 관리자 정보 막기*/
	public String getAuthor(String id) {
		String authorNum = "";
		sql = "select author from member where id =? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) authorNum = rs.getString(1);
		} catch (Exception e) {
			System.out.println("getAuthor 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return authorNum;
	}


}
