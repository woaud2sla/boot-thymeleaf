package idu.cs.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired UserRepository userRepo; // Dependency Injection
	
	@GetMapping("/test")
	public String home(Model model) {
		model.addAttribute("test", "인덕 컴소");
		model.addAttribute("hjm", "황재명");
		return "index";
	}
	@GetMapping("/register")
	public String loadRegForm(Model model) {
		return "regform";
	}
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userlist";
	}
	@GetMapping("/welcome")
	public String loadwelcome(Model model) {
		return "welcome";
	}
	
	@GetMapping("/users/{id}")
	public String getAllUser(@PathVariable(value = "id") Long userId, 
			Model model) throws ResourceNotFoundException{
		User user = userRepo.findById(userId).get();
		model.addAttribute("id", " " + userId);
		model.addAttribute("name", user.getName());
		model.addAttribute("company", user.getCompany());
		return "user";
	}
	@PostMapping("/create")
	public String createUser(@Valid @RequestBody User user, Model model) {
		userRepo.save(user);
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
}
