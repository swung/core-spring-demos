package com.chariot.romantidbits;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TwitterTests {
	private static final Logger logger = LoggerFactory
			.getLogger(TwitterTests.class);
	private static final String CHARIOT = "chariotsolution";
	private final TwitterTemplate twitterTemplate = new TwitterTemplate();

	@Rule
	public TestName name = new TestName();

	@Test
	public void testTwitterFriends() {
		logger.debug("Running '{}'...", name.getMethodName());

		List<TwitterProfile> friends = twitterTemplate.friendOperations().getFriends(CHARIOT);

		assertNotNull(friends);
		assertTrue(friends.size() > 0);

		logger.debug(CHARIOT + "'s friends:");
		for (TwitterProfile coolPerson : friends) {
			logger.debug("\t{}", coolPerson.getName());
		}
	}

	@Test
	public void testTwitterWithRestTemplate() {
		logger.debug("Running '{}'...", name.getMethodName());

		final RestTemplate restTemplate = new RestTemplate();
		String search = "#phillyete";
		String results = restTemplate.getForObject(
				"http://search.twitter.com/search.json?q={search}",
				String.class, search);
		assertNotNull(results);
		logger.debug("Search Results: {}", results);
	}

	@Test
	public void testTwitterTimeline() {
		logger.debug("Running '{}'...", name.getMethodName());
		List<Tweet> userTimeline = twitterTemplate.timelineOperations().getUserTimeline(CHARIOT);

		assertNotNull(userTimeline);
		assertTrue(userTimeline.size() > 0);
		logger.debug("Total Messages {}", userTimeline.size());

		for (Tweet tweet : userTimeline) {
			String date = (new SimpleDateFormat("M/d/yy hh:mm")).format(tweet
					.getCreatedAt());
			logger.debug("Tweet on {} - {}",
					new String[] { date, tweet.getText() });
		}
	}

}
