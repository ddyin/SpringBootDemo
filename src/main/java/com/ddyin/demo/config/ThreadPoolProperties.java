package com.ddyin.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.task.pool")
@Data
public class ThreadPoolProperties {
	
	private int corePoolSize;
	private int maxPoolSize;
	private int keepAliveSeconds;
	private int queueCapacity;

}
