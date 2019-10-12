package whole.model.dto;

import java.util.ArrayList;
import java.util.List;

import whole.model.entity.Cart;
import whole.model.entity.CartItem;


public class CartDTO {
	private long cartId;
	private List<CartItemDTO> products;
	
	/* ---------------------------------------------    CONSTRUCTORS    --------------------------------------------*/
	public CartDTO() {
		products = new ArrayList<CartItemDTO>();
	}
	public CartDTO(Cart cart) {
		this.cartId = cart.getCartId();
		List<CartItem> cartItems = cart.getProducts();
		this.products = new ArrayList<CartItemDTO>();
		long iterator = 1;
		for(CartItem cartItem : cartItems) {
			this.products.add(new CartItemDTO(cartItem, iterator));
			iterator++;
		}
	}
	
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public List<CartItemDTO> getProducts() {
		return products;
	}
	public void setProducts(List<CartItemDTO> products) {
		this.products = products;
	}
	
	
}
