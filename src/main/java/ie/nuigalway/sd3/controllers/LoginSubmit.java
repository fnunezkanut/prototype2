package ie.nuigalway.sd3.controllers;

import ie.nuigalway.sd3.ApplicationException;
import ie.nuigalway.sd3.entities.User;
import ie.nuigalway.sd3.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public HashMap<String,String> action(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "pass") String pass,
			HttpSession session
	) throws ApplicationException {

		//convert the posted password into md5 hash
		String passwordHash = DigestUtils.md5Hex(pass).toUpperCase();

		System.out.println( passwordHash );

		//fetch user from database given email and passhash
		User dbUser = new User();
		try{

			dbUser = userService.getUserByEmailAndPasshash( email, passwordHash );

			//save the user in session
			session.setAttribute( "currentUser", dbUser );
		}
		catch (Exception e){ //TODO better exception catching here

			//TODO EmptyResultDataAccessException
			throw new ApplicationException( e.getMessage() );
		}


		//output successful json
		HashMap<String,String> jsonResponse = new HashMap<>(  );
		jsonResponse.put( "status", "ok" );
		jsonResponse.put( "user_id", dbUser.getId().toString() );
		return jsonResponse;
	}
}
