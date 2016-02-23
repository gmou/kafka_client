package com.gmou.ikafka.test.consumer;

import com.gmou.ikafka.consumer.ConsumerLogic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by gmou on 16/2/23.
 */
public class Demo1ConsumerLogic implements ConsumerLogic {

    private static final Log log = LogFactory.getLog(Demo1ConsumerLogic.class);

    public boolean consume(String message) throws Exception {
        System.out.println("Demo1ConsumerLogic consume success,message[" + message + "]");
        return false;
    }
}
