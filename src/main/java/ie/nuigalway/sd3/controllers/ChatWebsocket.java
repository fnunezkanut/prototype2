package ie.nuigalway.sd3.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.nuigalway.sd3.entities.JsonResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatWebsocket {

	/**
	 * inserts a new message into database, and responds with complete list of messages  in reverse order
	 * @param threadId
	 * @param jsonRequest
	 * @return
	 * @throws ApplicationException
	 */
	@MessageMapping("/thread/{threadId}")
	@SendTo("/topic/chat")
	public JsonResponse action(
		@DestinationVariable String threadId,
		String jsonRequest
	                                     ){


		//parse our incoming JSON string to Map
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {

			ObjectMapper mapper = new ObjectMapper();
			jsonMap = mapper.readValue(jsonRequest, new TypeReference<Map<String, String>>(){} );
		}
		catch ( IOException e) {

			return new JsonResponse("error", e.getMessage() );
		}


		//extract user id of posting user
		long userId = Long.parseLong( jsonMap.get( "user_id" ) );


		//check such a user exists



		JsonResponse jsonResponse = new JsonResponse("ok", "test");
		jsonResponse.put( "thread_id", threadId );
		jsonResponse.put( "message", jsonMap.get( "message" ) );
		jsonResponse.put( "user_id", jsonMap.get( "user_id" ) );
		return jsonResponse;
	}
}
