package org.example.tests;

import org.example.pages.MainPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorTabsTest extends TestBase {

    @Test
    public void bunsTabIsActiveByDefault() {
        MainPage mainPage = openMainPage();

        assertEquals("По умолчанию должна быть активна вкладка «Булки»",
                "Булки", mainPage.getActiveTabName());
    }

    @Test
    public void userCanSwitchToSaucesTab() {
        MainPage mainPage = openMainPage();

        mainPage.openSaucesTab();

        assertEquals("Должна открыться вкладка «Соусы»", "Соусы", mainPage.getActiveTabName());
    }

    @Test
    public void userCanSwitchToFillingsTab() {
        MainPage mainPage = openMainPage();

        mainPage.openFillingsTab();

        assertEquals("Должна открыться вкладка «Начинки»", "Начинки", mainPage.getActiveTabName());
    }
}
