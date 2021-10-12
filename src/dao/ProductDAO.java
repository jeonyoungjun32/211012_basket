package dao;

import static db.Jdbcutil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Cart;
import vo.IO;
import vo.Member;
import vo.Product;

public class ProductDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

//	private MemberDAO() {}

	private static ProductDAO memberDAO;

	public static ProductDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new ProductDAO();
		}

		return memberDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	/* 관리자 물건 등록 */ //이걸로 상품코드 만들어냈다잉
	public int insertProduct(Product product, String id) {
		sql = "select date_format(sysdate(),'%m%d%i%S')";
		int serial_code = 0;
		String product_status = "O";
		int insertCount = 0;
		int product_count = 0;
		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next())
				serial_code = rs.getInt(1);

			sql = "insert into Product values(?,?,?,?,?,?,?,now(),?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setLong(1, serial_code);
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getKcal());
			pstmt.setString(4, product.getAllergy());
			pstmt.setInt(5, product.getPrice());
			pstmt.setString(6, product.getChoice());
			pstmt.setString(7, product_status);
			pstmt.setInt(8, product_count);
			pstmt.setString(9, id);

			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("insertProduct 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}

	//상품 총 갯수 출력
	public int getAllListCount() {
		int allListCount = 0;
		sql ="select count(*) from Product";
		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				allListCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getAllListCount 2 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return allListCount;
	}
	
	//각 상품의 총 갯수 출력
	public int getProductListCount(int intViewSelect) {
		int productListCount = 0;
		/* 상품 */
		sql = "select count(*) from Product where choice =?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, intViewSelect);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				productListCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getAllListCount 2 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return productListCount;
	}

	// 상품 조회 페이지 뷰 처리
	public ArrayList<Product> getProductListSelect(int page, int intviewCount, int intViewSelect) {
		ArrayList<Product> productList = new ArrayList<Product>();
		int startrow = (page - 1) * intviewCount;
		String strViewSelect = Integer.toString(intViewSelect);
		Product product = null;
		try {
			sql = "select serial_code, name, kcal, allergy, price, choice, status, PI_date, count, id from Product where choice = ? order by 1 asc limit ?,?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, strViewSelect);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, intviewCount);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					product = new Product();
					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(Integer.parseInt(rs.getString("kcal")));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(Integer.parseInt(rs.getString("count")));
					product.setId(rs.getString("id"));

					productList.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getProductListSelect 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}

	/*상품 전체 뷰 처리*/
	public ArrayList<Product> getProductAllListSelect(int page, int intviewCount) {
		ArrayList<Product> productList = new ArrayList<Product>();
		int startrow = (page - 1) * intviewCount;
		Product product = null;
		try {
			sql = "select serial_code, name, kcal, allergy, price, choice, status, PI_date, count, id from Product limit ?,?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startrow);
			pstmt.setInt(2, intviewCount);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					product = new Product();
					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(Integer.parseInt(rs.getString("kcal")));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(Integer.parseInt(rs.getString("count")));
					product.setId(rs.getString("id"));

					productList.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getProductListSelect 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}
	
	// 아이스케잌 조회
	public ArrayList<Product> getListIceCake() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> iceCakeList = new ArrayList<Product>();
		Product product = null;
		String product_choice = "1";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, product_choice);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					product = new Product();

					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(Integer.parseInt(rs.getString("kcal")));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(Integer.parseInt(rs.getString("count")));
					product.setId(rs.getString("id"));

					iceCakeList.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListIceCake 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return iceCakeList;
	}

	// 음료 조회
	public ArrayList<Product> getListBeverage() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> beverageList = new ArrayList<Product>();
		Product product = null;
		String product_choice = "2";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, product_choice);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					product = new Product();

					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(Integer.parseInt(rs.getString("kcal")));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(Integer.parseInt(rs.getString("count")));
					product.setId(rs.getString("id"));

					beverageList.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListBeverage 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return beverageList;
	}

	// 커피 조회
	public ArrayList<Product> getListCoffee() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> coffeeList = new ArrayList<Product>();
		Product product = null;
		String product_choice = "3";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, product_choice);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					product = new Product();

					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(Integer.parseInt(rs.getString("kcal")));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(Integer.parseInt(rs.getString("count")));
					product.setId(rs.getString("id"));

					coffeeList.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListCoffee 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return coffeeList;
	}

	// 디저트 조회
	public ArrayList<Product> getListDessert() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> dessertList = new ArrayList<Product>();
		Product product = null;
		String product_choice = "4";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, product_choice);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					product = new Product();

					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(Integer.parseInt(rs.getString("kcal")));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(Integer.parseInt(rs.getString("count")));
					product.setId(rs.getString("id"));

					dessertList.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListDessert 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return dessertList;
	}

	// 아이스크림 조회
	public ArrayList<Product> getListIcecream() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> icecreamList = new ArrayList<Product>();
		Product product = null;
		String product_choice = "5";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, product_choice);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					product = new Product();

					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(Integer.parseInt(rs.getString("kcal")));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(Integer.parseInt(rs.getString("count")));
					product.setId(rs.getString("id"));

					icecreamList.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListIceCake 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return icecreamList;
	}

	// 상품 코드로 상품 조회 (1개)
	public Product getFindCode(int serial_code) {
		Product product = null;
		try {
			sql = "select * from Product where serial_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, serial_code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new Product();
				product.setSerial_code(rs.getInt("serial_code"));
				product.setName(rs.getString("name"));
				product.setKcal(rs.getInt("kcal"));
				product.setAllergy(rs.getString("allergy"));
				product.setPrice(rs.getInt("price"));
				product.setChoice(rs.getString("choice"));
				product.setStatus(rs.getString("status"));
				product.setPI_date(rs.getString("PI_date"));
				product.setCount(rs.getInt("count"));
				product.setId(rs.getString("id"));
			}

		} catch (Exception e) {
			System.out.println("getFindCode 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return product;
	}

	// 상품 정보 수정
	public int productUpdate(int serial_code, String name, String allergy, int kcal, int price) {
		String strserial = String.valueOf(serial_code);
		strserial = strserial.substring(0, 1);

		int productUpdateCount = 0;
		try {
			sql = "update Product set name = ?, kcal = ?, allergy = ?, price = ? where serial_code = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, kcal);
			pstmt.setString(3, allergy);
			pstmt.setInt(4, price);
			pstmt.setInt(5, serial_code);

			productUpdateCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ProductUpdate 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return productUpdateCount;
	}

	// 상품리스트에서 체크한것을 찾기
	public ArrayList<Product> adminGetProduct(String[] productCheckCode) {
		ArrayList<Product> productList = new ArrayList<Product>();
		Product product = null;

		for (int i = 0; i < productCheckCode.length; i++) {
			try {
				sql = "select * from Product where serial_code = ?";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, productCheckCode[i]);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					product = new Product(rs.getInt("serial_code"), rs.getString("name"), rs.getInt("kcal"),
							rs.getString("allergy"), rs.getInt("price"), rs.getString("choice"), rs.getString("status"),
							rs.getString("PI_date"), rs.getInt("count"), rs.getString("id"));

					productList.add(product);
				}

			} catch (Exception e) {
				System.out.println("adminGetProduct 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
		}
		return productList;
	}

	// 상품 체크박스 선택시 삭제
	public int ProductAllDelete(Product product) {
		int productAllDeleteCount = 0;

		try {
			sql = "delete from Product where serial_code =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product.getSerial_code());
			productAllDeleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ProductAllDelete 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return productAllDeleteCount;
	}

	// 상품 체크박스 선택한 정보 가져오기
	public ArrayList<Product> arrProductInfo(String[] productCheckCode) {
		ArrayList<Product> productList = new ArrayList<Product>();
		Product product = null;
		if (productCheckCode != null) {

			for (int i = 0; i < productCheckCode.length; i++) {
				sql = "select * from Product where serial_code_p = ?";
				try {
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, productCheckCode[i]);

					rs = pstmt.executeQuery();

					if (rs.next()) {
						product = new Product(rs.getInt("serial_code_p"), rs.getString("name"), rs.getInt("kcal"),
								rs.getString("allergy"), rs.getInt("price"), rs.getString("choice"),
								rs.getString("status"), rs.getString("PI_date"), rs.getInt("count"),
								rs.getString("id"));

						productList.add(product);
					}

				} catch (Exception e) {
					System.out.println("arrProductInfo 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
					close(rs);
				}
			}
		}
		return productList;
	}

	// IO 등록(입고)
	// serial_code : 상품 코드 / product_count : 상품 갯수
	public int AdminIOinsert(int[] intSerial_code, int[] product_count) {
		int IOcount = 0;
		String IO_status = "I";

		int product_insert_count = 0;
		int product_update_count = 0;
		int ReturnValue = 0;

		for (int i = 0; i < intSerial_code.length; i++) {
			try {
				/* 입고 번호 */
				sql = "select ifnull(max(IO_index),0)+1 from IO";

				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();

				if (rs.next())
					IOcount = rs.getInt(1);

			} catch (Exception e) {
				System.out.println("AdminIOupdate 1-1 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			sql = "insert into IO values (?,?,?,now(),?)";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, IOcount);
				pstmt.setString(2, IO_status);
				pstmt.setInt(3, product_count[i]);
				pstmt.setInt(4, intSerial_code[i]);

				product_insert_count = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("arrAdminIOupdate 1-2 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			sql = "update Product set status = ?, count = count + ? where serial_code = ?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, IO_status);
				pstmt.setInt(2, product_count[i]);
				pstmt.setInt(3, intSerial_code[i]);

				product_update_count = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("arrAdminIOupdate 1-3 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}

		if ((product_insert_count > 0 && product_update_count > 0)) {
			ReturnValue = 1;
		}

		return ReturnValue;
	}

	// 주문에서 선택한 상품을 찾아서 상세내역에 돌려주기
	public ArrayList<Product> getProductListDetail(int[] intCode) {
		ArrayList<Product> productList = new ArrayList<Product>();
		Product product = null;

		for (int i = 0; i < intCode.length; i++) {
			try {
				sql = "select * from Product where serial_code = ?";
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, intCode[i]);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					product = new Product();
					product.setSerial_code(rs.getInt("serial_code"));
					product.setName(rs.getString("name"));
					product.setKcal(rs.getInt("kcal"));
					product.setAllergy(rs.getString("allergy"));
					product.setPrice(rs.getInt("price"));
					product.setChoice(rs.getString("choice"));
					product.setStatus(rs.getString("status"));
					product.setPI_date(rs.getString("PI_date"));
					product.setCount(rs.getInt("count"));
					product.setId(rs.getString("id"));

					productList.add(product);
				}
			} catch (Exception e) {
				System.out.println("getProductListDetail : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
		}
		return productList;
	}

	// 주문 내역 하나씩 등록
	public int insertOrder(ArrayList<Product> productList, int[] Count, Member member, String howchoice) {
		int insertCount = 0;
		for (int i = 0; i < productList.size(); i++) {
			sql = "select ifnull(max(order_code),0)+1 from member_order";
			insertCount = 0;
			int order_code = 0;
			try {

				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();

				if (rs.next())
					order_code = Integer.parseInt(rs.getString(1));

				sql = "insert into member_order values (?,?,?,?,now(),?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, order_code);
				pstmt.setInt(2, productList.get(i).getPrice());
				pstmt.setInt(3, Count[i]);
				pstmt.setString(4, howchoice);
				pstmt.setString(5, member.getId());
				pstmt.setInt(6, productList.get(i).getSerial_code());

				insertCount = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("insertOrder 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
		}
		return insertCount;
	}

	// 주문 내역 결과에대한 출고
	public int insertIO_Out(ArrayList<Product> productList, int[] count) {
		int i = 0;
		for (i = 0; i < productList.size(); i++) {
			i++;
		}
		
		int IntCode[] = new int[i];

		int IOcount = 0;
		for (i = 0; i < productList.size(); i++) {
			IntCode[i] = productList.get(i).getSerial_code();
		}

		String IO_status = "O";
		int product_insert_count = 0;
		int product_update_count = 0;

		for (i = 0; i < productList.size(); i++) {
			try {
				sql = "select ifnull(max(IO_index),0)+1 from IO";

				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();

				if (rs.next())
					IOcount = rs.getInt(1);

			} catch (Exception e) {
				System.out.println("insertIO_Out 번호 지정 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}

			try {
				sql = "insert into IO values (?,?,?,now(),?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, IOcount);
				pstmt.setString(2, IO_status);
				pstmt.setInt(3, count[i]);
				pstmt.setInt(4, productList.get(i).getSerial_code());

				product_insert_count = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("insertIO_Out insert 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			if(productList.get(i).getCount()-count[i] == 0) {
				sql = "update Product set status = ?, count = count - ? where serial_code = ?";

				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, IO_status);
					pstmt.setInt(2, count[i]);
					pstmt.setInt(3, productList.get(i).getSerial_code());

					product_update_count = pstmt.executeUpdate();

				} catch (Exception e) {
					System.out.println("insertIO_Out product 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			} else {
				sql = "update Product set count = count - ? where serial_code = ?";

				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, count[i]);
					pstmt.setInt(2, productList.get(i).getSerial_code());

					product_update_count = pstmt.executeUpdate();

				} catch (Exception e) {
					System.out.println("insertIO_Out product 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			}
		}
		int ReturnValue = 0;
		if (product_insert_count > 0 && product_update_count > 0) {
			ReturnValue = 1;
		}
		return ReturnValue;
	}

	/* 상품 구매후 돈 계산 파트 */
	public int moneyUpdateMember(Member member, int resultMoney, int resultPoint) {
		sql = "update member set money = money - ?, point = point + ? where id = ?";
		int moneyUpdateCount = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, resultMoney);
			pstmt.setInt(2, resultPoint);
			pstmt.setString(3, member.getId());

			moneyUpdateCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("moneyUpdateMember 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return moneyUpdateCount;

	}

	//회원 포인트 전부와 돈 사용
	public int moneyAndPointUpdateMember(Member member, int resultMoney, int resultPoint) {
		if(member.getPoint() >= resultMoney) {
			sql = "update member set money = money - (? - ?), point = (point + ?)- ? where id = ?";
		} else if(member.getPoint() <= resultMoney) {
			sql = "update member set money = money + (? - ?), point = (point + ?)- ? where id = ?";
		}
		int moneyUpdateCount = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, member.getPoint());
			pstmt.setInt(2, resultMoney);
			pstmt.setInt(3, resultPoint);
			pstmt.setInt(4, member.getPoint());
			pstmt.setString(5, member.getId());

			moneyUpdateCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("moneyAndPotintUpdateMember 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return moneyUpdateCount;
	}
	
	// IO 리스트 출력
	public ArrayList<IO> IOList(int page, int intviewCount) {
		sql = "select * from IO order by IO_date desc limit ?,?";
		int startrow = (page - 1) * intviewCount;
		IO io = null;
		ArrayList<IO> ioList = new ArrayList<IO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, intviewCount);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					io = new IO(rs.getInt("IO_index"), rs.getString("IO_inout"), rs.getInt("IO_count"),
							rs.getString("IO_date"), rs.getInt("serial_code"));

					ioList.add(io);
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("selectIOlist 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return ioList;
	}

	/* IO 총 갯수 */
	public int IOListCount() {
		int IOListCount = 0;
		sql = "select count(*) from IO";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				IOListCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("selectIOListCount 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return IOListCount;
	}

	/* 입출고 총 갯수 출력 */
	public int selectIOListCount(String iO_select) {
		int IOListCount = 0;
		sql = "select count(*) from IO where IO_inout = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iO_select);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				IOListCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("selectIOListCount 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return IOListCount;
	}
	
	/*수정중*/
	public ArrayList<IO> selectIOList(int page, int intviewCount, String iO_select) {
		sql = "select * from IO where IO_inout = ? order by IO_date desc limit ?,?";
		int startrow = (page - 1) * intviewCount;
		IO io = null;
		ArrayList<IO> ioList = new ArrayList<IO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iO_select);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, intviewCount);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					io = new IO(rs.getInt("IO_index"), rs.getString("IO_inout"), rs.getInt("IO_count"),
							rs.getString("IO_date"), rs.getInt("serial_code"));

					ioList.add(io);
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("selectIOlist 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return ioList;
	}

	/*주문 취소*/
	public int deleteOrder(String order_code) {
		int deleteCount = 0;
		sql = "delete from member_order where order_code = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_code);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteOrder 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	/*취소할 상품 정보 얻기*/
	public Product getProductInfo(String order_code_name) {
		sql = "select * from Product where name = ?";
		Product product = null;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, order_code_name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new Product();

				product.setSerial_code(rs.getInt("serial_code"));
				product.setName(rs.getString("name"));
				product.setKcal(Integer.parseInt(rs.getString("kcal")));
				product.setAllergy(rs.getString("allergy"));
				product.setPrice(Integer.parseInt(rs.getString("price")));
				product.setChoice(rs.getString("choice"));
				product.setStatus(rs.getString("status"));
				product.setPI_date(rs.getString("PI_date"));
				product.setCount(Integer.parseInt(rs.getString("count")));
				product.setId(rs.getString("id"));

			}
		} catch (Exception e) {
			System.out.println("getProductInfo 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return product;
		
	}

	/*주문 취소내역 다시 입고처리*/
	public int deleteIO_Out(Product product, int count) {
		int IOcount = 0;
		String IO_status = "I";
		int product_insert_count = 0;
		int product_update_count = 0;
		try {
			sql = "select ifnull(max(IO_index),0)+1 from IO";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				IOcount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("deleteIO_Out 번호 지정 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		try {
			sql = "insert into IO values (?,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, IOcount);
			pstmt.setString(2, IO_status);
			pstmt.setInt(3, count);
			pstmt.setInt(4, product.getSerial_code());
			product_insert_count = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteIO_Out insert 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		sql = "update Product set status = ?, count = count + ? where serial_code = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, IO_status);
			pstmt.setInt(2, count);
			pstmt.setInt(3, product.getSerial_code());

			product_update_count = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("deleteIO_Out product 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		int ReturnValue = 0;
		if (product_insert_count>0&&product_update_count>0) {
			ReturnValue = 1;
		}
		return ReturnValue;
	}

	/* 상품 취소로인한 환불요청 */
	public int moneyRefundMember(Member member, int price) {
		sql = "update member set money = money + ?, point = point - ? where id = ?";
		int resultPoint = price/100;
		int moneyRefundCount = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, price);
			pstmt.setInt(2, resultPoint);
			pstmt.setString(3, member.getId());

			moneyRefundCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("moneyRefundMember 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return moneyRefundCount;
	}

	/*장바구니에 담긴 이름을 product으로 옮기는 작업*/
	public ArrayList<Product> getProductInfo(ArrayList<Cart> cartList) {
		ArrayList<Product> productList = new ArrayList<Product>();
		Product product = null;
		for (Cart cart : cartList) {
			sql = "select * from Product where name = ?";
			try {
				pstmt = con.prepareStatement(sql);

				
				pstmt.setString(1, cart.getName());

				rs = pstmt.executeQuery();

				if(rs.next()) {
					product = new Product();
					
					product.setSerial_code(rs.getInt(1));
					product.setName(rs.getString(2));
					product.setKcal(rs.getInt(3));
					product.setAllergy(rs.getString(4));
					product.setPrice(rs.getInt(5));
					product.setChoice(rs.getString(6));
					product.setStatus(rs.getString(7));
					product.setPI_date(rs.getString(8));
					product.setCount(rs.getInt(9));
					product.setId(rs.getString(10));
					
					productList.add(product);
				}
				
			} catch (Exception e) {
				System.out.println("getProductInfo 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
		}
		return productList;
		
	}

	/*상품 등록할때 동일한 이름의 상품이 있는지 확인*/
	public String getProductConfirmInfo(String name) {
		sql="select name from Product where name = ?";
		String resultName = "";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			if(rs.next()) resultName = rs.getString(1);  
		} catch (Exception e) {
			System.out.println("getProductConfirmInfo 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return resultName;
	}

	/*장바구니에 담을 상품 번호로 찾기*/
	public Product selectBaskeyCart(int serial_code) {
		Product product = null;

		try {
			sql = "select * from Product where serial_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, serial_code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new Product(rs.getInt("serial_code"), rs.getString("name"), rs.getInt("kcal"),
						rs.getString("allergy"), rs.getInt("price"), rs.getString("choice"), rs.getString("status"),
						rs.getString("PI_date"), rs.getInt("count"), rs.getString("id"));
			}
		} catch (Exception e) {
			System.out.println("selectBaskeyCart 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return product;
	}

}
