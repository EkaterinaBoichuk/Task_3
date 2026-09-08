package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.name("name");
    private static final By PASSWORD_INPUT = By.name("Пароль");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти']");
    private static final By REGISTER_LINK = By.xpath("//a[text()='Зарегистрироваться']");
    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email {email} на странице входа")
    public LoginPage enterEmail(String email) {
        waitVisible(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль на странице входа")
    public LoginPage enterPassword(String password) {
        waitVisible(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку «Войти»")
    public void clickLoginButton() {
        String previousUrl = driver.getCurrentUrl();
        waitClickable(LOGIN_BUTTON).click();
        waitUrlChanges(previousUrl);
    }

    @Step("Войти в аккаунт с email {email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Перейти на страницу регистрации по ссылке «Зарегистрироваться»")
    public RegisterPage clickRegisterLink() {
        waitClickable(REGISTER_LINK).click();
        return new RegisterPage(driver);
    }

    @Step("Перейти на страницу восстановления пароля")
    public ForgotPasswordPage clickForgotPasswordLink() {
        waitClickable(FORGOT_PASSWORD_LINK).click();
        return new ForgotPasswordPage(driver);
    }
}
