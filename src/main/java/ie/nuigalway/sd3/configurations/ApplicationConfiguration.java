package ie.nuigalway.sd3.configurations;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.spring.boot.config.JtwigViewResolverConfigurer;
import org.jtwig.web.servlet.JtwigRenderer;
import org.jtwig.hot.reloading.HotReloadingExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ie.nuigalway.sd3")
public class ApplicationConfiguration implements JtwigViewResolverConfigurer {

	@Autowired
	private Environment env;

	//mysql connection datasource
	@Bean(name = "dataSource")
	@Profile({"default","test","prod"})
	public DataSource dataSourceForDefault() {

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.pass"));

		return dataSource;
	}

	//configures jtwig
	@Override
	public void configure( JtwigViewResolver viewResolver ) {

		viewResolver.setRenderer(
				new JtwigRenderer(
					EnvironmentConfigurationBuilder.configuration()
													.extensions()
													.add(new HotReloadingExtension())
													.and()
													.build()
			)
		);
		viewResolver.setSuffix(".twig");
	}
}
