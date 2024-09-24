package ar.edu.utn.frba.dds.models.domain.colaboraciones;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class PuntosViandasDistribuidasTest {
    Colaborador colaborador;
    RedistribucionViandas donacion_1, donacion_2;
    Heladera heladera_1, heladera_2;

    private ICalculadorPuntos calculadorPuntos;

    @BeforeEach
    void test_init() {
        colaborador = new Colaborador();
        donacion_1 = new RedistribucionViandas(colaborador, LocalDate.of(2023, 2, 4), heladera_1, heladera_2, new MotivoRedistribucionVianda("Llegaron viandas nuevas"), 10);
        donacion_2 = new RedistribucionViandas(colaborador, LocalDate.of(2023, 4, 3), heladera_1, heladera_2, new MotivoRedistribucionVianda("Llegaron viandas nuevas"), 5);
        calculadorPuntos = new CalculadorPuntos();
    }

    @Test
    @DisplayName("redistribui 15 viandas")
    void validarPuntosAcumuladosPersonaVulnerable() {
        calculadorPuntos.sumarPuntosPara(colaborador, donacion_1, donacion_2);
        Assertions.assertEquals(15, colaborador.getPuntosGanados());
    }
}
