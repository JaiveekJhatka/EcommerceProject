package whole.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.entity.CompleteOrder;



public class OrderDTO {
	private long orderId;
	private List<OrderItemDTO> products;
	private String orderStatus;
	
	public OrderDTO() {
		orderId = 0;
		products = new ArrayList<OrderItemDTO>();
		orderStatus = "Default";
	}
	public OrderDTO(CompleteOrder order) {
		this.orderId = order.getId();
		this.orderStatus = order.getOrderStatus().name();
		
		this.products = new ArrayList<OrderItemDTO>();
		ObjectMapper objMapper = new ObjectMapper();
		for(String itemJson : order.getOrderItemDtoJsons()) {
			try {
				OrderItemDTO orderItemDto = objMapper.readValue(itemJson, OrderItemDTO.class);
				this.products.add(orderItemDto);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public List<OrderItemDTO> getProducts() {
		return products;
	}
	public void setProducts(List<OrderItemDTO> products) {
		this.products = products;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
