package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MailRuInboxPage;
import pages.MailRuMainPage;
import pages.MailRuSendMessagePage;

/**
 * Тестирование mail.ru
 */
public class MailRuTest {
    private final String testLogin = "testingMailRu@bk.ru";
    @SuppressWarnings("FieldCanBeLocal")
    private final String testPassword = "123456789q";

    private WebDriver driver;
    public static final long TIMEOUT_IN_SECONDS = 10;

    @Before
    public void setUp() throws Exception {
        //настраеваем путь до вебдрайвера
        final String  chromeDriverPath = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //инициализируем вебдрайвер
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

    /**
     * Логин с главной страницы
     */
    @Test
    public void login() {
        MailRuMainPage mainPage = new MailRuMainPage(driver);
        driver.get(MailRuMainPage.URL);
        mainPage.setUserName(testLogin);
        mainPage.setPassword(testPassword);
        mainPage.clickLogin();
    }

    /**
     * Нажимаем кнопку "Написать письмо"
     */
    @Test
    public void createMessageClick() {
        login();
        MailRuInboxPage inboxPage = new MailRuInboxPage(driver);
        inboxPage.waitForPageToLoad();
        assert driver.getCurrentUrl().contains(inboxPage.URL_PART);
        inboxPage.createMessageClick();

    }

    /**
     * Заполняем письмо и отправляем
     */
    @Test
    public void sendMessage() {
        createMessageClick();
        MailRuSendMessagePage sendMessagePage = new MailRuSendMessagePage(driver);
        sendMessagePage.waitForPageToLoad();
        assert driver.getCurrentUrl().contains(sendMessagePage.URL_PART);
        sendMessagePage.setReceiver(testLogin);
        sendMessagePage.setSubject("Test");
        sendMessagePage.setMessage("This is test email");
        sendMessagePage.sendMessageButtonClick();
    }

}