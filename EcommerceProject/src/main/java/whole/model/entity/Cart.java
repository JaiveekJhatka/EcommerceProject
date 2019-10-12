package whole.model.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	@ElementCollection
	private List<CartItem> products;
	@OneToOne
	private Customer customer;
	
	public Cart() {
		products = new ArrayList<CartItem>();
	}
	public Cart(Customer customer) {
		products = new ArrayList<CartItem>();
		this.customer = customer;
	}
	
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public List<CartItem> getProducts() {
		return products;
	}
	public void setProducts(List<CartItem> products) {
		this.products = products;
	}
	
	
}
