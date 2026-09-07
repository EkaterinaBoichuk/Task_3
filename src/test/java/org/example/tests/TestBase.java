package org.example.tests;

import org.example.config.Config;
import org.example.driver.BrowserFactory;
import org.example.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class TestBase {

    protected WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = BrowserFactory.getWebDriver(browser);
        driver.manage().window().maximize();
        driver.get(Config.get("base.url"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected MainPage openMainPage() {
        driver.get(Config.get("base.url"));
        return new MainPage(driver);
    }
}
