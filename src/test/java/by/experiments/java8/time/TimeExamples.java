package by.experiments.java8.time;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.Test;

/**
 * Simple examples of using java time API.
 * 
 * @author Tsimafei_Shchytkavets
 */
public class TimeExamples
{
    @Test
    public void currentTimeMillis()
    {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
    }

    @Test
    public void zones()
    {
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone = ZoneId.of("Europe/Berlin");
        System.out.println(zone.getRules());
    }

    @Test
    public void localTimeCreation()
    {
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(
                Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);
    }

    @Test
    public void localTimeManipulation()
    {
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1.isBefore(now2));

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);
        System.out.println(minutesBetween);
    }

    @Test
    public void localDateCreation()
    {
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(
                Locale.GERMAN);

        LocalDate formatted = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(formatted);
    }

    @Test
    public void localDateManipulation()
    {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(yesterday);

        LocalDate birthDay = LocalDate.of(1985, Month.JUNE, 28);
        DayOfWeek dayOfWeek = birthDay.getDayOfWeek();
        System.out.println(dayOfWeek);
    }

    @Test
    public void localDateTimeCreation()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);
    }

    @Test
    public void localDateTimeManipulation()
    {
        LocalDateTime birthDay = LocalDateTime.of(1985, Month.JUNE, 28, 23, 59, 59);

        DayOfWeek dayOfWeek = birthDay.getDayOfWeek();
        System.out.println(dayOfWeek);

        Month month = birthDay.getMonth();
        System.out.println(month);

        long minuteOfDay = birthDay.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);
    }
}
