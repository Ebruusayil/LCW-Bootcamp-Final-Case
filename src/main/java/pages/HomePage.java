package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {


    private By childrenCategory = By.xpath("//a[@class='menu-header-item__title' and @href='/cocuk-bebek-h3']");
    private By montAndKaban = By.xpath("//a[@href='/kiz-cocuk-dis-giyim-t-1010']"); // Mont ve Kaban

    public HomePage(WebDriver driver) {
        super(driver);

    }

    @Step("Navigate to Çocuk & Bebek category")
    public void navigateToChildrenCategory() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(childrenCategory));
            categoryLink.click();
            System.out.println("Çocuk & Bebek kategorisine başarıyla gidildi.");
        } catch (Exception e) {
            System.err.println("Çocuk & Bebek kategorisine giderken hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    @Step("Navigate to Kız Çocuk category")
    public void navigateToGirlChildrenCategory() {
        driver.navigate().to("https://www.lcw.com/kiz-bebek-dis-giyim-t-1009");
        /*try {
            //  Çocuk & Bebek kategorisine hover yap
            Actions actions = new Actions(driver);
            WebElement childrenCategory = driver.findElement(By.xpath("//a[@class='menu-header-item__title' and @href='/cocuk-bebek-h3']"));
            actions.moveToElement(childrenCategory).perform();

            //Kız Çocuk kategorisini bekle ve tıkla
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement subCategoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'KIZ ÇOCUK')]")));
            subCategoryLink.click();
            System.out.println("Kız Çocuk kategorisine başarıyla gidildi.");
        } catch (Exception e) {
            System.err.println("Kız Çocuk kategorisine giderken hata oluştu: " + e.getMessage());
            throw e; // Hatanın TestNG'de görünmesini sağlamak için yeniden fırlatılır.
        }*/
    }


    @Step("scrollToSizeFilterHeader")
    public void scrollToSizeFilterHeader() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement sizeFilterHeader = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[6]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sizeFilterHeader);

            System.out.println("Beden başlığı başarıyla görüntülendi.");
        } catch (Exception e) {
            System.err.println("Beden başlığına kaydırma sırasında hata oluştu: " + e.getMessage());
        }
    }
}

