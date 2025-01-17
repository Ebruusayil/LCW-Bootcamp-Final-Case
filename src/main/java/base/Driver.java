package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // WebDriverManager ile ChromeDriver'ı ayarla
            WebDriverManager.chromedriver().setup();

            // ChromeOptions oluştur ve ayarları ekle
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications"); // Bildirimleri kapat
            options.addArguments("--disable-popup-blocking"); // Popup engellemeyi devre dışı bırak
            options.addArguments("--start-maximized"); // Tarayıcıyı tam ekran başlat

            // ChromeDriver'ı ayarlarla başlat
            driver = new ChromeDriver(options);

            // Pencereyi maksimum boyuta getir
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
