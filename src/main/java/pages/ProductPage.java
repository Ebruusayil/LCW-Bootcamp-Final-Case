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

public class ProductPage extends BasePage {

    private By sizeFilter_5_6 = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[3]/div/div[2]/div[3]/div[26]/div/span");
    private By sizeFilter_6 = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[3]/div/div[2]/div[3]/div[27]/div/span");
    private By sizeFilter_6_7 = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[3]/div/div[2]/div[3]/div[28]/div/span");

    private By cookieBanner = By.id("cookieseal-banner-accept");

    public ProductPage(WebDriver driver) {
        super(driver);
    }
    private void createDebugMousePointer() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "if (!document.getElementById('debug-pointer')) {" +
                        "  var pointer = document.createElement('div');" +
                        "  pointer.id = 'debug-pointer';" +
                        "  pointer.style.width = '10px';" +
                        "  pointer.style.height = '10px';" +
                        "  pointer.style.border = '2px solid red';" +
                        "  pointer.style.borderRadius = '50%';" +
                        "  pointer.style.position = 'fixed';" +
                        "  pointer.style.zIndex = '10000';" +
                        "  pointer.style.pointerEvents = 'none';" +
                        "  pointer.style.transition = 'top 0.1s linear, left 0.1s linear';" +
                        "  document.body.appendChild(pointer);" +
                        "  document.addEventListener('mousemove', function(event) {" +
                        "    pointer.style.top = event.clientY + 'px';" +
                        "    pointer.style.left = event.clientX + 'px';" +
                        "  });" +
                        "}"
        );
        System.out.println("Debug mouse işaretçisi oluşturuldu.");
    }

    @Step("Apply size filters for 5-6, 6, and 6-7")
    public void applyFilters() {
        createDebugMousePointer();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Çerez bannerını kapat ve bildirim izin pop-up'ını kontrol et
        closeCookieBanner();
        closeNotificationPopup();

        try {
            // 5-6 Yaş filtresine tıkla
            WebElement sizeFilter_5_6 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='root']/div/div[2]/div[1]/div[6]/div/div[1]/div/div[3]/div/div[2]/div[3]/div[27]/div/span")
            ));

            scrollToElement(sizeFilter_5_6);
            sizeFilter_5_6.click();
            System.out.println("6 Yaş filtresi başarıyla seçildi.");
            Thread.sleep(3000); // Sayfanın güncellenmesini bekleyin

            // Sayfayı aşağı kaydır
            scrollPageDown();

            // 6 Yaş filtresine tıkla
            retryClickWithPageReload(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[4]/div/div[2]/div[3]/div[27]/div/span"),
                    "5-6 Yaş filtresi"
            );

            // Sayfayı aşağı kaydır
            scrollPageDown();

            // 6-7 Yaş filtresine tıkla
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
            //changeQuantityAndClick();
            addFavourite();
            navigateFavouritesPage();
        } catch (Exception e) {
            System.err.println("Filtreleme sırasında hata oluştu: " + e.getMessage());
        }
    }
    /*private void changeQuantityAndClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Input alanını bul
            WebElement quantityInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div/input")
            ));

            // Hedef tıklama alanını bul
            WebElement targetButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='ShoppingCartContent']/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[2']")
            ));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Değeri "2" yap ve DOM'u güncelle
            js.executeScript("arguments[0].value='2'; arguments[0].dispatchEvent(new Event('change'));", quantityInput);
            System.out.println("Adet 2 olarak ayarlandı.");

            // İlk tıklama
            Thread.sleep(2000); // Bekleme süresi
            targetButton.click();
            System.out.println("İlk tıklama gerçekleştirildi.");

            // Değeri "1" yap ve DOM'u güncelle
            js.executeScript("arguments[0].value='1'; arguments[0].dispatchEvent(new Event('change'));", quantityInput);
            System.out.println("Adet 1 olarak ayarlandı.");

            // İkinci tıklama
            Thread.sleep(2000); // Bekleme süresi
            targetButton.click();
            System.out.println("İkinci tıklama gerçekleştirildi.");
        } catch (Exception e) {
            System.err.println("İşlem sırasında hata oluştu: " + e.getMessage());
        }
    }
*/




    private void addFavourite(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            WebElement addFav = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"Cart_addFavorite_1767109658\"]")
            ));
            addFav.click();
            System.out.println("Favoriye eklendi!");
        }catch (Exception e){
            System.err.println("Favoriye eklenemedi: " + e.getMessage());
        }
    }
    private void navigateFavouritesPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            WebElement goFavPage = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[1]")
            ));
            goFavPage.click();
            System.out.println("Favoriler sayfasına gidildi!");
        }catch (Exception e){
            System.err.println("Favoriler sayfasına gidilemedi: " + e.getMessage());
        }
    }
    // Retry clicking an element after page reload
    private void retryClickWithPageReload(By locator, String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(locator)
                ));
                scrollToElement(element);
                element.click();
                System.out.println(description + " başarıyla seçildi.");
                Thread.sleep(3000); // Sayfanın güncellenmesini bekleyin
                return;
            } catch (Exception e) {
                System.err.println(description + " seçilemedi, tekrar deneniyor... (" + (i + 1) + "/3)");
            }
        }
        throw new RuntimeException(description + " seçilemedi.");
    }
    private void navigateToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            // Ürün detay sayfasına gitmek için tıklama
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[2]/a")
            ));
            productLink.click();
            System.out.println("Sepet sayfasına yönlendirildi.");
        } catch (Exception e) {
            System.err.println("Sepet sayfasına yönlendirilemedi: " + e.getMessage());
        }
    }
    private void navigateAndSelectSizeAndAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Ürün detay sayfasına gitmek için tıklama
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[2]/div[4]/a/div[1]/img")
            ));
            productLink.click();
            System.out.println("Ürün detay sayfasına yönlendirildi.");

            // Aktif olan ilk bedeni seç
            for (int i = 1; i <= 8; i++) { // Maksimum 8 beden kontrol ediliyor (12 yaş da dahil)
                String sizeXPath = "//*[@id=\"product-detail-add-to-card-operation\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/button[" + i + "]";
                WebElement sizeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sizeXPath)));

                // Eğer beden aktifse (enabled), tıklama işlemi yap
                if (sizeElement.isEnabled()) {
                    sizeElement.click();
                    System.out.println("Aktif beden seçildi: " + sizeElement.getText());
                    break;
                }
            }
            Thread.sleep(3000);
            // Sepete Ekle butonuna tıkla
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

    // Helper method to scroll the page down
    private void scrollPageDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500);"); // Sayfayı 500 piksel aşağı kaydırır
        System.out.println("Sayfa aşağı kaydırıldı.");
    }
    private void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);"); // Sayfayı en üstüne kaydırır
        System.out.println("Sayfa en üstüne kaydırıldı.");
    }


    // Helper method to scroll to an element
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Helper method to close notification pop-up
    private void closeNotificationPopup() {
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

    // Retry clicking an element
    private void retryClick(By locator, String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                scrollToElement(element);
                element.click();
                System.out.println(description + " başarıyla seçildi.");
                Thread.sleep(3000); // Sayfanın güncellenmesini bekleyin
                return;
            } catch (Exception e) {
                System.err.println(description + " seçilemedi, tekrar deneniyor... (" + (i + 1) + "/3)");
            }
        }
        throw new RuntimeException(description + " seçilemedi.");
    }
    // Helper method to close notification pop-up

    private void moveMouseToTopLeft() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).perform(); // Mouse'u (0, 0) koordinatına taşır
        System.out.println("Mouse en sol üst köşeye taşındı.");
    }
    private void scrollToTopMoveMouseAndClickElement() {
        try {
            // Sayfayı en üste kaydır
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            System.out.println("Sayfa en üste kaydırıldı.");

            // Mouse'u en sol üst köşeye taşı
            Actions actions = new Actions(driver);
            actions.moveByOffset(0, 0).perform();
            System.out.println("Mouse en sol üst köşeye taşındı.");

            // XPath ile belirtilen elemente tıkla
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

    // Çerez banner'ını kapatan metod
    private void closeCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieBannerElement = wait.until(ExpectedConditions.elementToBeClickable(cookieBanner));
            cookieBannerElement.click();
            System.out.println("Çerez banner'ı kapatıldı.");
        } catch (Exception e) {
            System.out.println("Çerez banner'ı bulunamadı veya zaten kapalı.");
        }
    }

    /*public void sortByMostSold() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Sayfayı aşağı kaydırarak sıralama butonunun görünür olmasını sağla
            scrollPageDown();

            // Sıralama combobox butonuna tıklayın
            WebElement sortDropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='root']/div/div[2]/div[1]/div[5]/div[1]/div/div/button")
            ));
            scrollToElement(sortDropdownButton); // Elementi görünür yapmak için kaydır
            sortDropdownButton.click();
            System.out.println("Sıralama combobox açıldı.");

            // 'En Çok Satanlar' seçeneğine tıklayın
            WebElement mostSoldOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='root']/div/div[2]/div[1]/div[5]/div[1]/div/div/div/a[5]")
            ));
            scrollToElement(mostSoldOption); // Elementi görünür yapmak için kaydır
            mostSoldOption.click();
            System.out.println("Sıralama: 'En Çok Satanlar' seçildi.");
        } catch (Exception e) {
            System.err.println("Sıralama işlemi sırasında hata oluştu: " + e.getMessage());
            retrySortByMostSold(); // Hata durumunda yeniden dene
        }
    }

    // Retry mekanizması
    private void retrySortByMostSold() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 3; i++) {
            try {
                // Sayfayı aşağı kaydırarak sıralama butonunun görünür olmasını sağla
                scrollPageDown();

                // Sıralama combobox butonuna tıklayın
                WebElement sortDropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='root']/div/div[2]/div[1]/div[5]/div[1]/div/div/button")
                ));
                scrollToElement(sortDropdownButton);
                sortDropdownButton.click();
                System.out.println("Sıralama combobox yeniden açıldı.");

                // 'En Çok Satanlar' seçeneğine tıklayın
                WebElement mostSoldOption = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='root']/div/div[2]/div[1]/div[5]/div[1]/div/div/div/a[5]")
                ));
                scrollToElement(mostSoldOption);
                mostSoldOption.click();
                System.out.println("Sıralama: 'En Çok Satanlar' yeniden seçildi.");
                return; // Başarılı olursa döngüden çık
            } catch (Exception e) {
                System.err.println("Sıralama işlemi yeniden deneniyor... (" + (i + 1) + "/3)");
            }
        }
        throw new RuntimeException("Sıralama işlemi başarısız oldu.");
    }*/

}
