package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class BrowserFactory {

    private BrowserFactory() {
    }

    public static WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            case "yandex":
                return createYandexDriver();
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver createYandexDriver() {
        String yandexBinaryPath = Config.get("yandex.browser.path");
        String chromiumMajorVersion = Config.get("yandex.chromium.version");
        WebDriverManager.chromedriver().browserVersion(chromiumMajorVersion).setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexBinaryPath);
        return new ChromeDriver(options);
    }
}
