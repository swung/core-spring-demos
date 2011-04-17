package com.gordondickens.sijms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: gordon
 * Date: 4/16/11
 * Time: 12:46 PM
 */
@ContextConfiguration("classpath:/com/gordondickens/sijms/JmsSenderTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsSenderTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JmsTemplate jmsTemplate;

    MyJmsGateway myJmsGateway;


    @Before
    public void beforeEachTest() {
        myJmsGateway = applicationContext.getBean("myJmsGateway", MyJmsGateway.class);
    }

    @Test
    public void testJmsSend() {
        myJmsGateway.sendMyMessage("myHeaderValue", "MY PayLoad");
        Assert.assertNotNull(jmsTemplate.receiveAndConvert("my.inbound.queue"));

    }

}
