package ru.netology;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CardDelivery {
    public static String countDateDelivery(int daysBeforeDelivery) {
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime date = ZonedDateTime.now(zoneId).plusDays(daysBeforeDelivery);
        String correctDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return correctDate;
    }
}
