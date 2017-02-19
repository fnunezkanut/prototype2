package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.Thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@Profile("dev")
public class MysqlThreadRepository implements ThreadRepository{

	//our jdbc tempate
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MysqlThreadRepository( DataSource dataSource ){

		jdbcTemplate = new JdbcTemplate( dataSource );
	}

	//thread mapper lambda mapping sql result rows to Thread objects
	private RowMapper<Thread> threadMapperLambda = (rs, rowNum) -> {
		Thread thread = new Thread();
		thread.setId( rs.getLong("id") );
		thread.setTitle( rs.getString("title") );
		thread.setDt_created( rs.getDate( "dt_created" ) );
		thread.setDt_updated( rs.getDate( "dt_updated" ) );
		return thread;
	};

	@Override
	public List<Thread> getThreads() {

		String sqlTxt = "SELECT * FROM threads";
		return jdbcTemplate.query(sqlTxt, threadMapperLambda );
	}


	@Override
	public Thread getThread(Long id) {

		String sqlTxt = "SELECT * FROM threads WHERE id=?";
		return jdbcTemplate.queryForObject( sqlTxt, threadMapperLambda, id );
	}

	@Override
	public int getNumberOfThreads() {

		return 0; //TODO
	}

	@Override
	public Long createThread( String title ) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sqlTxt = "INSERT INTO threads(title) VALUES(?)";

		try{

			jdbcTemplate.update((Connection connection) -> {
				PreparedStatement ps = connection.prepareStatement(sqlTxt, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, title );
				return ps;

			}, keyHolder);
		}
		catch (Exception e){

			e.printStackTrace();
		}

		Long insertId = keyHolder.getKey().longValue();

		return insertId;
	}

	@Override
	public void deleteThread(Long id) {

		//TODO
	}

	@Override
	public void updateThread(Thread thread) {

		//TODO
	}

	/*
	private class ThreadMapper implements RowMapper<Thread> {

		@Override
		public Thread mapRow(ResultSet resultSet, int i) throws SQLException {
			return new Thread( resultSet.getLong("id"), resultSet.getString("title") );
		}
	}
	*/

	//TODO sql unit test
}
