package ie.nuigalway.sd3.controllers;

import ie.nuigalway.sd3.error.ErrorResponseException;
import ie.nuigalway.sd3.entities.User;
import ie.nuigalway.sd3.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
	public HashMap<String,String> action( @RequestParam(value = "email") String email, @RequestParam(value = "pass") String pass, HttpSession session ) throws ErrorResponseException {

		//convert the posted password into md5 hash
		String passwordHash = DigestUtils.md5Hex(pass).toUpperCase();


		//fetch user from database given email and passhash
		User dbUser = new User();
		try{

			dbUser = userService.getUserByEmailAndPasshash( email, passwordHash );
			throw new ErrorResponseException("test");
		}
		catch (Exception e){

			//TODO better exception handling here
			throw new ErrorResponseException("test2");
		}


/*
		//output successful json
		HashMap<String,String> jsonResponse = new HashMap<>(  );
		jsonResponse.put( "status", "ok" );
		jsonResponse.put( "user_id", dbUser.getId().toString() );
		return jsonResponse;
		*/
	}



}
