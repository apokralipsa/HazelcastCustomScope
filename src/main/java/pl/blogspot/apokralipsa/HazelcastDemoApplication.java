package pl.blogspot.apokralipsa;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import pl.blogspot.apokralipsa.scope.HazelcastClusterScope;

@SpringBootApplication
public class HazelcastDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastDemoApplication.class, args);
	}

	@Bean
	@Scope(HazelcastClusterScope.NAME)
	public GreetingService greetingsService() {
		return new GreetingService(System.getProperty("greetingService.name"));
	}

	@PostConstruct
	public void doGreet() {
		greetingsService().greet();
	}

}
