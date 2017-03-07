package ie.nuigalway.sd3.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class GreetingController {

	@MessageMapping("/helloworld")
	@SendTo("/topic/greetings")
	public HashMap<String, String> action() throws Exception{

		//output successful json
		HashMap<String, String> jsonResponse = new HashMap<>();
		jsonResponse.put( "status", "ok" );
		jsonResponse.put( "message", "hello world" );
		return jsonResponse;
	}
}
