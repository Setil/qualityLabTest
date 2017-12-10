package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.MailRuTest;

/**
 * Страница создания нового письма
 */
public class MailRuSendMessagePage {
    private WebDriver driver;
    private By toLocator = By.cssSelector("textarea[data-original-name=To]");
    private By subjectLocator = By.cssSelector("input[name=Subject]");
    /**
     * Локатор iframe для ввода текста сообщения
     */
    private By messageTextFrameLocator = By.cssSelector("[id$=composeEditor_ifr]");
    /**
     * Локатор внутри iframe для ввода текста сообщения
     */
    private By messageTextInnerLocator = By.tagName("body");
    private By sendMessageButtonLocator = By.cssSelector("[data-name=send]");
    public final String URL_PART = "/compose/";

    public MailRuSendMessagePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setReceiver(String receiverEmail) {
        driver.findElement(toLocator).sendKeys(receiverEmail);
    }

    public void setSubject(String subject) {
        driver.findElement(subjectLocator).sendKeys(subject);
    }

    public void setMessage(String messageText) {
        //переключаемся на внутренний фрейм WYSIWYG редактора
        driver.switchTo().frame(driver.findElement(messageTextFrameLocator));
        //отправляем текст редактору
        WebElement element = driver.findElement(messageTextInnerLocator);
        element.sendKeys(messageText);
        //переключаемся обратно на основной фрейм
        driver.switchTo().parentFrame();
    }

    public void waitForPageToLoad() {
        WebDriverWait waiter = new WebDriverWait(driver, MailRuTest.TIMEOUT_IN_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(messageTextFrameLocator));
    }

    public void sendMessageButtonClick() {
        driver.findElement(sendMessageButtonLocator).click();
    }
}
