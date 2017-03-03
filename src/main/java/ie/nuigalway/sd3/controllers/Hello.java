package ie.nuigalway.sd3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {

	@RequestMapping("/hello/{name}")
	public String hello( ModelMap model, @PathVariable("name") String name ){

		model.addAttribute("name", name);
		return "hello";
	}
}
