package vo;

public class IO {
	private int IO_index;
	private String IO_inout;
	private int IO_count;
	private String IO_date;
	private int serial_code;
	
	public IO() {}

	public IO(int iO_index, String iO_inout, int iO_count, String iO_date, int serial_code) {
		super();
		IO_index = iO_index;
		IO_inout = iO_inout;
		IO_count = iO_count;
		IO_date = iO_date;
		this.serial_code = serial_code;
	}

	public int getIO_index() {
		return IO_index;
	}

	public void setIO_index(int iO_index) {
		IO_index = iO_index;
	}

	public String getIO_inout() {
		return IO_inout;
	}

	public void setIO_inout(String iO_inout) {
		IO_inout = iO_inout;
	}

	public int getIO_count() {
		return IO_count;
	}

	public void setIO_count(int iO_count) {
		IO_count = iO_count;
	}

	public String getIO_date() {
		return IO_date;
	}

	public void setIO_date(String iO_date) {
		IO_date = iO_date;
	}

	public int getSerial_code() {
		return serial_code;
	}

	public void setSerial_code_p(int serial_code) {
		this.serial_code = serial_code;
	}

	
}
