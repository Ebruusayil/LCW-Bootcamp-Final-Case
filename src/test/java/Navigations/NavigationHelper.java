package Navigations;

import base.Driver;
import pages.CartPage;
import pages.FavoritesPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class NavigationHelper {
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private FavoritesPage favoritesPage;

    public NavigationHelper() {
        this.loginPage = new LoginPage(Driver.getDriver());
        this.homePage = new HomePage(Driver.getDriver());
        this.productPage = new ProductPage(Driver.getDriver());
        this.cartPage = new CartPage(Driver.getDriver());
        this.favoritesPage = new FavoritesPage(Driver.getDriver());
    }

    public void login(String email, String password) {
        loginPage.navigateTo("https://www.lcw.com/");
        loginPage.login(email, password);
    }

    public void navigateToCategory() {
        HomePage homePage = new HomePage(Driver.getDriver());
        homePage.navigateToChildrenCategory();
        homePage.navigateToGirlChildrenCategory();
        homePage.scrollToSizeFilterHeader();
    }

    public void filterAndSelectProduct() {
        ProductPage productPage = new ProductPage(Driver.getDriver());
        productPage.applyFilters();
       // productPage.sortByMostSold();//   productPage.sortProducts();
        //productPage.selectFirstProduct();
    }



    }
