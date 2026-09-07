package org.example.tests;

import org.example.api.User;
import org.example.api.UserApiClient;
import org.example.pages.AccountPage;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LogoutTest extends TestBase {

    private final UserApiClient userApiClient = new UserApiClient();
    private User testUser;
    private String accessToken;

    @Before
    public void createAndLoginTestUser() {
        testUser = User.random();
        accessToken = userApiClient.register(testUser);

        MainPage mainPage = openMainPage();
        mainPage.clickLoginButton();
        new LoginPage(driver).login(testUser.getEmail(), testUser.getPassword());
        new MainPage(driver).clickPersonalAccountLink();
    }

    @After
    public void deleteTestUser() {
        userApiClient.delete(accessToken);
    }

    @Test
    public void userCanLogoutFromPersonalAccount() {
        new AccountPage(driver).clickLogout();

        assertTrue("После выхода должна открыться страница входа",
                driver.getCurrentUrl().contains("/login"));
        assertTrue("После выхода на главной должна отображаться кнопка «Войти в аккаунт»",
                openMainPage().isLoginButtonVisible());
    }
}
