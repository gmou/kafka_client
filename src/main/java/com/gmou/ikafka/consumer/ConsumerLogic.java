package com.gmou.ikafka.consumer;

/**
 * kafka 业务数据消费接口
 */
public interface ConsumerLogic {

    /**
     * 业务消费逻辑
     * @param message
     * @return
     * @throws Exception
     */
    public boolean consume(String message) throws Exception;

}
