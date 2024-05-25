package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

public class PuntosDonacionViandaTest {
    Colaborador colaborador;
    Heladera heladera;
    DonacionVianda donacion_1, donacion_2;
    CalculadorDePuntos calculador;
    Vianda vianda_1, vianda_2;

    @BeforeEach
    void test_init(){
        colaborador = new Colaborador();
        vianda_1 = new Vianda("Pollo",  new GregorianCalendar(1990, Calendar.AUGUST, 4).getTime(),LocalDate.of(2024, 4, 5),colaborador, heladera,300, (int) 0.5, true);
        vianda_2 = new Vianda("Carne",  new GregorianCalendar(1991, Calendar.AUGUST, 4).getTime(),LocalDate.of(2024, 5, 5),colaborador, heladera,300, (int) 0.4, true);
        donacion_1 = new DonacionVianda(vianda_1,colaborador);
        donacion_2 = new DonacionVianda(vianda_2,colaborador);
    }

    @Test
    @DisplayName("Se donaron 2 viandas y se obtuvieron 3 puntos")
    void validarPuntosAcumuladosDonacionVianda() {
        donacion_1.efectuar(); //Tira que "this.colaborador" da null pero en el test de "PuntosAltaPersonaVulnerableTest" tengo lo mismo que aca y no me tira el error, no se que onda
        donacion_2.efectuar();
        Assertions.assertEquals(3F, colaborador.getPuntosGanados());
    }
}
