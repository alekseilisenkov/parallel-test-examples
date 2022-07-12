package com.alexlis.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexMainPage {

    private SelenideElement searchInput = $(".search3__input");
    private SelenideElement searchButton = $("button[type='submit']");

    public YandexResultsPage doSearch(String searchQuery) {
        searchInput.setValue(searchQuery);
        searchButton.click();
        return new YandexResultsPage();
    }

}