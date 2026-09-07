package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private static final By CONSTRUCTOR_LINK = By.xpath("//nav//a[.//p[text()='Конструктор']]");
    private static final By LOGO_LINK = By.cssSelector("[class*='AppHeader_header__logo'] a");
    private static final By PROFILE_LINK = By.xpath("//a[text()='Профиль']");
    private static final By ORDER_HISTORY_LINK = By.xpath("//a[text()='История заказов']");
    private static final By LOGOUT_BUTTON = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что открыт личный кабинет")
    public boolean isProfileLinkActive() {
        return isDisplayed(PROFILE_LINK);
    }

    @Step("Проверить, что ссылка «История заказов» отображается")
    public boolean isOrderHistoryLinkVisible() {
        return isDisplayed(ORDER_HISTORY_LINK);
    }

    @Step("Кликнуть на раздел «Конструктор» из личного кабинета")
    public void clickConstructorLink() {
        String previousUrl = driver.getCurrentUrl();
        waitClickable(CONSTRUCTOR_LINK).click();
        waitUrlChanges(previousUrl);
    }

    @Step("Кликнуть на логотип Stellar Burgers из личного кабинета")
    public void clickLogo() {
        String previousUrl = driver.getCurrentUrl();
        waitClickable(LOGO_LINK).click();
        waitUrlChanges(previousUrl);
    }

    @Step("Нажать кнопку «Выход»")
    public void clickLogout() {
        waitClickable(LOGOUT_BUTTON).click();
        waitUrlContains("/login");
    }
}
