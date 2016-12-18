package by.bsu.testing.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage
{
    private final Logger logger = Logger.getLogger(LoginPage.class);
    private final String BASE_URL = "https://twitter.com/";

    @FindBy(xpath = "//div[@class='username field']/input[@id='signin-email']")
    private WebElement inputLogin;

    @FindBy(xpath = "//div[@class='password flex-table-form']/input[@id='signin-password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//form[@class='t1-form signin']//button[@type='submit']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//span[@class='u-linkComplex-target']")
    private WebElement linkLoggedInUser;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened login page");
    }

    public void login(String username, String password)
    {
        inputLogin.click();
        inputLogin.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
        logger.info("Logged in");
    }

    public String getLoggedInUserName()
    {
        return linkLoggedInUser.getText();
    }

}