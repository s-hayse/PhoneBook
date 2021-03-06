package phoneBook;

public class Address{

	//properties
	private String streetAdd;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber; 
	
	//parameterized constructor
	public Address (String streetAdd, String city, String state, String zipCode, String phoneNumber) {
		super();
		this.streetAdd = streetAdd;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
	}

	public String getStreetAdd() {
		return streetAdd;
	}

	public void setStreetAdd(String streetAdd) {
		this.streetAdd = streetAdd;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Address [streetAdd=" + streetAdd + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
				+ ", phoneNumber=" + phoneNumber + "]";
	}
}
