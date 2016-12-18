package by.bsu.testing.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage extends AbstractPage {
    private final Logger logger = Logger.getLogger(LoginPage.class);

    private final String BASE_URL = "https://twitter.com/pupkina_test";

    @FindBy(id = "stream-items-id")
    private WebElement twitterStream;

    @FindBy(xpath = "//ol[@id = 'stream-items-id']/li[1]//button[contains(@class,'js-dropdown-toggle')]")
    private WebElement moreButtonLatestTweet;

    @FindBy(xpath = "//ol[@id = 'stream-items-id']/li[1]//li[@class = 'js-actionDelete']//button")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[contains(@class, 'profile-stream')]//ol[@id = 'stream-items-id']/li[1]//p[contains(@class, 'tweet-text')]")
    private WebElement firstTweetText;

    @FindBy(xpath = "//div[contains(@class, 'profile-stream')]//ol[@id = 'stream-items-id']/li")
    private List<WebElement> tweetContainers;

    public ProfilePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened profile page");
    }

    public boolean hasTweet(String tweetText)  {
        for (WebElement tweet: twitterStream.findElements(By.className("js-tweet-text"))){
            if (tweetText.equals(tweet.getText())) return true;
        }
        return false;
    }

    public void deleteTweet() {
            moreButtonLatestTweet.click();
            deleteButton.click();
            driver.switchTo().activeElement().click();
            logger.info("Tweet deleted");
    }

    public String getFirstTweetText() {
        return firstTweetText.getText();
    }

    public boolean hasTweets() {
        return (tweetContainers.size() > 0);
    }
}
