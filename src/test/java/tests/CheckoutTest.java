package tests;

import Annotations.TestInfo;
import Navigations.NavigationHelper;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    private NavigationHelper navigationHelper = new NavigationHelper();

    @Test
    @TestInfo(description = "Complete checkout process", tags = {"Checkout", "EndToEnd"})
    public void testCompleteCheckout() {
        navigationHelper.login("test@example.com", "password123");
        navigationHelper.navigateToCategory();
        navigationHelper.filterAndSelectProduct();
        navigationHelper.verifyAndCheckout("KIz Çocuk Mont", "Bej", "1", "1.499,99 TL");
    }
}
