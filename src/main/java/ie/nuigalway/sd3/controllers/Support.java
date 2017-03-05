package ie.nuigalway.sd3.controllers;

import ie.nuigalway.sd3.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class Support {

	//from .properties
	@Value("${app.RANDOM}")
	String app_RANDOM;
	@Value("${app.BASE_URL}")
	String app_BASE_URL;



	@RequestMapping("/support")
	public ModelAndView action(
			ModelMap model,
			HttpSession session
	){


		//get current user from session
		User currentUser = (User)session.getAttribute( "currentUser" );
		if( currentUser == null ){

			//redirect if not signed in
			return new ModelAndView("redirect:" + app_BASE_URL + "signin" );
		}
		else{


			//pass data to twig view
			model.addAttribute("app_RANDOM", app_RANDOM );
			model.addAttribute("app_BASE_URL", app_BASE_URL );
			model.addAttribute("user_id", currentUser.getId()  );
			model.addAttribute("user_email", currentUser.getEmail()  );
			model.addAttribute("user_is_support", currentUser.getIsSupport()  );


			//the view shown depends on whether the user is support person or a normal customer
			String viewName = "support/customer";
			if( currentUser.getIsSupport() == true ){
				viewName = "support/admin";
			}

			//return view name
			return new ModelAndView( viewName );
		}
	}
}
