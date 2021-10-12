package vo;

public class Product {
	private int serial_code;
	private String name;
	private int kcal;
	private String allergy;
	private int price;
	private String choice;
	private String status;
	private String PI_date;
	private int count;
	private String id;
	
	/*고객이 주문한 결과 갯수*/
	private int resultCount;
	
	public Product() {}

	public Product(int serial_code, String name, int kcal, String allergy, int price, String choice, String status,
			String pI_date, int count, String id) {
		super();
		this.serial_code = serial_code;
		this.name = name;
		this.kcal = kcal;
		this.allergy = allergy;
		this.price = price;
		this.choice = choice;
		this.status = status;
		PI_date = pI_date;
		this.count = count;
		this.id = id;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int result) {
		this.resultCount = result;
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

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPI_date() {
		return PI_date;
	}

	public void setPI_date(String pI_date) {
		PI_date = pI_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
