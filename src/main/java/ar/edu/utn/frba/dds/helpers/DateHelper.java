package ar.edu.utn.frba.dds.helpers;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

/**
 * DateHelper class permite realizar operaciones con fechas.
 */
public class DateHelper {

    /**
     * Constructor.
     */
    public static int mesesEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("fecha inicio mas tardia que la fecha final");
        }

        Period periodo = Period.between(fechaInicio, fechaFin);
        int anios = periodo.getYears();
        int meses = periodo.getMonths();

        return anios * 12 + meses;
    }

    /**
     * fechaFromString method permite convertir un string a una fecha.
     */
    public static LocalDate fechaFromString(String fecha, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(fecha, formatter);
        return date;
    }

    public static int horasEntre(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Duration duracion = Duration.between(fechaInicio, fechaFin);
        return (int) duracion.toHours();
    }

    public static int minutosEntre(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Duration duracion = Duration.between(fechaInicio, fechaFin);
        return (int) duracion.toMinutes();
    }

    public static boolean esLaMismaSemana(LocalDate fecha1, LocalDate fecha2) {
        Period periodo = Period.between(fecha1, fecha2);
        return periodo.getDays() <= 6;
    }

    public static boolean esLaMismaSemana(LocalDateTime fecha1, LocalDateTime fecha2) {
        Period periodo = Period.between(fecha1.toLocalDate(), fecha2.toLocalDate());
        return periodo.getDays() <= 6;
    }

    public static LocalDateTime localDateTimeFromTimestamp(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
    }

    public static LocalDate principioDeSemana(LocalDate fecha) {
        return fecha.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
    }

    public static LocalDateTime principioDeSemana(LocalDateTime fecha) {
        return fecha.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                .with(LocalTime.MIN);
    }

    public static LocalDate finDeSemana(LocalDate fecha) {
        return fecha.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
    }

    public static LocalDateTime finDeSemana(LocalDateTime fecha) {
        return fecha.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .with(LocalTime.MAX);
    }

}
