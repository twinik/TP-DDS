package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PuntosDonacionDineroTest {
    Colaborador colaborador;
    DonacionDinero donacion;
    ICalculadorPuntos calculador;

    FormaColaboracion dinero = new FormaColaboracion("DONACION_DINERO");

    @BeforeEach
    void test_init(){
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
