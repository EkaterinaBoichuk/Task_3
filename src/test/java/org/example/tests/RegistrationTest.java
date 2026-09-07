package org.example.tests;

import org.example.api.User;
import org.example.api.UserApiClient;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends TestBase {

    private final UserApiClient userApiClient = new UserApiClient();
    private User testUser;

    @Before
    public void prepareTestUser() {
        testUser = User.random();
    }

    @After
    public void cleanUpTestUser() {
        String accessToken = userApiClient.login(testUser.getEmail(), testUser.getPassword());
        userApiClient.delete(accessToken);
    }

    @Test
    public void userCanRegisterWithValidData() {
        MainPage mainPage = openMainPage();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.register(testUser.getName(), testUser.getEmail(), testUser.getPassword());

        assertTrue("После успешной регистрации должна открыться страница входа",
                driver.getCurrentUrl().contains("/login"));
    }

    @Test
    public void registrationShowsErrorForShortPassword() {
        MainPage mainPage = openMainPage();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.enterName(testUser.getName());
        registerPage.enterEmail(testUser.getEmail());
        registerPage.enterPassword("12345");
        registerPage.blurPasswordField();

        assertTrue("Должна отображаться ошибка «Некорректный пароль»", registerPage.isPasswordErrorVisible());
    }
}
