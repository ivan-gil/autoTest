package by.bsu.testing.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends AbstractPage {
    private final String BASE_URL = "https://twitter.com/search-home";
    private final Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(xpath = "//div[contains(@class, 'search-stream')]//ol[contains(@class, 'stream-items')]")
    private WebElement searchResults;

    public SearchPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened search page");
    }

    public boolean isFoundCorrectly(String searchRequest) {
        for(WebElement searchedText: searchResults.findElements(By.xpath("//p[contains(@class, 'js-tweet-text')]//strong"))){
            if (!(searchRequest.equals(searchedText.getText().toLowerCase()))) {
                return false;
            }
        }
        return true;
    }
}
