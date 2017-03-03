package ie.nuigalway.sd3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Homepage {

	@RequestMapping("/")
	public String hello(){

		return "homepage";
	}
}
