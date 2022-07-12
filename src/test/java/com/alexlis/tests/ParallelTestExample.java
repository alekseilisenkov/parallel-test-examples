package com.alexlis.tests;

import com.alexlis.page.YandexMainPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ParallelTestExample {

    private static YandexMainPage ymp = new YandexMainPage();

    @ValueSource(strings = {
            "selenide",
            "qameta",
            "allure"
    })

    @ParameterizedTest
    void yandexSearchPageObjectTest(String searchQuery, TestInfo testInfo) {
        Selenide.open("http://ya.ru");
        ymp.doSearch(searchQuery).checkResults(searchQuery);

        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
        ymp = null;
    }

    @ValueSource(strings = {
            "selenide",
            "qameta",
            "allure"
    })

    @ParameterizedTest
    void yandexSearchTest(String searchQuery, TestInfo testInfo) {
        Configuration.startMaximized = false;
        Selenide.open("http://ya.ru");
        $(".search3__input").setValue(searchQuery);
        $("button[type='submit']").click();
        $$(".serp-item").shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(1).shouldHave(text(searchQuery));

        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
    }

    @Test
    void minimizedWindowSearchTest(TestInfo testInfo) {
        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        $(".search3__input").setValue("Википедия");
        $("button[type='submit']").click();
        $$(".serp-item").shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(0).shouldHave(text("Википедия — свободная энциклопедия"));

        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
    }
}