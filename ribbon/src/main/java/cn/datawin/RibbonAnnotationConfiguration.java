package cn.datawin;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonAnnotationConfiguration {

	@Bean
	public ServerList<Server> ribbonServerList() {
		// 实例列表
		String listOfServers = "http://localhost:10001,http://localhost:10002";
		String[] splits = listOfServers.split(",");
		int len = splits.length;

		Server[] servers = new Server[len];
		for (int i = 0; i < len; i++) {
			servers[i] = new Server(splits[i].trim());
		}
		return new StaticServerList<>(servers);
	}

	@Bean
	public IRule ribbonRule() {
		return new RoundRobinRule();
	}
}
