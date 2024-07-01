package ru.nikita.apicourse.core.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtils {

    public static long toUTC(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
    }
}
