package whole.model.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import whole.model.enums.OrderStatus;


@Entity
public class CompleteOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ElementCollection
	private List<String> orderItemDtoJsons;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@ManyToOne
	private Customer customer;
	
	public CompleteOrder() {
		this.orderItemDtoJsons = new ArrayList<String>();
		this.orderStatus = OrderStatus.PROCESSING;
		this.customer = new Customer();
	}
	
	public CompleteOrder(Customer customer) {
		this.orderItemDtoJsons = new ArrayList<String>();
		this.orderStatus = OrderStatus.PROCESSING;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<String> getOrderItemDtoJsons() {
		return orderItemDtoJsons;
	}
	public void setOrderItemDtoJsons(List<String> orderItemDtoJsons) {
		this.orderItemDtoJsons = orderItemDtoJsons;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
}
