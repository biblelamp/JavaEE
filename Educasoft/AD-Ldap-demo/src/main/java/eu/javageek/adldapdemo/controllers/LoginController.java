package eu.javageek.adldapdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView Login() {
		return new ModelAndView("login");
	}
}
