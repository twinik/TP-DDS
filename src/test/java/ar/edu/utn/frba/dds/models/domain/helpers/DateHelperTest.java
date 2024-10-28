package ar.edu.utn.frba.dds.models.domain.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class DateHelperTest {

  @Test
  void pruebaFechaFromString() {
    LocalDate f1 = LocalDate.of(2018, 12, 9);
    LocalDate f2 = DateHelper.fechaFromString("09/12/2018", "dd/MM/yyyy");
    assertEquals(f1, f2);
  }

}