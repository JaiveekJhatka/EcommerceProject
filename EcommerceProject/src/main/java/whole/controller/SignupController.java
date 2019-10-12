package whole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.dto.ResultResponse;
import whole.model.dto.SignupRequest;
import whole.model.dto.SignupResponse;
import whole.model.service.Signup;


@RestController
public class SignupController {
	@Autowired
	Signup signupUser;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Object> signup(@RequestBody String newUser) {
		try{
			ObjectMapper objMapper = new ObjectMapper();
			SignupRequest signupRequest = objMapper.readValue(newUser, SignupRequest.class);
			long customerId = signupUser.addCustomer(signupRequest);
			return new ResponseEntity<>(new SignupResponse(customerId), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
