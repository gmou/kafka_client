package com.gmou.ikafka.producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 *  kafka 生产者
 */
public class KafkaProducer extends Producer<String, String> implements DisposableBean{
	
	private static final Log log = LogFactory.getLog(KafkaProducer.class);

	public KafkaProducer(ProducerConfig config) {
		super(config);
		log.info("=========KafkaProducer init success!");
	}
	
	/**
	 * 字符串类型消息发送
	 * @param topic  消息主题
	 * @param key	 消息关键字
	 * @param message 消息体
	 * @return
	 */
	public boolean send(String topic, String key, String message){
		log.info("=========kafka topic [" + topic + "] begin send,key [" + key + "],message[" + message + "]!");
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(
				topic, key, message);
        this.send(data);
        log.info("=========kafka topic [" + topic + "] send success!");
		return true;
	}
	
	public void destroy() throws Exception {
		log.info("=========begin to close kafka");
		this.close();
		log.info("=========end to close kafka");
	}


}
