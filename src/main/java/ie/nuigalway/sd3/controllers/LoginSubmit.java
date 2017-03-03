package ie.nuigalway.sd3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class LoginSubmit {


	//receives email+pass via ajax
	@RequestMapping(
		method= RequestMethod.POST,
		value="/login/submit",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces={"application/json; charset=UTF-8"}
	)
	@ResponseStatus( HttpStatus.OK)
	public HashMap<String,String> subaction_submit( ){

		HashMap<String,String> jsonResponse = new HashMap<>(  );

		jsonResponse.put( "status", "ok" );

		return jsonResponse;
	}
}
