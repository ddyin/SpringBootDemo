package com.ddyin.demo.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

@Configuration
public class TransactionConfig {
	private static final String PROPAGATION_REQUIRED = "PROPAGATION_REQUIRED,-Exception";
	private static final String PROPAGATION_SUPPORTS = "PROPAGATION_SUPPORTS";

	private static final String[] methodRequired = new String[] { "save*",
			"add*", "insert*", "del*", "delete*", "upd*", "update*" };
	private static final String[] methodsReadOnly = new String[] { "find*",
			"get*", "select*", "has*", "is*","*" };

	public TransactionInterceptor transactionInterceptor(
			PlatformTransactionManager platformTransactionManager) {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor
				.setTransactionManager(platformTransactionManager);
		Properties transactionAttributes = new Properties();
		for (int i = 0; i < methodRequired.length; i++) {
			String key = methodRequired[i];
			transactionAttributes.put(key, PROPAGATION_REQUIRED);
		}
		for (int i = 0; i < methodsReadOnly.length; i++) {
			String key = methodsReadOnly[i];
			transactionAttributes.put(key, PROPAGATION_SUPPORTS);
		}
		transactionInterceptor.setTransactionAttributes(transactionAttributes);
		return transactionInterceptor;
	}

	@Bean(name="transactionAutoProxy")
	public AspectJExpressionPointcutAdvisor transactionAutoProxy(
			@Qualifier("primaryDataSourceTransactionManager")  PlatformTransactionManager platformTransactionManager) {
		AspectJExpressionPointcutAdvisor transactionAutoProxy = new AspectJExpressionPointcutAdvisor();
		transactionAutoProxy
				.setAdvice(transactionInterceptor(platformTransactionManager));
		transactionAutoProxy
				.setExpression("execution(* com.ddyin.demo.service..*(..))");
		return transactionAutoProxy;
	}
}
