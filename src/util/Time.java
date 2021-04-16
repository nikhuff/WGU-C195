package util;

import database.DBAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Time {

    private static ZoneId zoneId;
    private static ZoneId estZoneId;
    private static ZoneId utcZoneId;
    private static LocalTime open;
    private static LocalTime close;

    public static ZoneId getZoneId() {
        return zoneId;
    }

    public static void init() {
        zoneId = ZoneId.systemDefault();
        estZoneId = ZoneId.of("US/Eastern");
        utcZoneId = ZoneId.of("UTC");
        ZonedDateTime openEst = getZonedDateTime(2021, 4, 12, 8, 0, estZoneId);
        ZonedDateTime closeEst = getZonedDateTime(2021, 4, 12, 22, 0, estZoneId);
        open = convertToSystemTime(openEst).toLocalTime();
        close = convertToSystemTime(closeEst).toLocalTime();
    }

    private static String getString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        return time.format(formatter);
    }

    public static ZonedDateTime parseString(String dateTimeString) {
        String[] array = dateTimeString.split(" ");
        String[] date = array[0].split("-");
        String[] time = array[1].split(":");

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        ZonedDateTime dateTimeUTC = getZonedDateTime(year, month, day, hour, minute, ZoneId.of("UTC"));
        return convertToSystemTime(dateTimeUTC);
    }

    public static String convertToDBString(ZonedDateTime zonedDateTime) {
        ZonedDateTime utc = convertToUTC(zonedDateTime);
        return utc.toLocalDateTime().toString().replace("T", " ");
    }

    public static int getOpen() {
        return Integer.parseInt(getString(open));
    }

    public static int getMinutes(String string) {
        return Integer.parseInt(string.replace(":",""));
    }

    public static int getClose() {
        return Integer.parseInt(getString(close));
    }

    public static String getMinuteString(LocalTime time) {
        return ":" + time.getMinute();
    }

    public static ZonedDateTime convertToUTC(ZonedDateTime time) {
        return ZonedDateTime.ofInstant(time.toInstant(), utcZoneId);
    }

    public static ZonedDateTime convertToSystemTime(ZonedDateTime time) {
        return ZonedDateTime.ofInstant(time.toInstant(), zoneId);
    }

    public static ZonedDateTime getZonedDateTime(LocalDate localDate, LocalTime localTime, ZoneId zoneId) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return ZonedDateTime.of(localDateTime, zoneId);
    }

    public static ZonedDateTime getEndDateTime(LocalDate date, LocalTime start, LocalTime end, ZoneId zoneId) {
        if (start.isAfter(end)) {
            date.plusDays(1);
        }
        return getZonedDateTime(date, end, zoneId);
    }

    public static ZonedDateTime getZonedDateTime(int year, int month, int day, int hour, int minutes, ZoneId zoneId) {
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalTime localTime = LocalTime.of(hour, minutes);
        return getZonedDateTime(localDate, localTime, zoneId);
    }

    public static ObservableList<Appointment> filterWeek(ObservableList<Appointment> appointments) {

        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

        ZonedDateTime now = ZonedDateTime.now(zoneId);
        LocalDate firstDayOfThisWeek = now.toLocalDate().with(DayOfWeek.MONDAY);
        LocalDate firstDayOfNextWeek = firstDayOfThisWeek.plusWeeks(1);
        ZonedDateTime thisWeekStart = firstDayOfThisWeek.atStartOfDay(zoneId);
        ZonedDateTime nextWeekStart = firstDayOfNextWeek.atStartOfDay(zoneId);

        for (Appointment appointment : appointments) {
            ZonedDateTime start = appointment.getStart();
            if (start.isAfter(thisWeekStart) && start.isBefore(nextWeekStart)) {
                filteredAppointments.add(appointment);
            }
        }

        return filteredAppointments;
    }

    public static ObservableList<Appointment> filterMonth(ObservableList<Appointment> appointments) {

        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

        ZonedDateTime now = ZonedDateTime.now(zoneId);
        LocalDate firstDayOfThisMonth = now.toLocalDate().withDayOfMonth(1);
        LocalDate firstDayOfNextMonth = firstDayOfThisMonth.plusMonths(1);
        ZonedDateTime thisMonthStart = firstDayOfThisMonth.atStartOfDay(zoneId);
        ZonedDateTime nextMonthStart = firstDayOfNextMonth.atStartOfDay(zoneId);

        for (Appointment appointment : appointments) {
            ZonedDateTime start = appointment.getStart();
            if (start.isAfter(thisMonthStart) && start.isBefore(nextMonthStart)) {
                filteredAppointments.add(appointment);
            }
        }

        return filteredAppointments;
    }

    public static Appointment appointmentSoon() {

        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime fifteen = now.plusMinutes(15);
        ObservableList<Appointment> appointments = DBAppointment.getAppointments();

        for (Appointment appointment : appointments) {
            if (appointment.getStart().isAfter(now) && appointment.getStart().isBefore(fifteen)) {
                return appointment;
            }
        }
        return null;
    }
}
