package ar.edu.utn.frba.dds.domain.helpers.colaboraciones;

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
  CalculadorDePuntos calculador;

  Colaborador colaborador;
  @BeforeEach
  void test_init(){
    colaborador = new Colaborador();
    donacion = new ColocacionHeladeras(new Heladera(LocalDate.of(2023,1,1)));
    colaborador.setHeladerasColocadas(new ArrayList<>());
    colaborador.agregarColocacionHeladera(donacion);
    calculador = new CalculadorPuntosColocacionHeladera();
  }


  @Test
  @DisplayName("activa desde octubre del 23")
  void validarPuntosAcumuladosHeladera() {
    Assertions.assertEquals(16,donacion.getMesesActiva());
    Assertions.assertEquals(80,calculador.calcularPuntos(donacion));
  }


}
