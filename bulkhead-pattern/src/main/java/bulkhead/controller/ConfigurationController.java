package bulkhead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bulkhead.model.Config;

@RestController
public class ConfigurationController {
	@Autowired
	private ServerProperties serverProperties;
	
	@Autowired
	private Config config;
	
	@GetMapping("/config")
	public String config() {
		return config.showConfig(serverProperties);
	}
	
	@PostMapping("/delay")
	public String delay(@RequestParam(name = "delay-ms") int ms) {
		this.config.setPause(ms);
		return "ok";
	}
	
}
