package vo;

public class Cart {
	private int serial_code;		//상품 코드
	private String name;			//상품 이름
	private int count;				//상품 수량
	private int price;				//상품 가격
	private String encodingName;	//인코딩 이름
	private String kinds;			//상품 종류
	
	public Cart() {}
	
	public Cart(int serial_code, String name, int count, int price, String encodingName, String kinds) {
		super();
		this.serial_code = serial_code;
		this.name = name;
		this.count = count;
		this.price = price;
		this.encodingName = encodingName;
		this.kinds = kinds;
	}

	public int getSerial_code() {
		return serial_code;
	}

	public void setSerial_code(int serial_code) {
		this.serial_code = serial_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getEncodingName() {
		return encodingName;
	}

	public void setEncodingName(String encodingName) {
		this.encodingName = encodingName;
	}

	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	
}
