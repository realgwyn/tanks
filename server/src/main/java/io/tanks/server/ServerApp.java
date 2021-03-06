package io.tanks.server;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import io.tanks.server.config.GameplayConfig;
import io.tanks.server.config.ServerConfig;

@SpringBootApplication
public class ServerApp {

	@Autowired
	ServerWindow serverWindow;
	@Autowired
	ServerConfig config;
	@Autowired
	GameplayConfig gameplayConfig;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ServerApp.class).headless(false).run(args);
	}

	@PostConstruct
	public void run() {
		serverWindow.setVisible(true);
		serverWindow.setServerName(config.getDefaultServerName());
		serverWindow.setTcpPort(config.getDefaultTcpPort());
		serverWindow.setUdpPort(config.getDefaultUdpPort());
	}

}
