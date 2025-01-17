package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FavoritesPage extends BasePage {
    private By favoriteProduct = By.xpath("//div[@class='favorite-item']");

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if product '{productName}' is in favorites")
    public boolean isProductInFavorites(String productName) {
        String actualProductName = driver.findElement(favoriteProduct).getText();
        return actualProductName.equals(productName);
    }
}
