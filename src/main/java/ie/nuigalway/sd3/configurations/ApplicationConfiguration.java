package ie.nuigalway.sd3.configurations;

import ie.nuigalway.sd3.controllers.Error;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.spring.asset.SpringAssetExtension;
import org.jtwig.spring.asset.resolver.AssetResolver;
import org.jtwig.spring.asset.resolver.BaseAssetResolver;
import org.jtwig.spring.boot.config.JtwigViewResolverConfigurer;
import org.jtwig.web.servlet.JtwigRenderer;
import org.jtwig.hot.reloading.HotReloadingExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableRedisHttpSession
@ComponentScan(basePackages = "ie.nuigalway.sd3")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter implements JtwigViewResolverConfigurer {

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
													.add(new SpringAssetExtension())
													.and()
													.extensions()
													.add(new HotReloadingExtension())
													.and()
													.build()
			)
		);
		viewResolver.setSuffix(".twig");
	}


	//the bean and override below configures static file loading from public
	@Bean
	public AssetResolver assetResolver () {
		BaseAssetResolver assetResolver = new BaseAssetResolver();
		assetResolver.setPrefix("public");
		return assetResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("static/**").addResourceLocations("classpath:static/");
	}
	//the bean and override above configures static file loading from public
}
