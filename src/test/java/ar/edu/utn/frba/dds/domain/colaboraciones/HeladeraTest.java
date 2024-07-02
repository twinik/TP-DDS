package ar.edu.utn.frba.dds.domain.colaboraciones;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class HeladeraTest {
  ColocacionHeladeras donacion;
  Colaborador colaborador;

  @BeforeEach
  void test_init() {
    colaborador = new Colaborador();
    donacion = new ColocacionHeladeras(colaborador,new Heladera(LocalDate.of(2023, 10, 1)));
    colaborador.setHeladerasColocadas(new ArrayList<>());
    donacion.efectuar();
  }


@Test
@DisplayName("activa desde octubre del 23")
void validarPuntosAcumuladosHeladera() {
    LocalDate fechaActivacion = LocalDate.of(2023, 10, 1); // Fecha de activación de la heladera
    LocalDate fechaActual = LocalDate.now();
    long mesesActiva = ChronoUnit.MONTHS.between(fechaActivacion, fechaActual);

    // Asumiendo que cada mes activo otorga 5 puntos, por ejemplo
    long puntosEsperados = mesesActiva * 5;

    // Ensure both arguments are of type long
    Assertions.assertEquals(mesesActiva, donacion.getMesesActiva().longValue()); // Explicitly unbox Long to long
    Assertions.assertEquals(puntosEsperados, donacion.calculadorDePuntos.calcularPuntos(donacion));
}
}
