package com.gmou.ikafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;
 
/**
 * 默认的消息分区实现
 * @author ligang.xiaomai
 *
 */
public class DefaultPartitioner implements Partitioner {
	
    public DefaultPartitioner(VerifiableProperties props) {
 
    }
 
    public int partition(Object obj, int numPartitions) {
        int partition = 0;
        if (obj instanceof String) {
            String key=(String)obj;
            int offset = key.lastIndexOf('.');
            if (offset > 0) {
                partition = Integer.parseInt(key.substring(offset + 1)) % numPartitions;
            }
        }else{
            partition = obj.toString().length() % numPartitions;
        }
         
        return partition;
    }
 
}