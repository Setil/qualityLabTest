package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Главная страница mail.ru
 */
public class MailRuMainPage {
    private WebDriver driver;
    public static final String URL = "http://mail.ru";
    private By loginLocator = By.id("mailbox:login");
    private By passwordLocator = By.id("mailbox:password");
    private By loginButtonLocator = By.cssSelector("#auth input[type=submit]");

    public MailRuMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String userName) {
        driver.findElement(loginLocator).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButtonLocator).click();
    }

}
