package pages;

import base.BasePage;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AllureUtils;

import java.time.Duration;

@Epic("LCW Automation")
@Feature("Product Page")
public class ProductPage extends BasePage {



    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step("Apply size filters (5-6, 6, and 6-7)")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Ürünlere ait beden filtrelerini sırasıyla uygular.")
    public void applyFilters() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        closeCookieBanner();
        closeNotificationPopup();

        try {
            System.out.println("Filtreler uygulanıyor...");

            WebElement sizeFilter_6 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='root']/div/div[2]/div[1]/div[6]/div/div[1]/div/div[3]/div/div[2]/div[3]/div[27]/div/span")
            ));
            scrollToElement(sizeFilter_6);
            sizeFilter_6.click();
            System.out.println("6 Yaş filtresi başarıyla seçildi.");
            Thread.sleep(3000);

            scrollPageDown();

            // Örnek => 5-6 Yaş filtresi
            retryClickWithPageReload(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[4]/div/div[2]/div[3]/div[27]/div/span"),
                    "5-6 Yaş filtresi"
            );
            scrollPageDown();
            retryClickWithPageReload(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[4]/div/div[2]/div[3]/div[28]/div/span"),
                    "6-7 Yaş filtresi"
            );

            retryClickWithPageReload(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[5]/div/div[2]/div[3]/div[1]"),
                    "Bej"
            );
            scrollToTopMoveMouseAndClickElement();
            Thread.sleep(5000);
            navigateAndSelectSizeAndAddToCart();
            navigateToCart();
            changeQuantityAndClick();
            addFavourite();
            navigateFavouritesPage();

        } catch (Exception e) {
            System.err.println("Filtreleme sırasında hata oluştu: " + e.getMessage());
        }
    }

    @Step("Change item quantity (first set to 2, then back to 1)")
    @Severity(SeverityLevel.MINOR)
    public void changeQuantityAndClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='ShoppingCartContent']/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div/input")
            ));

            WebElement blankArea = driver.findElement(By.xpath("//body"));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].value='2'; arguments[0].dispatchEvent(new Event('change'));",
                    quantityInput
            );
            System.out.println("Adet 2 olarak ayarlandı.");

            Thread.sleep(3000);
            blankArea.click();
            System.out.println("Boş alana tıklanarak value onaylandı.");

            Thread.sleep(2000);
            quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='ShoppingCartContent']/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div/input")
            ));

            js.executeScript(
                    "arguments[0].value='1'; arguments[0].dispatchEvent(new Event('change'));",
                    quantityInput
            );
            System.out.println("Adet 1 olarak ayarlandı.");

            Thread.sleep(1000);
            blankArea.click();
            System.out.println("Tekrar boş alana tıklanarak value onaylandı.");

        } catch (Exception e) {
            System.err.println("İşlem sırasında hata oluştu: " + e.getMessage());
            AllureUtils.takeScreenshot(driver);
        }
    }

    @Step("Add item to favourites")
    @Severity(SeverityLevel.CRITICAL)
    public void addFavourite() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement addFav = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(@id, 'Cart_addFavorite_')]")
            ));

            if (!addFav.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addFav);
                Thread.sleep(2000);
            }
            addFav.click();
            System.out.println("Ürün favorilere eklendi!");

            Thread.sleep(3000);

        } catch (Exception e) {
            System.err.println("Favoriye ekleme işlemi sırasında hata oluştu: " + e.getMessage());
            AllureUtils.takeScreenshot(driver);
        }
    }

    @Step("Navigate to favourites page")
    @Severity(SeverityLevel.NORMAL)
    public void navigateFavouritesPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement goFavPage = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='header__container']/header/div[1]/div[3]/div/div[1]")
            ));
            Thread.sleep(2000);
            goFavPage.click();
            System.out.println("Favoriler sayfasına gidildi!");
            Thread.sleep(3000);

        } catch (Exception e) {
            System.err.println("Favoriler sayfasına gidilemedi: " + e.getMessage());
        }
    }
    @Step("Select size and add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    private void navigateAndSelectSizeAndAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Ürün detayına yönlendirme
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[2]/div[4]/a/div[1]/img")
            ));
            productLink.click();
            System.out.println("Ürün detay sayfasına yönlendirildi.");

            // Beden seçimi
            for (int i = 1; i <= 8; i++) {
                String sizeXPath = "//*[@id=\"product-detail-add-to-card-operation\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/button[" + i + "]";
                WebElement sizeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sizeXPath)));

                if (sizeElement.isEnabled()) {
                    sizeElement.click();
                    System.out.println("Aktif beden seçildi: " + sizeElement.getText());
                    break;
                }
            }
            Thread.sleep(2000);

            // Sepete ekleme
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"product-detail-add-to-card-operation\"]/div/div[2]/button")
            ));
            scrollPageDown();
            addToCartButton.click();
            System.out.println("Ürün sepete eklendi.");

            // Sepete eklenme durumunu kontrol et
            WebElement cartSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Ürün sepete eklendi')]") // Sepete eklenme mesajının XPath'ini kontrol edin
            ));
            System.out.println("Sepete eklenme doğrulandı: " + cartSuccessMessage.getText());

            // Sepet ekranına git
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"header-cart\"]") // Sepet butonunun XPath'ini kontrol edin
            ));
            cartButton.click();
            System.out.println("Sepet ekranına geçildi.");

        } catch (Exception e) {
            System.err.println("İşlem sırasında hata oluştu: " + e.getMessage());
            AllureUtils.takeScreenshot(driver);
        }
    }


    @Step("Navigate to cart")
    @Severity(SeverityLevel.NORMAL)
    private void navigateToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[2]/a")
            ));
            productLink.click();
            System.out.println("Sepet sayfasına yönlendirildi.");
        } catch (Exception e) {
            System.err.println("Sepet sayfasına yönlendirilemedi: " + e.getMessage());
            AllureUtils.takeScreenshot(driver);
        }
    }



}
