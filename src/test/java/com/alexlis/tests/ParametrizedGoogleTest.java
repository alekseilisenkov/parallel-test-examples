package com.alexlis.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedGoogleTest {

    @ValueSource(strings = {
            "selenide",
            "selenium",
            "junit"
    })

    @ParameterizedTest(name = "Search by text: {0}")
    void searchValueSourceTest(String i) {
        open("https://www.google.ru/");
        $(".gLFyf").setValue(i).pressEnter();
        $("#search").shouldHave(text(i));
    }
}