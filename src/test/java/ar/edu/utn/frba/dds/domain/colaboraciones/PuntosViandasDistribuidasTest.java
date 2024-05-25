package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosAltaPersona;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PuntosViandasDistribuidasTest {
    Colaborador colaborador;
    RedistribucionViandas donacion_1, donacion_2;
    Heladera heladera_1,heladera_2;
    CalculadorDePuntos calculador;

    @BeforeEach
    void test_init(){
        colaborador = new Colaborador();
        donacion_1 = new RedistribucionViandas(colaborador, heladera_1, heladera_2, LocalDate.of(2023, 2 ,4),new MotivoRedistribucionVianda("Llegaron viandas nuevas"),10);
        donacion_2 = new RedistribucionViandas(colaborador, heladera_1, heladera_2, LocalDate.of(2023, 4 ,3),new MotivoRedistribucionVianda("Llegaron viandas nuevas"),5);
        calculador = new CalculadorPuntosAltaPersona();
        donacion_1.setCalculadorDePuntos(calculador);
        donacion_2.setCalculadorDePuntos(calculador);
    }

    @Test
    @DisplayName("Se repartieron 2 tarjetas y se obtuvieron 4 puntos")
    void validarPuntosAcumuladosPersonaVulnerable() {
        donacion_1.efectuar();
        donacion_2.efectuar();
        Assertions.assertEquals(15, colaborador.getPuntosGanados());
    }
}
