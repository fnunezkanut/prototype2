package ie.nuigalway.sd3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

	@Autowired
	private String myMessage;


	@RequestMapping("/status")
	public String welcome( ){

		return "my.messageValue: " + myMessage;
	}
}
