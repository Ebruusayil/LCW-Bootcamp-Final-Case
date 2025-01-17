package tests;

import Annotations.TestInfo;

import Navigations.NavigationHelper;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
     final NavigationHelper navigationHelper = new NavigationHelper();

    @Test
    @TestInfo(description = "Verify cart and proceed to checkout", tags = {"Cart", "Regression"})
    public void testVerifyAndCheckout() {
        navigationHelper.verifyAndCheckout("Kız Çocuk Mont", "Bej", "1", "1.499,99 TL");
    }
}
