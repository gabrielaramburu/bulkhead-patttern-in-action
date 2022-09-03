package bulkheadcomp.service;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class Config implements AsyncConfigurer {

	@Bean(name = "threadPoolExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(7);
		executor.setMaxPoolSize(42);
		executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("threadPoolExecutor-");
		executor.initialize();
		return executor;
	}
//	
//	@Bean(name = "simpleAsyncTaskExecutor")
//	public Executor getAsyncExecutor() {
//		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
//		executor.setConcurrencyLimit(1000);
//		executor.setThreadNamePrefix("simpleAsyncTask");
//		return executor;
//	}
}
