package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class CardDeliveryTest {
    @Test
    public void shouldOrderDeliveryCard() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $x("//*[contains(@placeholder, 'Дата встречи')]").sendKeys(Keys.CONTROL,Keys.BACK_SPACE);
        ZonedDateTime date = ZonedDateTime.now().plusDays(4);
        String correctDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        $x("//*[contains(@placeholder, 'Дата встречи')]").setValue(correctDate);
        $(byName("name")).setValue("Петров-Водкин Иван");
        $("[data-test-id=phone] input").setValue("+79993332211");
        $("[data-test-id=agreement]").click();
        $$("button").findBy(exactText("Забронировать")).click();
        $("[data-test-id=\"notification\"]").shouldHave(text("Успешно!"), Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на" + correctDate)).shouldBe(visible);

    }
    }