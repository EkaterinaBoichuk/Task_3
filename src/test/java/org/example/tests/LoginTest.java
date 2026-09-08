package org.example.tests;

import org.example.api.User;
import org.example.api.UserApiClient;
import org.example.pages.ForgotPasswordPage;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class LoginTest extends TestBase {

    private final UserApiClient userApiClient = new UserApiClient();
    private User testUser;
    private String accessToken;

    @Before
    public void createTestUser() {
        testUser = User.random();
        accessToken = userApiClient.register(testUser);
    }

    @After
    public void deleteTestUser() {
        userApiClient.delete(accessToken);
    }

    @Test
    public void userCanLoginFromMainPageButton() {
        MainPage mainPage = openMainPage();
        mainPage.clickLoginButton();
        new LoginPage(driver).login(testUser.getEmail(), testUser.getPassword());

        assertFalse("После входа кнопка «Войти в аккаунт» не должна отображаться",
                new MainPage(driver).isLoginButtonVisible());
    }

    @Test
    public void userCanLoginFromPersonalAccountLink() {
        MainPage mainPage = openMainPage();
        mainPage.clickPersonalAccountLink();
        new LoginPage(driver).login(testUser.getEmail(), testUser.getPassword());

        assertFalse("После входа кнопка «Войти в аккаунт» не должна отображаться",
                new MainPage(driver).isLoginButtonVisible());
    }

    @Test
    public void userCanLoginFromRegisterForm() {
        MainPage mainPage = openMainPage();
        mainPage.clickLoginButton();
        RegisterPage registerPage = new LoginPage(driver).clickRegisterLink();
        LoginPage loginPage = registerPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertFalse("После входа кнопка «Войти в аккаунт» не должна отображаться",
                new MainPage(driver).isLoginButtonVisible());
    }

    @Test
    public void userCanLoginFromForgotPasswordForm() {
        MainPage mainPage = openMainPage();
        mainPage.clickLoginButton();
        ForgotPasswordPage forgotPasswordPage = new LoginPage(driver).clickForgotPasswordLink();
        LoginPage loginPage = forgotPasswordPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertFalse("После входа кнопка «Войти в аккаунт» не должна отображаться",
                new MainPage(driver).isLoginButtonVisible());
    }
}
