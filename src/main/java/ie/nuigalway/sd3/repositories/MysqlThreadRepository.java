package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.Thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile("prod")
public class MysqlThreadRepository implements ThreadRepository{


	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Thread> getThreads() {

	}

	@Override
	public Thread getThread(Long id) {

	}

	@Override
	public int getNumberOfThreads() {

	}

	@Override
	public Long createThread(String title) {

	}

	@Override
	public void deleteThread(Long id) {

	}

	@Override
	public void updateThread(Thread thread) {

	}
}
