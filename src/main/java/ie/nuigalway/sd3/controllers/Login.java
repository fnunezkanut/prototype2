package ie.nuigalway.sd3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {

	@RequestMapping("/login")
	public String subaction(){

		return "login";
	}
}
