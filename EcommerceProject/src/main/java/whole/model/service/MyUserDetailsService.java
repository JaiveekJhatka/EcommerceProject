package whole.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import whole.model.MyUserDetails;
import whole.model.entity.User;
import whole.model.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		 user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
	     return user.map(MyUserDetails::new).get();
	}

	
}
