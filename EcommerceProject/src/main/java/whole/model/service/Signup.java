package whole.model.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import whole.model.dto.SignupRequest;
import whole.model.entity.Customer;
import whole.model.entity.User;
import whole.model.repository.CustomerRepository;
import whole.model.repository.UserRepository;


@Service
public class Signup {
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public long addCustomer(SignupRequest signup) throws JsonProcessingException, RuntimeException{
		Optional<User> exists = userRepo.findByUsername(signup.getEmail());
		if(exists.isPresent())
			throw new RuntimeException("Email id/username already exists in database!");
		else {
			Customer customer = new Customer(signup);
			return customerRepo.save(customer).getId();
		}
	}
}
