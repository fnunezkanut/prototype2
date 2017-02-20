package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.Thread;
import ie.nuigalway.sd3.services.ThreadService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@SqlGroup({
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/beforeThreadRepositoryTest.sql"),
	//@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/afterThreadRepositoryTest.sql")
})
public class ThreadRepositoryTest {


	@Autowired
	private ThreadService threadService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void getThreads() throws Exception {

		List<Thread> threads = this.threadService.getThreads();
		System.out.println( threads );
	}



}