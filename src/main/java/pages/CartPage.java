package pages;

import base.BasePage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
 /*   private By productName = By.xpath("//div[@class='cart-item-name']");
    private By productColor = By.xpath("//div[@class='cart-item-color']");
    private By productQuantity = By.xpath("//input[@class='cart-item-quantity']");
    private By totalAmount = By.xpath("//div[@class='total-price']");
    private By proceedToCheckoutButton = By.id("proceedToCheckout");*/

    public CartPage(WebDriver driver) {
        super(driver);
    }


    @Step("Navigate to cart")
    @Severity(SeverityLevel.NORMAL)
    public void navigateToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[2]/a")
            ));
            productLink.click();
            System.out.println("Sepet sayfasına yönlendirildi.");
        } catch (Exception e) {
            System.err.println("Sepet sayfasına yönlendirilemedi: " + e.getMessage());
        }
    }

    @Step("Select size and add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void navigateAndSelectSizeAndAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[2]/div[4]/a/div[1]/img")
            ));
            productLink.click();
            System.out.println("Ürün detay sayfasına yönlendirildi.");

            for (int i = 1; i <= 8; i++) {
                String sizeXPath = "//*[@id=\"product-detail-add-to-card-operation\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/button[" + i + "]";
                WebElement sizeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sizeXPath)));

                if (sizeElement.isEnabled()) {
                    sizeElement.click();
                    System.out.println("Aktif beden seçildi: " + sizeElement.getText());
                    break;
                }
            }
            Thread.sleep(3000);

            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"product-detail-add-to-card-operation\"]/div/div[2]/button")
            ));
            addToCartButton.click();
            System.out.println("Ürün sepete eklendi.");
            Thread.sleep(3000);

        } catch (Exception e) {
            System.err.println("İşlem sırasında hata oluştu: " + e.getMessage());
        }
    }

}
