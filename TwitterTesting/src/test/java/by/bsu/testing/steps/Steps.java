package by.bsu.testing.steps;

import java.util.concurrent.TimeUnit;

import by.bsu.testing.pages.MainPage;
import by.bsu.testing.pages.ProfilePage;
import by.bsu.testing.pages.SearchPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import by.bsu.testing.pages.LoginPage;


public class Steps {
    private WebDriver driver;

    private final Logger logger = Logger.getLogger(Steps.class);

    public void initBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(28, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);
        logger.info("Browser started");
    }

    public void closeDriver() {
        driver.quit();
        logger.info("Browser stopped");
    }

    public void loginTwitter(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isLoggedIn(String username) {
        LoginPage loginPage = new LoginPage(driver);
        return (loginPage.getLoggedInUserName().trim().toLowerCase().equals(username));
    }

    public void createTweet(String tweetText) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.writeNewTweet(tweetText);
    }

    public boolean isTweetCreated(String tweetText)
    {
        ProfilePage profilePage = new ProfilePage(driver);
        openNewTab();
        profilePage.openPage();
        return (profilePage.hasTweet(tweetText));
    }


    public boolean deleteFirstTweet() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openPage();
        if (profilePage.hasTweets()) {
            String firstTweet = profilePage.getFirstTweetText();
            profilePage.deleteTweet();
            openNewTab();
            profilePage.openPage();
            return (!(firstTweet.equals(profilePage.getFirstTweetText())));
        } else {
            logger.info("Nothing to delete");
            return true;
        }
    }

    public boolean searchByHashtag(String searchRequest) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.inputSearchRequest(searchRequest);
        SearchPage searchPage = new SearchPage(driver);
        return (searchPage.isFoundCorrectly(searchRequest));
    }

    public void sendMessage(String username, String message) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.sendMessageToYourself(username, message);
    }


    public boolean isMessageSent(String message) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        return (message.equals(mainPage.getLatestMessageInConversation()));
    }

    private void openNewTab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        logger.info("Opened new tab");
    }
}