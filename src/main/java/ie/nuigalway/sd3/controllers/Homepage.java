package ie.nuigalway.sd3.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Homepage {

	//from .properties
	@Value("${app.RANDOM}")
	String app_RANDOM;
	@Value("${app.BASE_URL}")
	String app_BASE_URL;

	@RequestMapping("/")
	public String action( ModelMap model, HttpSession session ){

		//pass data to twig view
		model.addAttribute("app_RANDOM", app_RANDOM );
		model.addAttribute("app_BASE_URL", app_BASE_URL );

		//return view name
		return "homepage";
	}
}
