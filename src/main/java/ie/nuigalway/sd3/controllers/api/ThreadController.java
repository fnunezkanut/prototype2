package ie.nuigalway.sd3.controllers.api;

import ie.nuigalway.sd3.entities.Thread;
import ie.nuigalway.sd3.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ThreadController {

	@Autowired
	private ThreadService threadService;

	@RequestMapping(method= RequestMethod.GET, value="/api/threads")
	public List<Thread> getThreads(){

		return threadService.getThreads();
	}
}
