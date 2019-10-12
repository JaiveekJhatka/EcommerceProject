package whole.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.dto.CartDTO;
import whole.model.dto.CartItemDTO;
import whole.model.dto.CustomerDTO;
import whole.model.dto.OrderDTO;
import whole.model.entity.Cart;
import whole.model.entity.CartItem;
import whole.model.entity.CompleteOrder;
import whole.model.entity.Customer;
import whole.model.entity.Product;
import whole.model.entity.User;
import whole.model.repository.CustomerRepository;
import whole.model.repository.ProductRepository;
import whole.model.repository.UserRepository;


@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	/* -----------------------------------------------   GET CART FUNCTION   ------------------------------------------------ */
	public CartDTO getCartByCustomerId(long userId) {
		return new CartDTO(customerRepo.getCartByCustomerId(userId));
	}
	
	
	/* ------------------------------------------------   GET PROFILE FUNCTION   ------------------------------------------------- */
	public CustomerDTO findById(long id) throws Exception{
		Customer customer = customerRepo.findById(id).orElseThrow(() -> new NullPointerException("No user found with the id " + id));
		return new CustomerDTO(customer);
	}
	
	
	/* ------------------------------------------------   UPDATE PROFILE FUNCTION   ---------------------------------------------- */
	public void updateProfile(CustomerDTO customerDto) throws Exception {
		long customerId = customerDto.getUserId();
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new NullPointerException("No user found with the id " + customerId));
		Optional<User> user = userRepo.findByUsername(customerDto.getEmail());
		if(user.isPresent()) {
			throw new RuntimeException("That email id is already registered to another user.");
		}
		customer.setName(customerDto.getName());
		customer.setPhone(customerDto.getPhone());
		customer.setUsername(customerDto.getEmail());
		customer.setAddress(customerDto.getAddress());
		customerRepo.save(customer);
	}
	
	
	/* -------------------------------------------------   ADD TO CART FUNCTION   ----------------------------------------------- */
	public CartItemDTO addToCart(long customerId, long productId) {
		
		CartItem cartItemReturn = null;
		long cartItemReturnId = 1;

		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new NullPointerException("No user found with the id: " + customerId));
		Product product = productRepo.findById(productId).orElseThrow(() -> new NullPointerException("No product found with the id: " + productId));
		
		Cart cart = customer.getCart();
		List<CartItem> cartItems = cart.getProducts();

		boolean found = false;
		for(CartItem cartItem : cartItems) {
			cartItemReturnId++;
			if(cartItem.getProduct().getProductId() == productId) {
				found = true;
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItemReturn = cartItem;
				break;
			}
		}
		if(!found) {
			cartItemReturn = new CartItem(product);
			cartItems.add(cartItemReturn);
		}
		customerRepo.save(customer);
		return new CartItemDTO(cartItemReturn, cartItemReturnId);
	}
	
	
	/* ----------------------------------------------   REMOVE FROM CART FUNCTION   --------------------------------------------- */
	public String removeFromCart(long customerId, long productId) {
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new NullPointerException("No user found with the id: " + customerId));

		Cart cart = customer.getCart();
		List<CartItem> cartItems = cart.getProducts();

		String output = "Product id: " + productId + " not found in cart. Was this a mistake or a test?";
		boolean found = false;
		for(Iterator<CartItem> itr = cartItems.listIterator(); (itr.hasNext() && !found); ) {
			CartItem cartItem = itr.next();
			if(cartItem.getProduct().getProductId() == productId) {
				itr.remove();
				found = true;
				output = cartItem.getProduct().getName() + " removed from cart successfully.";
			}
		}
		customerRepo.save(customer);
		return output;
	}
	
	
	/* -----------------------------------------------   CHANGE QUANTITY FUNCTION   --------------------------------------------- */
	public CartItemDTO changeQuantityOfProduct(long customerId, long productId, int quantity) {
		CartItem cartItemReturn = null;
		long cartItemReturnId = 1;
		if(quantity == 0) {
			throw new RuntimeException(removeFromCart(customerId, productId) + " Please use 'remove from cart' feature, instead of 'change quantity' for this purpose, in future.");
		}
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new NullPointerException("No user found with the id: " + customerId));
		Product product = productRepo.findById(productId).orElseThrow(() -> new NullPointerException("No product found with the id: " + productId));
		
		Cart cart = customer.getCart();
		List<CartItem> cartItems = cart.getProducts();

		boolean found = false;
		for(CartItem cartItem : cartItems) {
			cartItemReturnId++;
			if(cartItem.getProduct().getProductId() == productId) {
				found = true;
				cartItem.setQuantity(quantity);
				cartItemReturn = cartItem;
				break;
			}
		}
		if(!found) {
			cartItemReturn = new CartItem(product);
			cartItemReturn.setQuantity(quantity);
			cartItems.add(cartItemReturn);
		}
		customerRepo.save(customer);
		return new CartItemDTO(cartItemReturn, cartItemReturnId);
	}
	
	/* ---------------------------------------------   GET ORDER HISTORY FUNCTION   --------------------------------------------- */
	public List<OrderDTO> getOrderHistory(long id){
		List<CompleteOrder> orders = customerRepo.getOrderHistory(id);
		List<OrderDTO> orderDtos = new ArrayList<>();
		for(CompleteOrder order : orders) {
			orderDtos.add(new OrderDTO(order));
		}
		return orderDtos;
	}
	
	/* -----------------------------------------------   CREATE ORDER FUNCTION   ------------------------------------------------ */
	
}
