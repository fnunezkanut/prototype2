package ie.nuigalway.sd3.controllers;

import ie.nuigalway.sd3.error.ErrorResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Login {

	//random uuid
	@Value("${my.uuid}")
	String myUuid;

	//shows login page
	@RequestMapping( value = "/login", produces = MediaType.TEXT_HTML_VALUE )
	public String action( ModelMap model, HttpSession session ) throws ErrorResponseException {

		//pass a random uuid to the view template
		model.addAttribute("randomUuid", myUuid );


		throw new ErrorResponseException("testtest");

		//return "login";
	}
}
