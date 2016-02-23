package com.gmou.ikafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * kafka后台消费者实例
 */
public class KafkaConsumer implements DisposableBean{
	
	private static final Log log = LogFactory.getLog(KafkaConsumer.class);
	
	private String topic;
    private int threadNum;
    private ConsumerConfig config;
    private ConsumerLogic consumerLogic;
    
    private ExecutorService executor;
    private ConsumerConnector consumer;
    
	public void destroy() throws Exception {
		log.info("=========begin to close kafka");
		if (consumer != null)
            consumer.shutdown();
        if (executor != null)
            executor.shutdown();
        log.info("=========end to close kafka");
	}

	/**
	 * 消费者启动方法
	 * @throws Exception
	 */
	public void init() throws Exception {
		log.info("=========kafka topic [" + topic + "] begin init,threadNum [" + threadNum + "]!");
		consumer = Consumer.createJavaConsumerConnector(config);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(threadNum));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
 
        // now launch all the threads
        executor = Executors.newFixedThreadPool(threadNum);
 
        // now create an object to consume the messages
        int threadNumber = 0;
        for (final KafkaStream<?, ?> stream : streams) {
            executor.submit(new ConsumerTask(stream, threadNumber,consumerLogic));
            threadNumber++;
        }
        log.info("=========kafka topic [" + topic + "] init success]!");
	}


	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public ConsumerConfig getConfig() {
		return config;
	}

	public void setConfig(ConsumerConfig config) {
		this.config = config;
	}

	public ConsumerLogic getConsumerLogic() {
		return consumerLogic;
	}

	public void setConsumerLogic(ConsumerLogic consumerLogic) {
		this.consumerLogic = consumerLogic;
	}
	
}
