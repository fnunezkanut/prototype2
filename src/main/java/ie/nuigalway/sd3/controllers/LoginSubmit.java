package ie.nuigalway.sd3.controllers;



import ie.nuigalway.sd3.entities.User;
import ie.nuigalway.sd3.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;



@RestController
public class LoginSubmit {

	@Autowired
	private UserService userService;

	//receives email+pass via ajax POST
	@RequestMapping(
		method= RequestMethod.POST,
		value="/login/submit",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public HashMap<String,String> action(
		@RequestParam(value = "email") String email,
        @RequestParam(value = "pass") String pass
	){

		//convert the posted password into md5 hash
		String passwordHash = DigestUtils.md5Hex(pass).toUpperCase();


		User dbUser = userService.getUserByEmailAndPasshash( email, passwordHash );


		HashMap<String,String> jsonResponse = new HashMap<>(  );

		jsonResponse.put( "status", "ok" );
		jsonResponse.put( "email", email );
		jsonResponse.put( "password", pass );
		jsonResponse.put( "passwordHash", passwordHash );
		jsonResponse.put( "id", dbUser.getId().toString() );

		return jsonResponse;
	}
}
