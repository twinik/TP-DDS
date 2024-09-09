package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class PuntosDonacionViandaTest {
    Colaborador colaborador;
    Heladera heladera;
    DonacionVianda donacion_1, donacion_2;
    private ICalculadorPuntos calculadorPuntos;
    Vianda vianda_1, vianda_2;

    @BeforeEach
    void test_init() {
        colaborador = new Colaborador();
        vianda_1 = new Vianda("Pollo", LocalDate.of(2020, 4, 5), LocalDate.of(2024, 4, 5), colaborador, heladera, 300, 0.5f, true);
        vianda_2 = new Vianda("Carne", LocalDate.of(2021, 4, 5), LocalDate.of(2024, 5, 5), colaborador, heladera, 300, 0.4f, true);
        donacion_1 = new DonacionVianda(colaborador, vianda_1.getFechaDonacion(), vianda_1);
        donacion_2 = new DonacionVianda(colaborador, vianda_2.getFechaDonacion(), vianda_2);
        calculadorPuntos = new CalculadorPuntos();
    }

    @Test
    @DisplayName("Se donaron 2 viandas y se obtuvieron 3 puntos")
    void validarPuntosAcumuladosDonacionVianda() {
        calculadorPuntos.sumarPuntosPara(colaborador, donacion_1, donacion_2);
        Assertions.assertEquals(3F, colaborador.getPuntosGanados());
    }
}
