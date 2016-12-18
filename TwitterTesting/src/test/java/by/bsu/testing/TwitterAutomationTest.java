package by.bsu.testing;

import by.bsu.testing.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class TwitterAutomationTest {
    private Steps steps;
    private final String USERNAME = "vanek_yahoo@mail.ru";
    private final String PASSWORD = "2432976tT";
    private final String SEARCH_REQUEST = "mountains";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }


    @Test(description = "Login to Twitter")
    public void oneCanLoginTwitter()
    {
        steps.loginTwitter(USERNAME, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn(USERNAME));
    }

    @Test(description = "Create Tweet")
    public void oneCanCreateTweet()
    {
        steps.loginTwitter(USERNAME, PASSWORD);
        String tweet = UUID.randomUUID().toString();
        steps.createTweet(tweet);
        Assert.assertTrue(steps.isTweetCreated(tweet));
    }

    @Test(description = "Delete first tweet in stream")
    public void oneCanDeleteTweet()
    {
        steps.loginTwitter(USERNAME, PASSWORD);
        Assert.assertTrue(steps.deleteFirstTweet());
    }

    @Test(description = "Search on Twitter by hashtag")
    public void oneCanSearchOnTwitter()
    {
        steps.loginTwitter(USERNAME, PASSWORD);
        Assert.assertTrue(steps.searchByHashtag(SEARCH_REQUEST));
    }

    @Test(description = "Send message to yourself")
    public void oneCanSendMessageOnTwitter()
    {
        steps.loginTwitter(USERNAME, PASSWORD);
        String message = UUID.randomUUID().toString();
        steps.sendMessage(USERNAME, message);
        Assert.assertTrue(steps.isMessageSent(message));
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}
