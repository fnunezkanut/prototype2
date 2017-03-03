package ie.nuigalway.sd3.controllers;

import ie.nuigalway.sd3.entities.User;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;



@RestController
public class LoginSubmit {


	//receives email+pass via ajax POST
	@RequestMapping(
		method= RequestMethod.POST,
		value="/login/submit",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public HashMap<String,String> action(
		@ModelAttribute  User user
	){

		HashMap<String,String> jsonResponse = new HashMap<>(  );

		jsonResponse.put( "status", "ok" );
		jsonResponse.put( "email", user.getEmail() );
		jsonResponse.put( "password", user.getPass() );

		return jsonResponse;
	}
}
