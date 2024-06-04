package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosColocacionHeladera;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

public class HeladeraTest {
  ColocacionHeladeras donacion;
  Colaborador colaborador;

  @BeforeEach
  void test_init() {
    colaborador = new Colaborador();
    donacion = new ColocacionHeladeras(colaborador,new Heladera(LocalDate.of(2023, 1, 1)));
    colaborador.setHeladerasColocadas(new ArrayList<>());
    donacion.efectuar();
  }

  @Test
  @DisplayName("activa desde octubre del 23")
  void validarPuntosAcumuladosHeladera() {
    // este test va a empezar a fallar el mes que viene xd    jaja xd ya lo cambie LOL
    Assertions.assertEquals(17, donacion.getMesesActiva());
    Assertions.assertEquals(85, donacion.calculadorDePuntos.calcularPuntos(donacion));
  }
}
