package ar.edu.utn.frba.dds.domain.helpers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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

  public static int horasEntre(LocalDateTime fechaInicio, LocalDateTime fechaFin){
    Duration duracion = Duration.between(fechaInicio,fechaFin);
    return (int) duracion.toHours();
  }

  public static boolean esLaMismaSemana(LocalDate fecha1, LocalDate fecha2){
    Period periodo = Period.between(fecha1,fecha2);
    return periodo.getDays() <= 6;
  }
}
