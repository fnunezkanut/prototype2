package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.Thread;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("dev")
public class MemoryThreadRepository implements ThreadRepository{


	//our 3 test threads will be stored here
	private static Map<Long, Thread> threadMap = new ConcurrentHashMap<Long, Thread>();

	private static Long nextId = 4L;

	static {
		threadMap.put( 1L, new Thread(1L, "test1") );
		threadMap.put( 2L, new Thread(2L, "test2") );
		threadMap.put( 3L, new Thread(3L, "test3") );
	}

	@Override
	public List<Thread> getThreads() {
		Collection<Thread> accountCollection = threadMap.values();
		List<Thread> threadList = new ArrayList<Thread>();
		for (Thread thread : accountCollection) {
			threadList.add( thread );
		}
		return threadList;
	}

	@Override
	public Thread getThread(Long id) {
		return threadMap.get( id );
	}

	@Override
	public int getNumberOfThreads() {
		return threadMap.size();
	}

	@Override
	public Long createThread(String title) {
		Long threadId = nextId++;
		threadMap.put( threadId, new Thread(threadId, title ));
		return threadId;
	}

	@Override
	public void deleteThread(Long id) {
		threadMap.remove( id );
	}

	@Override
	public void updateThread(Thread thread) {

		threadMap.put( thread.getId(), thread );
	}
}
