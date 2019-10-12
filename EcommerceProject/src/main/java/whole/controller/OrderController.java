package whole.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import whole.model.dto.ResultResponse;
import whole.model.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping(value = "/order/{userId}/createOrder")
	public ResponseEntity<Object> createOrder(@PathVariable(value="userId") String customerIdStr) {
		long customerId=0;
		try {
			customerId = Long.parseLong(customerIdStr);
			return new ResponseEntity<>(orderService.createOrder(customerId), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
