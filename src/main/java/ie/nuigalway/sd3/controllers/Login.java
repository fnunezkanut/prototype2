package ie.nuigalway.sd3.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {

	@Value("${my.uuid}")
	String myUuid;

	@RequestMapping("/login")
	public String subaction(  ModelMap model ){

		//pass a random uuid to the view template
		model.addAttribute("randomUuid", myUuid );

		return "login";
	}
}
