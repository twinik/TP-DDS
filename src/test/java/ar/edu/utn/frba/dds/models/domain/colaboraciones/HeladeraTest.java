package ar.edu.utn.frba.dds.models.domain.colaboraciones;


import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HeladeraTest {
    ColocacionHeladeras donacion;
    Colaborador colaborador;

    private ICalculadorPuntos calculadorPuntos;

    @BeforeEach
    void test_init() {
        colaborador = new Colaborador();
        calculadorPuntos = new CalculadorPuntos();
        donacion = new ColocacionHeladeras(colaborador, LocalDate.of(2023, 10, 1), new Heladera(LocalDate.of(2023, 10, 1)));
        colaborador.setHeladerasColocadas(new ArrayList<>());
        calculadorPuntos.sumarPuntosPara(colaborador, donacion);
    }


    @Test
    @DisplayName("activa desde octubre del 23")
    void validarPuntosAcumuladosHeladera() {
        LocalDate fechaActivacion = LocalDate.of(2023, 10, 1); // Fecha de activación de la heladera
        LocalDate fechaActual = LocalDate.now();
        long mesesActiva = ChronoUnit.MONTHS.between(fechaActivacion, fechaActual);

        // Asumiendo que cada mes activo otorga 5 puntos, por ejemplo
        long puntosEsperados = mesesActiva * 5;

        Assertions.assertEquals(mesesActiva, donacion.getMesesActiva().longValue());
        Assertions.assertEquals(puntosEsperados, donacion.calcularPuntaje());
    }
}
