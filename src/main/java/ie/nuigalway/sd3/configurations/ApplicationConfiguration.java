package ie.nuigalway.sd3.configurations;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "ie.nuigalway.sd3")
public class ApplicationConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public String myMessage( @Value("${my.messageValue}") String messageValue ){

		return messageValue;
	}


	//development mysql connection
	@Bean(name = "dataSource")
	@Profile("dev")
	public DataSource dataSourceForProd() {

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.pass"));

		return dataSource;
	}
}
