package com.gmou.ikafka.consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

/**
 * 消费任务
 */
public class ConsumerTask implements Runnable {
	
	private static final Log log = LogFactory.getLog(ConsumerTask.class);

	private ConsumerLogic consumerLogic;
	private KafkaStream<byte[], byte[]> m_stream;
	private int m_threadNumber;

	public ConsumerTask(KafkaStream stream, int threadNumber,ConsumerLogic consumerLogic) {
		this.m_threadNumber = threadNumber;
		this.m_stream = stream;
		this.consumerLogic = consumerLogic;
	}

	public void run() {
		ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
		while (it.hasNext()) {
			MessageAndMetadata<byte[], byte[]> next = it.next();
			String message = new String(next.message());
			String topic = next.topic();
			
			log.info("topic[" + topic + "],message[" + message + "] 消费开始,消费线程[" + m_threadNumber + "]！");
			try {
				if(message == null || "".equals(message)){
					log.warn("topic[" + topic + "],message 为空，不进行消费！");
				}else{
					boolean ret = consumerLogic.consume(message);
					log.info("topic[" + topic + "],message[" + message + "] 消费结束,ret[" + ret + "]！");
				}
				
			} catch (Exception e) {
				// TODO 异常处理需要特殊处理，后续需要继续优化
				log.error("topic[" + topic + "],message[" + message + "]消息消费失败,",e);;
			}
		}

		log.info("Shutting down Thread: " + m_threadNumber);
	}

}
