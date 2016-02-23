package com.gmou.ikafka.test.producer;

import com.gmou.ikafka.producer.KafkaProducer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerTest {
	private static final String CONFIG = "/spring-config-proxy-kafkaproducer.xml";

	public static void main(String[] args) throws InterruptedException {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, ProducerTest.class);
		ctx.start();
		
		KafkaProducer producer = ctx.getBean("kafkaProducer",KafkaProducer.class);

		for(int i=0;i<10;i++){
			boolean ret = producer.send("test", String.valueOf(i), "hello-" + i);
			System.out.println("发送结果[" + ret + "]");
		}
		Thread.sleep(10000);
		ctx.destroy();
	}
}
