package ie.nuigalway.sd3.controllers;

import ie.nuigalway.sd3.entities.Thread;
import ie.nuigalway.sd3.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RestController
public class ThreadController {

	@Autowired
	private ThreadService threadService;

	@RequestMapping("/api/threads");
	public List<Thread> getThreads(){

		return threadService.getThreads();
	}
}
