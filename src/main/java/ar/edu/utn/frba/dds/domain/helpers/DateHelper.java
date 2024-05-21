package ar.edu.utn.frba.dds.domain.helpers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateHelper {

  public static int mesesEntre(LocalDate fechaInicio, LocalDate fechaFin) {
    if (fechaInicio.isAfter(fechaFin)) {
      throw new IllegalArgumentException("fecha inicio mas tardia que la fecha final");
    }

    Period periodo = Period.between(fechaInicio, fechaFin);
    int anios = periodo.getYears();
    int meses = periodo.getMonths();

    return anios * 12 + meses;
  }

  public static LocalDate fechaFromString(String fecha, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    LocalDate date = LocalDate.parse(fecha, formatter);
    return date;
  }

}
