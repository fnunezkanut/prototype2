package ie.nuigalway.sd3.controllers;

import ie.nuigalway.sd3.ApplicationException;
import ie.nuigalway.sd3.entities.User;
import ie.nuigalway.sd3.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class Login {

	//random uuid
	@Value("${my.uuid}")
	String myUuid;

	@Autowired
	private UserService userService;


	//shows login page
	@RequestMapping(
			value = "/login",
			produces = MediaType.TEXT_HTML_VALUE
	)
	public String action( ModelMap model, HttpSession session ) throws ApplicationException {

		//pass a random uuid to the view template
		model.addAttribute("randomUuid", myUuid );

		return "login";
	}



	//receives email+pass via ajax POST
	@RequestMapping(
			method= RequestMethod.POST,
			value="/login/submit",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public HashMap<String,String> action_submit(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "pass") String pass,
			HttpSession session
	) throws ApplicationException {

		//convert the posted password into md5 hash
		String passwordHash = DigestUtils.md5Hex(pass).toUpperCase();


		//fetch user from database given email and passhash
		User dbUser = new User();
		try{

			dbUser = userService.getUserByEmailAndPasshash( email, passwordHash );
		}
		catch (Exception e){

			//TODO better exception handling here
			throw new ApplicationException( e.getMessage() );
		}
		

		//output successful json
		HashMap<String,String> jsonResponse = new HashMap<>(  );
		jsonResponse.put( "status", "ok" );
		jsonResponse.put( "user_id", dbUser.getId().toString() );
		return jsonResponse;

	}
}
