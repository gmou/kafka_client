package com.gmou.ikafka.start;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerMsgTask implements Runnable {
    private KafkaStream m_stream;
    private int m_threadNumber;
 
    public ConsumerMsgTask(KafkaStream stream, int threadNumber) {
        m_threadNumber = threadNumber;
        m_stream = stream;
    }
 
    public void run() {

        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();

        while (it.hasNext()) {
//            System.out.println("Thread " + m_threadNumber + ": " + new String(it.next().message()));
            try {
//                System.out.println("msg receive...!!!");
                MessageAndMetadata<byte[], byte[]> data = it.next();
                String  topic      = data.topic();
                int     partition  = data.partition();
                long    offset     = data.offset();
                String     key        = new String(data.key());     /** key和value都是bytes */
                String  msg        = new String(data.message()); /** 需要转换 */
                System.out.println(String.format(
                        "Consumer: [%s],  Topic: [%s],  PartitionId: [%d],Offset: [%s],  Key: [%s], msg: [%s]" ,
                m_threadNumber, topic, partition, offset, key, msg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}