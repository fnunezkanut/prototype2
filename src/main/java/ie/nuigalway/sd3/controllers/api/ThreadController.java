package ie.nuigalway.sd3.controllers.api;

import ie.nuigalway.sd3.entities.Thread;
import ie.nuigalway.sd3.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ThreadController {

	@Autowired
	private ThreadService threadService;

	//fetching all threads
	@RequestMapping(method= RequestMethod.GET, value="/api/threads")
	public List<Thread> getThreads(){

		return threadService.getThreads();
	}

	//fetching a particular thread
	@RequestMapping(method= RequestMethod.GET, value = "/api/threads/{threadId}")
	public Thread getTopic( @PathVariable("threadId") Long threadId ){

		return threadService.getThread( threadId );
	}


	@RequestMapping(method=RequestMethod.POST, value="/api/threads")
	public void createThread( @RequestBody Thread thread ){

		threadService.createThread( thread );
	}
}
