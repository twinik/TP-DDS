package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PuntosDonacionDineroTest {
    Colaborador colaborador;
    DonacionDinero donacion;
    CalculadorDePuntos calculador;


    @BeforeEach
    void test_init(){
        colaborador = new Colaborador();
        donacion = new DonacionDinero(colaborador, 2000F, FrecuenciaDonacion.MENSUAL, LocalDate.of(2024, 4, 5));
        calculador = new CalculadorPuntosDonacionDinero();
        donacion.setCalculadorDePuntos(calculador);
    }

    @Test
    @DisplayName("Se dono 2000 pesos y se obtuvieron 1000 puntos")
    void validarPuntosAcumuladosDonacionDinero() {
        Assertions.assertEquals(1000F, calculador.calcularPuntos(donacion));
    }
}
