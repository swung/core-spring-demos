package com.chariot.romantidbits;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.oauth1.OAuth1Template;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TwitterOauthTests {
	private static final Logger logger = LoggerFactory
			.getLogger(TwitterTests.class);
	private static final String GDICKENS = "gdickens";
	private static final String ACCESS_TOKEN_URL = "https://api.twitter.com/oauth/access_token";
	private static final String REQUEST_TOKEN_URL = "hhttps://api.twitter.com/oauth/request_token";
	private static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
	private static final String CONSUMER_KEY = "XENDydJQ62d7AA3uFJWCAw";
	private static final String CONSUMER_SECRET = "rweT0o3hwgY2C2CNzC8lbW5KUGVWBRZZsxnXJbzWwM";

	private OAuth1Template oauth10a;

	@Autowired
	TwitterTemplate twitterTemplate;

	@Rule
	public TestName name = new TestName();

	@Before
	public void beforeEachTest() {
		assertNotNull("TwitterTemplate MUST Exist", twitterTemplate);
		oauth10a = new OAuth1Template("consumer_key", "consumer_secret",
				REQUEST_TOKEN_URL, AUTHORIZE_URL
						+ "?oauth_token={request_token}", ACCESS_TOKEN_URL);
	}

	@Test
	public void testSecureConnection() {
		logger.debug("Running '{}'...", name.getMethodName());

		try {
			String profileId = twitterTemplate.userOperations().getScreenName();
			assertNotNull("Profile Id MUST exist", profileId);
			assertEquals("Profile Id should be 'gdickens'", profileId, GDICKENS);

			logger.debug("Profile Id '{}'", profileId);
		} catch (InvalidAuthorizationException e) {
			logger.error(e.getMessage(), e);
			fail("Invalid Authorization!");
		}
	}

	@Test
	public void buildAuthorizeUrl() {
		assertEquals(AUTHORIZE_URL + "?oauth_token=request_token",
				oauth10a.buildAuthorizeUrl("request_token", null));
	}

	// @Test
	// public void fetchNewRequestToken_OAuth10a() {
	// MockRestServiceServer mockServer =
	// MockRestServiceServer.createServer(oauth10a.getRestTemplate());
	// HttpHeaders responseHeaders = new HttpHeaders();
	// responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	// mockServer
	// .expect(requestTo("https://www.someprovider.com/oauth/requestToken"))
	// .andExpect(method(POST))
	// .andExpect(
	// headerContains("Authorization",
	// "oauth_callback=\"http%3A%2F%2Fwww.someclient.com%2Foauth%2Fcallback\""))
	// .andExpect(headerContains("Authorization", "oauth_version=\"1.0\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_signature_method=\"HMAC-SHA1\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_consumer_key=\"consumer_key\""))
	// .andExpect(headerContains("Authorization", "oauth_nonce=\""))
	// .andExpect(headerContains("Authorization", "oauth_signature=\""))
	// .andExpect(headerContains("Authorization", "oauth_timestamp=\""))
	// .andRespond(
	// withResponse(new ClassPathResource("requestToken.formencoded",
	// getClass()), responseHeaders));
	//
	// OAuthToken requestToken =
	// oauth10a.fetchNewRequestToken("http://www.someclient.com/oauth/callback");
	// assertEquals("1234567890", requestToken.getValue());
	// assertEquals("abcdefghijklmnop", requestToken.getSecret());
	// }
	//
	// @Test
	// public void fetchNewRequestToken_OAuth10() {
	// MockRestServiceServer mockServer =
	// MockRestServiceServer.createServer(oauth10.getRestTemplate());
	// HttpHeaders responseHeaders = new HttpHeaders();
	// responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	// mockServer.expect(requestTo("https://www.someprovider.com/oauth/requestToken"))
	// .andExpect(method(POST))
	// .andExpect(headerContains("Authorization", "oauth_version=\"1.0\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_signature_method=\"HMAC-SHA1\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_consumer_key=\"consumer_key\""))
	// .andExpect(headerContains("Authorization", "oauth_nonce=\""))
	// .andExpect(headerContains("Authorization", "oauth_signature=\""))
	// .andExpect(headerContains("Authorization", "oauth_timestamp=\""))
	// .andRespond(withResponse(new
	// ClassPathResource("requestToken.formencoded", getClass()),
	// responseHeaders));
	//
	// OAuthToken requestToken =
	// oauth10.fetchNewRequestToken("http://www.someclient.com/oauth/callback");
	// assertEquals("1234567890", requestToken.getValue());
	// assertEquals("abcdefghijklmnop", requestToken.getSecret());
	// }
	//
	// @Test
	// public void exchangeForAccessToken_OAuth10a() {
	// MockRestServiceServer mockServer =
	// MockRestServiceServer.createServer(oauth10a.getRestTemplate());
	// HttpHeaders responseHeaders = new HttpHeaders();
	// responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	// mockServer
	// .expect(requestTo("http://www.someprovider.com/oauth/accessToken"))
	// .andExpect(method(POST))
	// .andExpect(headerContains("Authorization", "oauth_version=\"1.0\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_signature_method=\"HMAC-SHA1\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_consumer_key=\"consumer_key\""))
	// .andExpect(headerContains("Authorization", "oauth_token=\"1234567890\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_verifier=\"verifier\""))
	// .andExpect(headerContains("Authorization", "oauth_nonce=\""))
	// .andExpect(headerContains("Authorization", "oauth_signature=\""))
	// .andExpect(headerContains("Authorization", "oauth_timestamp=\""))
	// .andRespond(withResponse(new ClassPathResource("accessToken.formencoded",
	// getClass()), responseHeaders));
	//
	// OAuthToken requestToken = new OAuthToken("1234567890",
	// "abcdefghijklmnop");
	// OAuthToken accessToken = oauth10a.exchangeForAccessToken(new
	// AuthorizedRequestToken(requestToken, "verifier"));
	// assertEquals("9876543210", accessToken.getValue());
	// assertEquals("ponmlkjihgfedcba", accessToken.getSecret());
	// }
	//
	// @Test
	// public void exchangeForAccessToken_OAuth10() {
	// MockRestServiceServer mockServer =
	// MockRestServiceServer.createServer(oauth10.getRestTemplate());
	// HttpHeaders responseHeaders = new HttpHeaders();
	// responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	// mockServer
	// .expect(requestTo("http://www.someprovider.com/oauth/accessToken"))
	// .andExpect(method(POST))
	// .andExpect(headerContains("Authorization", "oauth_version=\"1.0\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_signature_method=\"HMAC-SHA1\""))
	// .andExpect(headerContains("Authorization",
	// "oauth_consumer_key=\"consumer_key\""))
	// .andExpect(headerContains("Authorization", "oauth_token=\"1234567890\""))
	// .andExpect(headerContains("Authorization", "oauth_nonce=\""))
	// .andExpect(headerContains("Authorization", "oauth_signature=\""))
	// .andExpect(headerContains("Authorization", "oauth_timestamp=\""))
	// .andRespond(withResponse(new ClassPathResource("accessToken.formencoded",
	// getClass()), responseHeaders));
	//
	// OAuthToken requestToken = new OAuthToken("1234567890",
	// "abcdefghijklmnop");
	// OAuthToken accessToken = oauth10.exchangeForAccessToken(new
	// AuthorizedRequestToken(requestToken, "verifier"));
	// assertEquals("9876543210", accessToken.getValue());
	// assertEquals("ponmlkjihgfedcba", accessToken.getSecret());
	// }
}
