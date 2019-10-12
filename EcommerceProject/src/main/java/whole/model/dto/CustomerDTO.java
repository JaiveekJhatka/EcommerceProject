package whole.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.entity.Customer;


public class CustomerDTO {
	private long userId;
	private String name;
	private String email;
	private String phone;
	private AddressDTO address;
	
	/* ---------------------------------------------    CONSTRUCTORS    --------------------------------------------*/
	public CustomerDTO() {
		userId = 0;
		name = "Default name";
		email = "Default email";
		phone = "Default phone";
		address = new AddressDTO();
	}
	public CustomerDTO(Customer customer) throws Exception {
		this.userId = customer.getId();
		this.name = customer.getName();
		this.email = customer.getUsername();
		this.phone = customer.getPhone();
		String addressJson = customer.getAddress();
		ObjectMapper objMapper = new ObjectMapper();
		address = objMapper.readValue(addressJson, AddressDTO.class);
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}	
}
