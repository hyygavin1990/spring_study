package cn.datawin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@EnableConfigServer
@SpringBootApplication
@Configuration
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegist() {
		FilterRegistrationBean frBean = new FilterRegistrationBean();
		frBean.setFilter(new Filter() {
			@Override
			public void init(FilterConfig filterConfig) throws ServletException {

			}

			@Override
			public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
				HttpServletRequest req = (HttpServletRequest) servletRequest;
				String uri = req.getRequestURI();
				if(uri.equals("/actuator/bus-refresh")){
					StringBuilder buffer = new StringBuilder();
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
						String line;
						while ((line = reader.readLine()) != null) {
							buffer.append(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				filterChain.doFilter(servletRequest, servletResponse);
			}

			@Override
			public void destroy() {

			}
		});
		frBean.addUrlPatterns("/*");
		return frBean;
	}
}
