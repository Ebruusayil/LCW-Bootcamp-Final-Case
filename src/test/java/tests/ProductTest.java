package tests;

import Annotations.TestInfo;
import Navigations.NavigationHelper;
import base.BasePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    private NavigationHelper navigationHelper;
    private BasePage basePage;

    @BeforeClass
    public void initPages() {
        navigationHelper = new NavigationHelper(driver);
        basePage = new BasePage(driver);
    }

    @Test
    @TestInfo(description = "Filter and select product", tags = {"Product", "Regression"})
    public void testFilterAndSelectProduct() {
        basePage.closeCookieBanner();
        navigationHelper.navigateToCategory();
        navigationHelper.filterAndSelectProduct();
    }
}
