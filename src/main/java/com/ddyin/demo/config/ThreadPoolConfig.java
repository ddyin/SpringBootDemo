package com.ddyin.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

	@Autowired
	private ThreadPoolProperties poolConfig;
	
	//定义一个线程池（同理可拓展）
	@Bean(name="testTaskAsyncPool")
    public Executor mcTaskAsyncPool() {  
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();  
        executor.setCorePoolSize(poolConfig.getCorePoolSize());  
        executor.setMaxPoolSize(poolConfig.getMaxPoolSize());  
        executor.setQueueCapacity(poolConfig.getQueueCapacity());  
        executor.setKeepAliveSeconds(poolConfig.getKeepAliveSeconds());  
        executor.setThreadNamePrefix("TestExecutor-");
  
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行  
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
        executor.initialize();  
        return executor;  
    }
}
