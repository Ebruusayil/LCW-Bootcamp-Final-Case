package tests;

import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = Driver.getDriver();
        driver.get("https://www.lcw.com/"); // Ana sayfaya yönlendir
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}
