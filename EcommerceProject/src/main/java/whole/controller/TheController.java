package whole.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheController {
	
	@GetMapping("/")
	public String home() {
		return("index.html");
	}
	
	@GetMapping("/user")
	public String user() {
		return("Welcome user.");
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
