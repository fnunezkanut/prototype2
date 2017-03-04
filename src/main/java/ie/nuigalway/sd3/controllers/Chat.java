package ie.nuigalway.sd3.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Chat {

	@Value("${my.uuid}")
	String myUuid;

	@RequestMapping("/chat")
	public String action( ModelMap model, HttpSession session ){

		//pass a random uuid to the view template
		model.addAttribute("randomUuid", myUuid );

		return "chat";
	}
}
