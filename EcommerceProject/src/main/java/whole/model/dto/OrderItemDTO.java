package whole.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.entity.CartItem;

public class OrderItemDTO {
	private long productId;
	private String productName;
	private long priceEach;
	private String details;
	private int quantity;
	
	public OrderItemDTO() {
	}
	public OrderItemDTO(CartItem cartItem) {
		this.productId = cartItem.getProduct().getProductId();
		this.productName = cartItem.getProduct().getName();
		this.priceEach = cartItem.getProduct().getPrice();
		this.details = cartItem.getProduct().getDetails();
		this.quantity = cartItem.getQuantity();
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getPriceEach() {
		return priceEach;
	}
	public void setPriceEach(long priceEach) {
		this.priceEach = priceEach;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
