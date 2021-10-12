package dao;

import static db.Jdbcutil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;
import vo.MemberOrder;

public class MyPageDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
//	private MemberDAO() {}
	
	private static MyPageDAO memberDAO;
	
	public static MyPageDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new MyPageDAO();
		}
		
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
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
	
	/*회원 탈퇴*/
	public int memberDelete(String id) {
		sql="delete from member where id = ?";
		int memberDeleteCount=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			memberDeleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("memberDelete 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return memberDeleteCount;
	}
	
	/*돈 입금*/
	public int MemberSetMoney(String money, String id) {
		sql = "update member set money = money + ? where id = ?";
		int MemberSetMoneyCount=0;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, money);
			pstmt.setString(2, id);
			
			MemberSetMoneyCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("MemberSetMoney 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return MemberSetMoneyCount;
	}
	
	/*돈입금 session에 다시 담을 회원 조회*/
	public Member MemberSelect(String id) {
		sql = "select * from member where id = ?";
		Member member = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
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
			System.out.println("MemberSelect 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}
	
	/*회원 전체 조회 */
	public ArrayList<Member> getListMember(int page, int viewCount) {
		sql = "select * from member order by author desc, id_code asc limit ?,?";
		ArrayList<Member> memberList = new ArrayList<Member>();
		int startrow = (page-1)*viewCount;
		Member member= null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, viewCount);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setAddress_number(rs.getString("address_number"));
				member.setAddress(rs.getString("address"));
				member.setAddress_contents(rs.getString("address_contents"));
				member.setEmail(rs.getString("email"));
				member.setBirth(rs.getString("birth"));
				member.setGender(rs.getString("gender"));
				member.setGrade(rs.getString("grade"));
				member.setId_code(rs.getInt("id_code"));
				member.setJoin_member(rs.getString("join_member"));
				member.setMoney(rs.getInt("money"));
				member.setPoint(rs.getInt("point"));
				member.setAuthor(rs.getString("author"));
				
				memberList.add(member);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListMember 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return memberList;
	}
	
	//관리자 회원(개인) 조회
	public Member getMember(String id) {
		sql ="select * from member where id = ?";
		Member member = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);			
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
			System.out.println("getMember 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}
	
	/*관리자 회원(개인) 조회*/
	public ArrayList<Member> adminGetMember(String[] memberCheckId) {
		ArrayList<Member> memberList = new ArrayList<Member>();
		Member member = null;
		if (memberCheckId != null) {

			for (int i = 0; i < memberCheckId.length; i++) {
				sql = "select * from member where id = ?";
				try {
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, memberCheckId[i]);

					rs = pstmt.executeQuery();

					if (rs.next()) {
						member = new Member(rs.getString("id"), rs.getString("pw"), rs.getString("name"),
								rs.getString("address_number"), rs.getString("address"),
								rs.getString("address_contents"), rs.getString("email"), rs.getString("birth"),
								rs.getString("gender"), rs.getString("grade"), rs.getInt("id_code"),
								rs.getString("join_member"), rs.getInt("money"), rs.getInt("point"),
								rs.getString("author"));

						memberList.add(member);
					}

				} catch (Exception e) {
					System.out.println("adminGetMember 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
					close(rs);
				}
			}
		}
		return memberList;
	}
	
	/* 관리자 회원 체크박스 선택시 삭제 */
	public int arrMemberDelete(Member members) {
		int arrMemberDeleteCount = 0;
		sql = "delete from member where id =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, members.getId());

			arrMemberDeleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("arrMemberDelete 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return arrMemberDeleteCount;
	}

	//관리자 회원 포인트있음에도 Y일시 삭제
	public int arrMemberPointDelete(ArrayList<Member> memberList) {
		int arrMemberrPointDeleteCount=0;
		if(!(memberList.isEmpty())) {
			for(Member pointMember : memberList) {
				sql="delete from member where id = ?";
				try {
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, pointMember.getId());
					
					arrMemberrPointDeleteCount = pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("arrMemberDelete 오류 : "+e);
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			}
			return arrMemberrPointDeleteCount;
		}
		return arrMemberrPointDeleteCount;
	}
	
	/*전제 회원 수*/
	public int selectMemberListCount() {
		int MemberListCount=0;
		sql = "select count(*) from member";
		try {
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				MemberListCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("seletMemberListCount 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return MemberListCount;
	}

	/*관리자 조회 - 회원 권한 수정*/
	public int updateMemberAuthor(Member members, String memberUpdateAthor) {
		sql="update member set author = ? where id = ?";
		int updateMemberAuthorCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberUpdateAthor);
			pstmt.setString(2, members.getId());
			
			updateMemberAuthorCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateMemberAuthor 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateMemberAuthorCount;
	}

	/*관리자 조회 - 회원 등급 수정*/
	public int updateMemberGrade(Member members, String memberUpdateGrade) {
		sql="update member set grade = ? where id = ?";
		int updateMemberGradeCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberUpdateGrade);
			pstmt.setString(2, members.getId());
			
			updateMemberGradeCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateMemberAuthor 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateMemberGradeCount;		
	}
	
	/*상품 구매 내 내역 조회*/
	public ArrayList<MemberOrder> getListMyOrder(int page, int intviewCount, String id) {
		ArrayList<MemberOrder> myOrderList =  new ArrayList<MemberOrder>();
		MemberOrder memberOrder = null;
		int startrow = (page-1)*intviewCount;
		//date_sub(now(),interval 7 day) 뜻은 -7일 이라는것!!
		
		sql = "select m1.order_code, m1.order_price, m2.name, m2.choice, m1.order_count, m1.order_how, m1.order_date, m2.serial_code from member_order m1, "
			+ " (select serial_code, name, choice from Product) m2 "
			+ " where m1.serial_code=m2.serial_code and (order_date between date_sub(now(),interval 7 day) and now())and m1.id =? "
			+ "	group by m1.order_code, m2.serial_code, m2.name, m2.choice "
			+ " order by 7 desc limit ?,?";
		try {
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, intviewCount);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					memberOrder = new MemberOrder();
					
					memberOrder.setOrder_code(rs.getInt("m1.order_code"));
					memberOrder.setOrder_price(rs.getInt("m1.order_price"));
					memberOrder.setOrder_count(rs.getInt("m1.order_count"));
					memberOrder.setOrder_how(rs.getString("m1.order_how"));
					memberOrder.setOrder_date(rs.getString("m1.order_date"));
					memberOrder.setSerial_code(rs.getInt("m2.serial_code"));
					memberOrder.setOrder_code_name(rs.getString("m2.name"));
					memberOrder.setOrder_choice(rs.getString("m2.choice"));
										
					myOrderList.add(memberOrder);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListMyOrder 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return myOrderList;
	}
	
	/*내 주문 내역 총 갯수*/
	public int getListMyOrderCount(String id) {
		int ListMyOrderCount = 0;
		sql = "select count(*) from member_order m1, "
			+ " (select serial_code, name from Product) m2 "
			+ " where m1.serial_code=m2.serial_code and (order_date between date_sub(now(),interval 7 day) and now())and m1.id =? ";
		try {
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) ListMyOrderCount = rs.getInt(1);
				
		} catch (Exception e) {
			System.out.println("getListMyOrder 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return ListMyOrderCount;
	}
	
	/*member_order 관리자 내역(6개월) 출력*/
	public ArrayList<MemberOrder> allListOrder(int page, int intviewCount) {
		ArrayList<MemberOrder> memberAllOrderList = new ArrayList<MemberOrder>();
		MemberOrder memberOrder = null;
		int startrow = (page-1)*intviewCount;
		
		sql = " select m1.order_code, m1.order_price, m2.name, m1.order_count, m1.order_how, m1.order_date, m1.id, m2.serial_code from member_order m1, "
			+ " (select serial_code, name from Product) m2 "
			+ " where m1.serial_code=m2.serial_code and (order_date between date_sub(now(),interval 6 month) and now()) "
			+ "	group by m1.order_code, m2.serial_code, m2.name "
			+ " order by 6 desc limit ?,? ";
		try {
			pstmt= con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, intviewCount);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				memberOrder = new MemberOrder();
				
				memberOrder.setOrder_code(rs.getInt(1));
				memberOrder.setOrder_price(rs.getInt(2));
				memberOrder.setOrder_code_name(rs.getString(3));
				memberOrder.setOrder_count(rs.getInt(4));
				memberOrder.setOrder_how(rs.getString(5));
				memberOrder.setOrder_date(rs.getString(6));
				memberOrder.setId(rs.getString(7));
				memberOrder.setSerial_code(rs.getInt(8));
				
				memberAllOrderList.add(memberOrder);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("allListOrder 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberAllOrderList;
	}
	
	/*member_order 관리자 내역(6개월) 카운터*/
	public int allListOrderCount() {
		int allListOrderCount = 0;
		
		sql = " select count(*) as count from member_order m1, (select serial_code, name from Product) m2 "
			+ " where m1.serial_code=m2.serial_code and (order_date between date_sub(now(),interval 6 month) and now()) ";
		try {
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) allListOrderCount = rs.getInt("count");
			
		} catch (Exception e) {
			System.out.println("allListOrder 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return allListOrderCount;
		
	}

	//아이디 검색을 통해 그 회원 주문내역 조회
	public ArrayList<MemberOrder> searchIDListOrder(int page, int intviewCount, String searchID) {
		ArrayList<MemberOrder> memberSearchIDOrderList = new ArrayList<MemberOrder>();
		MemberOrder memberOrder = null;
		int startrow = (page-1)*intviewCount;
		
		sql = " select m1.order_code, m1.order_price, m2.name, m1.order_count, m1.order_how, m1.order_date, m1.id, m2.serial_code from member_order m1, "
				+ " (select serial_code, name from Product) m2 "
				+ " where m1.serial_code=m2.serial_code and (order_date between date_sub(now(),interval 6 month) and now()) and m1.id = ?"
				+ "	group by m1.order_code, m2.serial_code, m2.name "
				+ " order by 6 desc limit ?,?";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, searchID);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, intviewCount);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				memberOrder = new MemberOrder();
				
				memberOrder.setOrder_code(rs.getInt(1));
				memberOrder.setOrder_price(rs.getInt(2));
				memberOrder.setOrder_code_name(rs.getString(3));
				memberOrder.setOrder_count(rs.getInt(4));
				memberOrder.setOrder_how(rs.getString(5));
				memberOrder.setOrder_date(rs.getString(6));
				memberOrder.setId(rs.getString(7));
				memberOrder.setSerial_code(rs.getInt(8));
				
				memberSearchIDOrderList.add(memberOrder);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("allListOrder 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberSearchIDOrderList;
	}
	
	//아이디 검색을 통해 그 회원 주문내역 총 수
	public int searchIDListOrderCount(String searchID) {
		int searchIDListOrderCount = 0;
		sql = " select count(*) from member_order m1, (select serial_code, name from Product) m2 "
			+ " where m1.serial_code=m2.serial_code and (order_date between date_sub(now(),interval 6 month) and now()) and m1.id = ?";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, searchID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) searchIDListOrderCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("allListOrder 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return searchIDListOrderCount;
	}
	
	//회원 전체 돈
	public int getMemberMoney() {
		sql="select sum(money) from member";
		int totalMoney=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
		
			if(rs.next()) totalMoney = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("getMemberMoney 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return totalMoney;
	}

	//회원 전체 포인트
	public int getMemberPoint() {
		sql="select sum(point) from member";
		int totalPoint=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
		
			if(rs.next()) totalPoint = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("getMemberMoney 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return totalPoint;
	}
	
}
