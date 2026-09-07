package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By LOGO_LINK = By.cssSelector("[class*='AppHeader_header__logo'] a");
    private static final By CONSTRUCTOR_LINK = By.xpath("//nav//a[.//p[text()='Конструктор']]");
    private static final By PERSONAL_ACCOUNT_LINK = By.xpath("//nav//a[.//p[text()='Личный Кабинет']]");
    private static final By BUNS_TAB = By.xpath("//div[span[text()='Булки']]");
    private static final By SAUCES_TAB = By.xpath("//div[span[text()='Соусы']]");
    private static final By FILLINGS_TAB = By.xpath("//div[span[text()='Начинки']]");
    private static final By ACTIVE_TAB = By.cssSelector("[class*='tab_tab_type_current']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу входа по кнопке «Войти в аккаунт» на главной")
    public LoginPage clickLoginButton() {
        waitClickable(LOGIN_BUTTON).click();
        return new LoginPage(driver);
    }

    @Step("Перейти в личный кабинет по ссылке в шапке")
    public void clickPersonalAccountLink() {
        String previousUrl = driver.getCurrentUrl();
        waitClickable(PERSONAL_ACCOUNT_LINK).click();
        waitUrlChanges(previousUrl);
    }

    @Step("Кликнуть на раздел «Конструктор» в шапке")
    public void clickConstructorLink() {
        waitClickable(CONSTRUCTOR_LINK).click();
    }

    @Step("Кликнуть на логотип Stellar Burgers")
    public void clickLogo() {
        waitClickable(LOGO_LINK).click();
    }

    @Step("Открыть вкладку «Булки»")
    public void openBunsTab() {
        waitClickable(BUNS_TAB).click();
    }

    @Step("Открыть вкладку «Соусы»")
    public void openSaucesTab() {
        waitClickable(SAUCES_TAB).click();
    }

    @Step("Открыть вкладку «Начинки»")
    public void openFillingsTab() {
        waitClickable(FILLINGS_TAB).click();
    }

    @Step("Получить название активной вкладки конструктора")
    public String getActiveTabName() {
        return waitVisible(ACTIVE_TAB).getText();
    }

    @Step("Проверить, что кнопка «Войти в аккаунт» отображается")
    public boolean isLoginButtonVisible() {
        return isDisplayed(LOGIN_BUTTON);
    }

    @Step("Проверить, что открыта страница конструктора")
    public boolean isBunsTabVisible() {
        return isDisplayed(BUNS_TAB);
    }
}
