package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By productName = By.xpath("//div[@class='cart-item-name']");
    private By productColor = By.xpath("//div[@class='cart-item-color']");
    private By productQuantity = By.xpath("//input[@class='cart-item-quantity']");
    private By totalAmount = By.xpath("//div[@class='total-price']");
    private By proceedToCheckoutButton = By.id("proceedToCheckout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify product details: Name={expectedName}, Color={expectedColor}, Quantity={expectedQuantity}")
    public boolean verifyProductDetails(String expectedName, String expectedColor, String expectedQuantity) {
        String actualName = driver.findElement(productName).getText();
        String actualColor = driver.findElement(productColor).getText();
        String actualQuantity = driver.findElement(productQuantity).getAttribute("value");

        return actualName.equals(expectedName) && actualColor.equals(expectedColor) && actualQuantity.equals(expectedQuantity);
    }

    @Step("Verify total amount: {expectedAmount}")
    public boolean verifyTotalAmount(String expectedAmount) {
        String actualAmount = driver.findElement(totalAmount).getText();
        return actualAmount.equals(expectedAmount);
    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }
}
