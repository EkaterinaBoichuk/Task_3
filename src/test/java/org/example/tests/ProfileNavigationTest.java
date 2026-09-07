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

public class ProfileNavigationTest extends TestBase {

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
    }

    @After
    public void deleteTestUser() {
        userApiClient.delete(accessToken);
    }

    @Test
    public void userCanOpenPersonalAccountFromHeader() {
        new MainPage(driver).clickPersonalAccountLink();

        assertTrue("Должна открыться страница личного кабинета",
                driver.getCurrentUrl().contains("/account"));
        assertTrue("В личном кабинете должна быть активна ссылка «Профиль»",
                new AccountPage(driver).isProfileLinkActive());
    }

    @Test
    public void userCanReturnToConstructorViaConstructorLink() {
        new MainPage(driver).clickPersonalAccountLink();
        AccountPage accountPage = new AccountPage(driver);

        accountPage.clickConstructorLink();

        assertTrue("После клика на «Конструктор» должна открыться страница конструктора",
                new MainPage(driver).isBunsTabVisible());
    }

    @Test
    public void userCanReturnToConstructorViaLogo() {
        new MainPage(driver).clickPersonalAccountLink();
        AccountPage accountPage = new AccountPage(driver);

        accountPage.clickLogo();

        assertTrue("После клика на логотип должна открыться страница конструктора",
                new MainPage(driver).isBunsTabVisible());
    }
}
