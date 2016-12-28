using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Autotest
{
    [TestFixture]
    public class TestingPlugin
    {
        private IWebDriver webDriver;
        private WebDriverWait wait;
        private String invalidMessage;
        private String invalidEmail;
        private String playerName;
        [OneTimeSetUp]
        public void Setup()
        {
            webDriver = new ChromeDriver();
            wait = new WebDriverWait(webDriver, TimeSpan.FromSeconds(10));
            playerName = "Russell Westbrook";
            invalidMessage = "Please enter a valid email address.";
            invalidEmail = "aaa";

            webDriver.Navigate().GoToUrl("http://stats.nba.com/");
        }
        [OneTimeTearDown]
        public void TearDown()
        {
            webDriver.Quit();
        }
        
        [Test]
        public void navigationToPlayersProfile()
        {
            webDriver.Navigate().GoToUrl("http://stats.nba.com/players/");
            IWebElement playerProfileLink = webDriver.FindElements(By.CssSelector(".category-table__text>a"))[0];
            playerName = playerProfileLink.Text;
            playerProfileLink.Click();
            IWebElement breadCrumb = webDriver.FindElement(By.CssSelector("body>main>div.stats-container__inner>div>div>div.breadcrumbs>span.ng-binding"));
            Assert.IsTrue(playerName == breadCrumb.Text);
        }
        [Test]
        public void checkingTopStories()
        {
            webDriver.Navigate().GoToUrl("http://www.nba.com/news");
            IWebElement storyToSelectTitle = webDriver.FindElements(By.ClassName("tile"))[0];
            IWebElement link = storyToSelectTitle.FindElement(By.TagName("a"));
            String nameOfTitle = storyToSelectTitle.FindElement(By.TagName("h4")).Text;
            wait.Until(ExpectedConditions.ElementToBeClickable(By.ClassName("tile>a")));
            link.Click();
            IWebElement selectedStoryTitle = webDriver.FindElement(By.CssSelector("#block-league-content>div>article>div>div.row.expanded>div>div>h2"));
            Assert.IsTrue(nameOfTitle == selectedStoryTitle.Text);
        }
        [Test]
        public void checkEmailValdationForStringWithoutAtSymbol()
        {
            webDriver.Navigate().GoToUrl("https://secure.nba.com/membership/user/register");
            IWebElement emailInput = webDriver.FindElement(By.CssSelector("#nbaMembershipEmailAddressWrapper>input"));
            emailInput.SendKeys(invalidEmail);
            emailInput.SendKeys(Keys.Enter);
            wait.Until(ExpectedConditions.ElementIsVisible(By.ClassName("parsley-type")));
            IWebElement messageTag = webDriver.FindElement(By.ClassName("parsley-type"));
            Assert.IsTrue(messageTag.Text == invalidMessage);

        }
        [Test]
        public void navigationThroughMenu()
        {
            IWebElement menu = webDriver.FindElement(By.CssSelector(".nav-inner__menu.nav-inner__menu--left"));
            wait.Until(ExpectedConditions.ElementIsVisible(By.LinkText("Players")));
            IWebElement playerTab = menu.FindElement(By.LinkText("Players"));
            playerTab.Click();
            IWebElement breadCrumb = webDriver.FindElement(By.CssSelector("body>main>div.stats-container__inner>div>div.breadcrumbs>span:nth-child(2)"));
            Assert.IsTrue("http://stats.nba.com/players/" == webDriver.Url && breadCrumb.Text == "Players");
        }
        [Test]
        public void searchPlayers()
        {
            IWebElement searchIcon = webDriver.FindElement(By.ClassName("stats-search__icon"));
            searchIcon.Click();
            wait.Until(ExpectedConditions.ElementIsVisible(By.ClassName("stats-search__input")));
            IWebElement searchInput = webDriver.FindElement(By.ClassName("stats-search__input"));
            searchInput.SendKeys(playerName);
            wait.Until(ExpectedConditions.ElementIsVisible(By.ClassName("stats-search__results")));
            List<IWebElement> results = webDriver.FindElements(By.ClassName("stats-search__link-anchor")).ToList();
            Assert.True(!results.Select(result => result.Text.Contains(playerName)).ToList().Contains(false));
        }
    }
}
