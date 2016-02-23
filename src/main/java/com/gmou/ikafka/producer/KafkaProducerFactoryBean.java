package com.gmou.ikafka.producer;

import org.springframework.beans.factory.FactoryBean;

import kafka.producer.ProducerConfig;

public class KafkaProducerFactoryBean implements FactoryBean<KafkaProducer> {
	
	private ProducerConfig config;

	public KafkaProducer getObject() throws Exception {
		return new KafkaProducer(config);
	}

	public Class<KafkaProducer> getObjectType() {
		return KafkaProducer.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public ProducerConfig getConfig() {
		return config;
	}

	public void setConfig(ProducerConfig config) {
		this.config = config;
	}

}
