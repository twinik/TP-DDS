package ar.edu.utn.frba.dds.models.domain.colaboraciones;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PuntosDonacionDineroTest {
  Colaborador colaborador;
  DonacionDinero donacion;

  @BeforeEach
  void test_init() {
    colaborador = new Colaborador();
    donacion = new DonacionDinero();
    donacion.setFrecuencia(FrecuenciaDonacion.MENSUAL);
    donacion.setMonto(2000F);
  }

  @Test
  @DisplayName("Se dono 2000 pesos y se obtuvieron 1000 puntos")
  void validarPuntosAcumuladosDonacionDinero() {
    Assertions.assertEquals(1000F, donacion.calcularPuntaje());
  }
}
