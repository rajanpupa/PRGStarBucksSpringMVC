package mum.app.controller;

import java.util.List;

import mum.edu.data.Database;
import mum.edu.domain.User;
import mum.edu.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes(value={"username"})
public class LoginController {
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	Database database;

	@RequestMapping("/")
    public String index(Model model) {
		return "login";
    }
	
	@RequestMapping(value="/login", method=RequestMethod.POST )
    public String login(User user, Model model) {
		
		List<String> errors = userValidator.validate(user);
		
		System.out.println("creating form");
		
		if(errors.size()>0){
			//login failed
			// flash attributes won't work in session
			//redirectAttributes.addFlashAttribute("errors", errors);
			
			// errors added in the request scope
			model.addAttribute("errors", errors);
			return "login";
		}else{
			// login passed
			model.addAttribute("username", user.getUsername());
			
			// username will be available in the redirect context
			return "redirect:login";
		}
		
    }
	
	@RequestMapping(value="/login", method=RequestMethod.GET )
	public String loginRedirect(){
		return "login";
	}
	
	@RequestMapping(value="/select", method=RequestMethod.GET )
	public ModelAndView selectCategory(@RequestParam("type") String  type){
		
		/*
		 * parameter being added in the request scope
		 */
		ModelAndView model1 = new ModelAndView("advice", "list", database.getAdvice(type));
		
		return model1;
	}
	
	@RequestMapping(value="/logout" , method=RequestMethod.GET)
    public String logout(SessionStatus status) {
		
		// delete the session attributes
		// when the method is finished
		status.setComplete();
		
		// session attributes will not be available in the redirect
		return "redirect:/";
    }
	
}
