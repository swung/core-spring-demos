package com.gordondickens.jmswithoxm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JunitWithOxmTest {
	private static final Logger logger = LoggerFactory
			.getLogger(JunitWithOxmTest.class);
	private static final String TEST_DEST = "oxmTestQueue";

	@Autowired
	JmsTemplate jmsTemplate;

	@Test
	public void testSendingMessage() {
		Account account = generateTestMessage();
		jmsTemplate.convertAndSend(TEST_DEST, account,
				new MessagePostProcessor() {
					@Override
					public Message postProcessMessage(Message message)
							throws JMSException {
						if (message instanceof BytesMessage) {
							BytesMessage messageBody = (BytesMessage) message;
							// message is in write mode, close & reset to start
							// of byte stream
							messageBody.reset();

							Long length = messageBody.getBodyLength();
							logger.debug("***** MESSAGE LENGTH is {} bytes",
									length);
							byte[] byteMyMessage = new byte[length.intValue()];
							int red = messageBody.readBytes(byteMyMessage);
							logger.debug(
									"***** SENDING MESSAGE - \n<!-- MSG START -->\n{}\n<!-- MSG END -->",
									new String(byteMyMessage));
						}
						return message;
					}
				});
		Account account2 = (Account) jmsTemplate.receiveAndConvert(TEST_DEST);
		assertNotNull("Account MUST return from JMS", account2);
		assertEquals("Name MUST match", account.getName(), account2.getName());
		assertEquals("Description MUST match", account.getDescription(),
				account2.getDescription());
		assertEquals("Balance MUST match", account.getBalance(),
				account2.getBalance());
	}

	private Account generateTestMessage() {
		Account account = new Account();
		account.setBalance(new BigDecimal(12345.67));
		account.setDescription("A no account varmint");
		account.setName("Waskally Wabbit");
		logger.debug("Generated Test Message: " + account.toString());
		return account;
	}
}
