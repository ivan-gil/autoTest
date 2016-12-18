package by.bsu.testing.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

    private final Logger logger = Logger.getLogger(LoginPage.class);

    private final String BASE_URL = "https://twitter.com/";

    @FindBy(id = "tweet-box-home-timeline")
    private WebElement tweetTextBox;

    @FindBy(xpath = "//div[contains(@class, 'timeline-tweet-box')] //button[contains(@class,'tweet-btn')]")
    private WebElement tweetBtnSubmit;

    @FindBy(className = "search-input")
    private WebElement searchField;

    @FindBy(xpath = "//li[@class = 'dm-nav']/a")
    private WebElement messagesTab;

    @FindBy(xpath = "//button[contains(@class, 'dm-new-button')]")
    private WebElement newMessageButton;

    @FindBy(xpath = "//button[contains(@class, 'dm-initiate-conversation')]")
    private WebElement startConversationButton;

    @FindBy(xpath = "//button[contains(@class, 'messaging')]")
    private WebElement sendMessageButton;

    @FindBy(xpath = "//ul[@class = 'DMInbox-conversations']/li[1]")
    private WebElement latestConversationButton;

    @FindBy(xpath = "//div[contains(@class, 'DMConversation-content')]/div[last()]//p")
    private WebElement latestMessage;

    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void writeNewTweet(String tweetText)
    {
        tweetTextBox.click();
        tweetTextBox.sendKeys(tweetText);
        tweetBtnSubmit.click();
        logger.info("Tweeted: " + tweetText);
    }

    public void inputSearchRequest(String searchRequest) {
        searchField.click();
        searchField.sendKeys("#" + searchRequest);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        logger.info("Input search request: " + searchRequest);
    }

    public void sendMessageToYourself(String username, String message) {
        messagesTab.click();
        newMessageButton.click();
        driver.switchTo().activeElement().sendKeys(username);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        startConversationButton.click();
        driver.switchTo().activeElement().sendKeys(message);
        sendMessageButton.click();
        logger.info("Message sent:" + message);
    }

    public String getLatestMessageInConversation() {
        messagesTab.click();
        latestConversationButton.click();
        return (latestMessage.getText());
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened main page");
    }
}
