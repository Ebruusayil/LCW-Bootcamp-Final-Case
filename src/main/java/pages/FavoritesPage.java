package pages;

import base.BasePage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FavoritesPage extends BasePage {
    private By favoriteProduct = By.xpath("//*[@id=\"Cart_addFavorite_1767109658\"]");

    public FavoritesPage(WebDriver driver) {
        super(driver);
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
}
