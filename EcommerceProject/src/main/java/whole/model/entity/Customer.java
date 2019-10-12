package whole.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.dto.AddressDTO;
import whole.model.dto.SignupRequest;


@Entity
public class Customer extends User{
	private String phone;
	private String address;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<CompleteOrder> orders;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	private Cart cart;
	
	
	/* ---------------------------------------------    CONSTRUCTORS    --------------------------------------------*/
	public Customer() {
		phone = "Default phone";
		address = "Default address";
		orders = new ArrayList<CompleteOrder>();
		cart = new Cart(this);
	}
	public Customer(SignupRequest signup) throws JsonProcessingException{
		super(signup, "USER");
		this.phone = "";
		AddressDTO addressDto = new AddressDTO();
		ObjectMapper objMapper = new ObjectMapper();
		this.address = objMapper.writeValueAsString(addressDto);
		orders = new ArrayList<CompleteOrder>();
		cart = new Cart(this);
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setAddress(AddressDTO addressDto) throws JsonProcessingException{
		ObjectMapper objMapper = new ObjectMapper();
		this.address = objMapper.writeValueAsString(addressDto);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<CompleteOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<CompleteOrder> orders) {
		this.orders = orders;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
}
