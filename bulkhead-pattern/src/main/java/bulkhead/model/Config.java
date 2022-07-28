package bulkhead.model;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

@Component
public class Config {
	
	private int pause;
	
	public Config() {
		this.pause = 2;
	}

	public int getPause() {
		return pause;
	}

	public void setPause(int pause) {
		this.pause = pause;
	}
	
	public String showConfig(ServerProperties serverProperties) {
		StringBuffer sb = new StringBuffer();
		sb.append("MaxThreads:");
		sb.append(serverProperties.getTomcat().getThreads().getMax());
		sb.append("\n");
		sb.append("MinSpareThreads:");
		sb.append(serverProperties.getTomcat().getThreads().getMinSpare());
		sb.append("\n");
		sb.append("AcceptedCount:");
		sb.append(serverProperties.getTomcat().getAcceptCount());
		sb.append("\n");
		sb.append("MaxConnections:");
		sb.append(serverProperties.getTomcat().getMaxConnections());
		sb.append("\n");
		sb.append("KeepAliveTimeout:");
		sb.append(serverProperties.getTomcat().getKeepAliveTimeout());
		sb.append("\n");
		sb.append(" ***** ");
		sb.append("pause:");
		sb.append(this.getPause());
		
		sb.append("\n");
		
		return sb.toString();
	}
}
