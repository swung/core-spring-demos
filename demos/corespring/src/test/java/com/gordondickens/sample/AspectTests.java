/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gordondickens.sample;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.zip.DataFormatException;

import javax.naming.InsufficientResourcesException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AspectTests {
	Logger logger = LoggerFactory.getLogger(AspectTests.class);

	// TESTING Autowired. See Log Output, after running.
	@Autowired
	private MyExceptionThrower myExceptionThrower;

	@Test
	public void testExceptionAOP() throws Exception {
		logger.debug("***** TEST Throwing Data Format Exception *****");
		try {
			myExceptionThrower.throwDFE();
		} catch (DataFormatException dae) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Expected DataFormatException");
		}

	}

	@Test
	public void testExceptionAOP2() throws Exception {
		logger.debug("***** TEST Throwing Insufficient Resources Exception *****");
		try {
			myExceptionThrower.throwIRE();
		} catch (InsufficientResourcesException ire) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Expected InsufficientResourcesException");
		}

	}

}
