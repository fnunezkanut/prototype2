package ie.nuigalway.sd3.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "ie.nuigalway.sd3")
public class ApplicationConfiguration {

	@Bean
	public String myMessage( @Value("${my.messageValue}") String messageValue ){

		return messageValue;
	}
}
