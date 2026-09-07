package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private static final By NAME_INPUT = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By EMAIL_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");
    private static final By PASSWORD_ERROR = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя {name} на странице регистрации")
    public RegisterPage enterName(String name) {
        waitVisible(NAME_INPUT).sendKeys(name);
        return this;
    }

    @Step("Ввести email {email} на странице регистрации")
    public RegisterPage enterEmail(String email) {
        waitVisible(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль на странице регистрации")
    public RegisterPage enterPassword(String password) {
        waitVisible(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    @Step("Убрать фокус с поля пароля")
    public RegisterPage blurPasswordField() {
        waitVisible(NAME_INPUT).click();
        return this;
    }

    @Step("Нажать кнопку «Зарегистрироваться»")
    public void clickRegisterButton() {
        waitClickable(REGISTER_BUTTON).click();
        waitUrlContains("/login");
    }

    @Step("Зарегистрировать нового пользователя {name} / {email}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Перейти на страницу входа по ссылке «Войти»")
    public LoginPage clickLoginLink() {
        waitClickable(LOGIN_LINK).click();
        return new LoginPage(driver);
    }

    @Step("Проверить, что отображается ошибка «Некорректный пароль»")
    public boolean isPasswordErrorVisible() {
        return isDisplayed(PASSWORD_ERROR);
    }
}
