package io.tanks.server.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:gameplay-config.yml")
@ConfigurationProperties
public class GameplayConfig {
	
	List<String> mapNames;

	public List<String> getMapNames() {
		return mapNames;
	}

	public void setMapNames(List<String> mapNames) {
		this.mapNames = mapNames;
	}
}
