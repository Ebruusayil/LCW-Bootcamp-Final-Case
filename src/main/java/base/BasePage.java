package base;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class BasePage {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public final String baseURL = "https://www.lcw.com/";



    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    @BeforeAll
    public WebDriver initializeDriver() {
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments(new String[]{"--remote-allow-origins=*"});
        cop.addArguments(new String[]{"--disable-notifications"});
        driver = new ChromeDriver(cop);
        driver.navigate().to("https://www.lcw.com/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }
    public void closeCookieBannere() {
        try {
            WebElement cookieBannere = driver.findElement(By.id("cookie-banner-id")); // Çerez banner ID'sini buraya girin
            if (cookieBannere.isDisplayed()) {
                cookieBannere.click();
                System.out.println("Çerez banner'ı kapatıldı.");
            }
        } catch (Exception e) {
            System.out.println("Çerez banner'ı bulunamadı veya zaten kapalı.");
        }
    }
}
