package tests;

import Annotations.TestInfo;
import Navigations.NavigationHelper;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    private NavigationHelper navigationHelper = new NavigationHelper();
    public static WebDriver driver;


    private BasePage basePage = new BasePage(driver);
    @Test
    @TestInfo(description = "Filter and select product", tags = {"Product", "Regression"})
    public void testFilterAndSelectProduct() {
        basePage.closeCookieBanner();
        navigationHelper.navigateToCategory();
        navigationHelper.filterAndSelectProduct();

    }
    }

