package vo;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String address_number;
	private String address;
	private String address_contents;
	private String email;
	private String birth;
	private String gender;
	private String grade;
	private int id_code;
	private String join_member;
	private int money;
	private int point;
	private String author;
	
	public Member() {}
	
	public Member(String id, String pw, String name, String address_number, String address, String address_contents,
			String email, String birth, String gender, String grade, int id_code, String join_member, int money,
			int point, String author) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address_number = address_number;
		this.address = address;
		this.address_contents = address_contents;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.grade = grade;
		this.id_code = id_code;
		this.join_member = join_member;
		this.money = money;
		this.point = point;
		this.author = author;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress_number() {
		return address_number;
	}
	public void setAddress_number(String address_number) {
		this.address_number = address_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_contents() {
		return address_contents;
	}
	public void setAddress_contents(String address_contents) {
		this.address_contents = address_contents;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getId_code() {
		return id_code;
	}
	public void setId_code(int id_code) {
		this.id_code = id_code;
	}
	public String getJoin_member() {
		return join_member;
	}
	public void setJoin_member(String join_member) {
		this.join_member = join_member;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
