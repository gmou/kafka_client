package com.gmou.ikafka.test.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
	private static final String CONFIG = "/spring-config-proxy-kafkaconsumer.xml";

	public static void main(String[] args) throws InterruptedException {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, ConsumerTest.class);
		ctx.start();

		Thread.sleep(Integer.MAX_VALUE);
		
	}
}
