package bulkhead.service.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import io.micrometer.core.annotation.Counted;

@Component
public class InfluxDBRegister {

	@Counted(value = "service1.bulkheadFullException.counted")
	public void registerBulkheadFullError() {
		
	}
}
