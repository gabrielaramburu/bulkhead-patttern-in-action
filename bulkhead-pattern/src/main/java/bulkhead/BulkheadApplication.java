package bulkhead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BulkheadApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BulkheadApplication.class, args); 
		System.out.println("Ejecutando...");
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
