package whole.model.service;

import java.util.IllegalFormatException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.dto.OrderDTO;
import whole.model.dto.OrderItemDTO;
import whole.model.entity.Cart;
import whole.model.entity.CartItem;
import whole.model.entity.CompleteOrder;
import whole.model.entity.Customer;
import whole.model.repository.CompleteOrderRepository;
import whole.model.repository.CustomerRepository;

@Service
public class OrderService {
	@Autowired
	CompleteOrderRepository orderRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	public OrderDTO createOrder(long customerId) throws Exception {
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new RuntimeException("No customer with id: " + customerId + " found"));
		Cart cart = customer.getCart();
		if(cart.getProducts().isEmpty()) {
			throw new RuntimeException("No items currently in cart. You ordering a visit from our delivery guy?");
		}
		CompleteOrder order = new CompleteOrder(customer);
		List<String> orderItems = order.getOrderItemDtoJsons();
		for(CartItem cartItem : cart.getProducts()) {
			OrderItemDTO oItemDto = new OrderItemDTO(cartItem);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonOrderItemDto = mapper.writeValueAsString(oItemDto);
			orderItems.add(jsonOrderItemDto);	
		}
		
		cart.getProducts().clear();
		return new OrderDTO(orderRepo.save(order));
	}
}
