package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.CardDelivery.countDateDelivery;

public class CardDeliveryTest {
    @BeforeEach
    public void shouldOpenPage() {
    open("http://localhost:9999");
    Configuration.holdBrowserOpen = true;}
    @Test
    public void shouldOrderDeliveryCard() {
        CardDelivery delivery = new CardDelivery();
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $x("//span[@data-test-id='date']//input").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        int daysBeforeDelivery = 5;
        $x("//span[@data-test-id='date']//input").setValue(CardDelivery.countDateDelivery(daysBeforeDelivery));
        $(byName("name")).setValue("Петров-Водкин Иван");
        $("[data-test-id=phone] input").setValue("+79993332211");
        $("[data-test-id=agreement]").click();
        $$("button").findBy(exactText("Забронировать")).click();
        $("[data-test-id=\"notification\"]").shouldHave(text("Успешно!"), Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + CardDelivery.countDateDelivery(daysBeforeDelivery))).shouldBe(visible);
    }
}
