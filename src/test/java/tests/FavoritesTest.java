package tests;

import Annotations.TestInfo;
import Navigations.NavigationHelper;
import org.testng.annotations.Test;

public class FavoritesTest extends BaseTest {
    private NavigationHelper navigationHelper = new NavigationHelper();

    @Test
    @TestInfo(description = "Add product to favorites and verify", tags = {"Favorites", "Regression"})
    public void testAddToFavorites() {
        navigationHelper.addToFavoritesAndVerify("Kız Çocuk Mont");
    }
}
