package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.Thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
@Profile({"default","test","prod"})
@Transactional
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

	//fetches all threads
	@Override
	public List<Thread> getThreads() {

		String sqlTxt = "SELECT * FROM threads";
		List<Thread> threads;

		//try to fetch all threads
		try{

			threads =  jdbcTemplate.query(sqlTxt, threadMapperLambda );
		}
		catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		}
		catch (DataAccessException e) {
			throw new RuntimeException(e);
		}


		return threads;
	}


	//get a single thread given its unique id
	@Override
	public Thread getThread(Long id) {

		Thread thread;
		String sqlTxt = "SELECT * FROM threads WHERE id=?";

		//try to fetch a single entry from table
		try{

			thread =  jdbcTemplate.queryForObject( sqlTxt, threadMapperLambda, id );
		}
		catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		}
		catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

		return thread;
	}

	//create a new thread given a title and return autoincremented unique id
	@Override
	public Long createThread( String title ) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sqlTxt = "INSERT INTO threads(title,dt_created,dt_updated) VALUES(?,?,?)";


		//current time used at insert time
		java.util.Date dt = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


		//try to insert entry to mysql
		try {

			jdbcTemplate.update(
					( Connection connection ) -> {
						PreparedStatement ps = connection.prepareStatement( sqlTxt, Statement.RETURN_GENERATED_KEYS );
						ps.setString( 1, title );
						ps.setString( 2, dateFormat.format( dt ) );
						ps.setString( 3, dateFormat.format( dt ) );
						return ps;
					},
					keyHolder
			);
		}
		catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		}
		catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

		//get the autoincrement id from the insert statement
		Long insertId = keyHolder.getKey().longValue();

		return insertId;
	}


	//updates the last udpated datetime for a row
	@Override
	public void updateDtUpdated( Long id ) {

		String sqlTxt = "UPDATE threads SET dt_updated=? WHERE id=?";

		//current time used at insert time
		java.util.Date dt = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		//try to update entry to mysql
		try {

			jdbcTemplate.update(sqlTxt, dateFormat.format( dt ), id );
		}
		catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		}
		catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

	}

	//TODO sql unit test
}
