package whole.model.dto;

import whole.model.entity.CartItem;

public class CartItemDTO {
	private long cartItemId;
	private ProductDTO product;
	private int quantity;
	
	
	/* ---------------------------------------------    CONSTRUCTORS    --------------------------------------------*/
	public CartItemDTO() {
		cartItemId = 0;
		product = new ProductDTO();
		quantity = 0;
	}
	
	public CartItemDTO(CartItem cartItem, long cartItemId) {
		this.cartItemId = cartItemId;
		this.product = new ProductDTO(cartItem.getProduct());
		this.quantity = cartItem.getQuantity();
	}
	
	public long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
