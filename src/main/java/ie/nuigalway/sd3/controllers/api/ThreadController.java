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
	@RequestMapping(method= RequestMethod.GET, value="/api/v1/threads")
	public List<Thread> getThreads(){

		return threadService.getThreads();
	}

	//fetching a particular thread
	@RequestMapping(method= RequestMethod.GET, value = "/api/v1/threads/{threadId}")
	public Thread getTopic( @PathVariable("threadId") Long threadId ){

		return threadService.getThread( threadId );
	}

	//creating a thread with a title
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/threads")
	public Long createThread( @RequestParam("title") String title ){

		return threadService.createThread( title );
	}

	//update the threads timestamp
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/threads/{threadId}/updated")
	public void updateDtUpdated( @PathVariable("threadId") Long threadId ){

		threadService.updateDtUpdated( threadId );
	}
}
