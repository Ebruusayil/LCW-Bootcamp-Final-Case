package Navigations;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.FavoritesPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class NavigationHelper {
    private final LoginPage loginPage;
    private final HomePage homePage;
    private final ProductPage productPage;
    @SuppressWarnings("unused")
    private final CartPage cartPage;
    @SuppressWarnings("unused")
    private final FavoritesPage favoritesPage;

    public NavigationHelper(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
        this.favoritesPage = new FavoritesPage(driver);
    }

    public void login(String email, String password) {
        loginPage.navigateTo("https://www.lcw.com/");
        loginPage.login(email, password);
    }

    public void navigateToCategory() {
        homePage.navigateToChildrenCategory();
        homePage.navigateToGirlChildrenCategory();
        homePage.scrollToSizeFilterHeader();
    }

    public void filterAndSelectProduct() {
        productPage.applyFilters();
    }
}
