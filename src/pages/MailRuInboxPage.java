package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.MailRuTest;

/**
 * Страница входящих сообщений. На нее перенаправляет автоматически после логина
 */
public class MailRuInboxPage {
    private WebDriver driver;
    private By createMessageButtonLocator = By.cssSelector("[data-name=compose]");
    public final String URL_PART = "/messages/inbox";

    public MailRuInboxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createMessageClick() {
        driver.findElement(createMessageButtonLocator).click();
    }

    public void waitForPageToLoad() {
        WebDriverWait waiter = new WebDriverWait(driver, MailRuTest.TIMEOUT_IN_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(createMessageButtonLocator));
    }
}
