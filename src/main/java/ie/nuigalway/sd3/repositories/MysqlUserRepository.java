package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;


@Repository
@Profile("dev")
@Transactional
public class MysqlUserRepository implements UserRepository{

	//our jdbc tempate
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MysqlUserRepository( DataSource dataSource ){

		jdbcTemplate = new JdbcTemplate( dataSource );
	}


	@Override
	public User getUser( Long id ) {
		return null;
	}

	@Override
	public User getUserByEmailAndPasshash( String email, String passhash ) {
		return null;
	}

	@Override
	public void updateDtUpdated( Long id ) {

	}
}
