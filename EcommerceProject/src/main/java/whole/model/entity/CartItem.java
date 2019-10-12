package whole.model.entity;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Embeddable
public class CartItem {
	@ManyToOne
	private Product product;
	private int quantity;
	
	public CartItem() {
		product = new Product();
	}
	
	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1;
	}
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
