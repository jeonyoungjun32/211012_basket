package vo;

public class MemberOrder {
	
	private int order_code;
	private int order_price;
	private int order_count;
	private String order_how;
	private String order_date;
	private String id;
	private int serial_code;
	
	private String order_code_name; /*상품 이름 추출*/
	private String order_choice; /*상품 종류*/
	
	public MemberOrder() {}

	public MemberOrder(int order_code, int order_price, int order_count, String order_how, String order_date, String id,
			int serial_code, String order_code_name, String order_choice) {
		super();
		this.order_code = order_code;
		this.order_price = order_price;
		this.order_count = order_count;
		this.order_how = order_how;
		this.order_date = order_date;
		this.id = id;
		this.serial_code = serial_code;
		this.order_code_name = order_code_name;
		this.order_choice = order_choice;
	}

	public int getOrder_code() {
		return order_code;
	}

	public void setOrder_code(int order_code) {
		this.order_code = order_code;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}

	public int getOrder_count() {
		return order_count;
	}

	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}

	public String getOrder_how() {
		return order_how;
	}

	public void setOrder_how(String order_how) {
		this.order_how = order_how;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSerial_code() {
		return serial_code;
	}

	public void setSerial_code(int serial_code) {
		this.serial_code = serial_code;
	}

	public String getOrder_code_name() {
		return order_code_name;
	}

	public void setOrder_code_name(String order_code_name) {
		this.order_code_name = order_code_name;
	}

	public String getOrder_choice() {
		return order_choice;
	}

	public void setOrder_choice(String order_choice) {
		this.order_choice = order_choice;
	}
	
}
