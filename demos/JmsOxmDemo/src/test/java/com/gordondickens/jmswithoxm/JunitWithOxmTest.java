package com.gordondickens.jmswithoxm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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
		jmsTemplate.convertAndSend(TEST_DEST, account);
		
		Account account2 = (Account) jmsTemplate.receiveAndConvert(TEST_DEST);
		assertNotNull("Account MUST return from JMS", account2);
		
		assertEquals("Name MUST match", account.getName(), account2.getName());
		assertEquals("Description MUST match", account.getDescription(), account2.getDescription());
		assertEquals("Balance MUST match", account.getBalance(), account2.getBalance());
	}

	@Ignore
	@Test
	public void testReceivingMessage() {

	}

	private Account generateTestMessage() {
		Account account = new Account();
		account.setBalance(new BigDecimal(12345.67));
		account.setDescription("A no account varmint");
		account.setName("Waskally Wabbit");
		logger.debug("Generated Test Message: " + account.toString());
		return account;
	}

	// public String convertToXml(Object source, Class... type) {
	// String result;
	// StringWriter sw = new StringWriter();
	// try {
	// JAXBContext context = JAXBContext.newInstance(type);
	// Marshaller marshaller = context.createMarshaller();
	// marshaller.marshal(source, sw);
	// result = sw.toString();
	// } catch (JAXBException e) {
	// throw new RuntimeException(e);
	// }
	//
	// return result;
	// }

}
