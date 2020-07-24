package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // This means that this class is a Controller
@RequestMapping(value = {"/user"})
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private IUserService userService;
	
	@GetMapping("/showUsers")
	public String findUsers(Model model) {
		List<User> users = (List<User>) userService.findAll();
		model.addAttribute("users", users);
		return "showUsers";
	}
		

	@GetMapping("/formadd")
	public String greetingForm(Model model, @ModelAttribute User user) {
		return "createUser";
	}
	
	@PostMapping(path="/formadd")
	public String userAdd(@ModelAttribute User user)
	{
		userRepository.save(user);
		return "resultUser";
	}	
	
    @RequestMapping(path = {"/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Long id) 
    {
        	long l = id;
        	int i = (int)l;
            User entity;
			try {
				entity = userService.getUserById(i);
				model.addAttribute("user", entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
        return "createUser";
    }
    
    @RequestMapping(path = {"/add/{id}"})
    public String addUser(Model model, @PathVariable("id") Integer id) 
    {
    	User entity = new User();
    	entity.setAID(id);
        model.addAttribute("user", entity);
        return "createUser";
    }
    
    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Integer id) throws Exception
    {
        userService.deleteUserById(id);
        return "removeUser";
    }
	
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateUser(User user) throws Exception 
    {
        userService.createOrUpdateUser(user);
        return "redirect:/user/showUsers";
    }
}


