package whole.model.dto;


public class AddressDTO {
	private String street;
	private String city;
	private String state;
	private String pincode;
	
	
	/* ---------------------------------------------    CONSTRUCTORS    --------------------------------------------*/
	public AddressDTO() {
		this.street = "";
		this.city = "";
		this.state = "";
		this.pincode = "";
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	
	
	@Override
	public String toString() {
		return "AddressDTO [street=" + street + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
	
	
}
