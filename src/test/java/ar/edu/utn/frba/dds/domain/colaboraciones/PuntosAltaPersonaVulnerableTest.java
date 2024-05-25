package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosAltaPersona;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PuntosAltaPersonaVulnerableTest {
    AltaPersonaVulnerable donacion_1, donacion_2;
    CalculadorDePuntos calculador;
    Colaborador colaborador;
    PersonaVulnerable personaVulnerable_1, personaVulnerable_2;
    Tarjeta tarjeta_1, tarjeta_2;

    @BeforeEach
    void test_init(){
        colaborador = new Colaborador();
        personaVulnerable_1 = new PersonaVulnerable("Ernesto", new GregorianCalendar(1990, Calendar.AUGUST, 4).getTime(),  new GregorianCalendar(2000, Calendar.AUGUST, 4).getTime(), true, "Caballito", TipoDocumento.DNI, "45419968", colaborador, null);
        personaVulnerable_2 = new PersonaVulnerable("Jaime", new GregorianCalendar(1995, Calendar.AUGUST, 8).getTime(),  new GregorianCalendar(2005, Calendar.AUGUST, 2).getTime(), true, "Monte Grande", TipoDocumento.DNI, "45419967", colaborador, null);
        donacion_1 = new AltaPersonaVulnerable(colaborador, personaVulnerable_1, tarjeta_1, LocalDate.of(2023, 1, 1));
        donacion_2 = new AltaPersonaVulnerable(colaborador, personaVulnerable_2, tarjeta_2, LocalDate.of(2024, 4, 5));
        calculador = new CalculadorPuntosAltaPersona();
        donacion_1.setCalculadorDePuntos(calculador);
        donacion_2.setCalculadorDePuntos(calculador);
    }

    @Test
    @DisplayName("Se repartieron 2 tarjetas y se obtuvieron 4 puntos")
    void validarPuntosAcumuladosPersonaVulnerable() {
        donacion_1.efectuar();
        donacion_2.efectuar();
        Assertions.assertEquals(4, colaborador.getPuntosGanados());
    }
}


