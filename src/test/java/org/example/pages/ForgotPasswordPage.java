package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private static final By EMAIL_INPUT = By.name("name");
    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email {email} на странице восстановления пароля")
    public ForgotPasswordPage enterEmail(String email) {
        waitVisible(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    @Step("Перейти на страницу входа по ссылке «Войти» из формы восстановления пароля")
    public LoginPage clickLoginLink() {
        waitClickable(LOGIN_LINK).click();
        return new LoginPage(driver);
    }
}
