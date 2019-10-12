package whole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.dto.CustomerDTO;
import whole.model.dto.QuantityDTO;
import whole.model.dto.ResultResponse;
import whole.model.service.CustomerService;


@RestController
public class CustomerController {
	@Autowired
	CustomerService customerServices;
	
	@RequestMapping(value = "/getprofile/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> signup(@PathVariable(value="id") String userIdStr) {
		long userId;
		try {
			userId = Long.parseLong(userIdStr);
			return new ResponseEntity<>(customerServices.findById(userId), HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<Object> updateProfile(@RequestBody String customerInfo) {
		try{
			ObjectMapper objMapper = new ObjectMapper();
			CustomerDTO customerDto = objMapper.readValue(customerInfo, CustomerDTO.class);
			customerServices.updateProfile(customerDto);
			return new ResponseEntity<>(new ResultResponse("Success"), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILURE :" + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/order/{userId}/getOrders")
	public ResponseEntity<Object> getOrderHistory(@PathVariable(value="userId") String customerIdStr) {
		long customerId = 0;
		try {
			customerId = Long.parseLong(customerIdStr);
			return new ResponseEntity<>(customerServices.getOrderHistory(customerId), HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/cart/{userId}/getCart")
	public ResponseEntity<Object> getCart(@PathVariable(value="userId") String customerIdStr){
		long customerId = 0;
		try {
			customerId = Long.parseLong(customerIdStr);
			return new ResponseEntity<>(customerServices.getCartByCustomerId(customerId), HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping(value = "/cart/{userId}/add/{productId}")
	public ResponseEntity<Object> addToCart(@PathVariable(value="userId") String customerIdStr, 
			@PathVariable(value="productId") String productIdStr) {
		long customerId = 0;
		long productId = 0;
		try {
			customerId = Long.parseLong(customerIdStr);
			productId = Long.parseLong(productIdStr);
			return new ResponseEntity<>(customerServices.addToCart(customerId, productId), HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/cart/{userId}/remove/{productId}")
	public ResponseEntity<Object> removeFromCart(@PathVariable(value="userId") String customerIdStr, 
			@PathVariable(value="productId") String productIdStr) {
		long customerId = 0;
		long productId = 0;
		try {
			customerId = Long.parseLong(customerIdStr);
			productId = Long.parseLong(productIdStr);
			return new ResponseEntity<>(customerServices.removeFromCart(customerId, productId), HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/cart/{userId}/changeQuantity/{productId}", method = RequestMethod.POST)
	public ResponseEntity<Object> changeQuantityOfProduct(@PathVariable(value="userId") String customerIdStr,
			@PathVariable(value="productId") String productIdStr, @RequestBody String quantityStr){
		long customerId = 0;
		long productId = 0;
		try {
			customerId = Long.parseLong(customerIdStr);
			productId = Long.parseLong(productIdStr);
			ObjectMapper objMapper = new ObjectMapper();
			QuantityDTO quantityDto = objMapper.readValue(quantityStr, QuantityDTO.class);
			return new ResponseEntity<>(customerServices.changeQuantityOfProduct(customerId, productId, quantityDto.getQuantity()), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
}
