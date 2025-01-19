package base;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class BasePage {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public final String baseURL = "https://www.lcw.com/";

    private By cookieBanner = By.id("cookieseal-banner-accept");

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

    public void scrollPageDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500);");
        System.out.println("Sayfa aşağı kaydırıldı.");
    }
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        System.out.println("Sayfa en üstüne kaydırıldı.");
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void closeNotificationPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement notificationPopup = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Engelle')]")
            ));
            notificationPopup.click();
            System.out.println("Bildirim izni pop-up'ı kapatıldı.");
        } catch (Exception e) {
            System.out.println("Bildirim izni pop-up'ı bulunamadı veya zaten kapalı.");
        }
    }

    public void retryClickWithPageReload(By locator, String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(locator)
                ));
                scrollToElement(element);
                element.click();
                System.out.println(description + " başarıyla seçildi.");
                Thread.sleep(3000);
                return;
            } catch (Exception e) {
                System.err.println(description + " seçilemedi, tekrar deneniyor... (" + (i + 1) + "/3)");
            }
        }
        throw new RuntimeException(description + " seçilemedi.");
    }

    public void scrollToTopMoveMouseAndClickElement() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            System.out.println("Sayfa en üste kaydırıldı.");

            Actions actions = new Actions(driver);
            actions.moveByOffset(0, 0).perform();
            System.out.println("Mouse en sol üst köşeye taşındı.");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[4]/div[2]/div/a[4]")
            ));
            element.click();
            System.out.println("Belirtilen XPath'e tıklandı.");
        } catch (Exception e) {
            System.err.println("İşlem sırasında hata oluştu: " + e.getMessage());
        }
    }

    public void closeCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieBannerElement = wait.until(ExpectedConditions.elementToBeClickable(cookieBanner));
            cookieBannerElement.click();
            System.out.println("Çerez banner'ı kapatıldı.");
        } catch (Exception e) {
            System.out.println("Çerez banner'ı bulunamadı veya zaten kapalı.");
        }
    }

}
