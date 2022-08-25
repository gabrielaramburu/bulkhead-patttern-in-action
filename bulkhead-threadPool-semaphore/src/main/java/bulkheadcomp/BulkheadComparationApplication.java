package bulkheadcomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class BulkheadComparationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkheadComparationApplication.class, args);
		System.out.println("Executing server...");
	}

}
