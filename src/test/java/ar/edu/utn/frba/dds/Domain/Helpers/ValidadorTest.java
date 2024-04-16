package ar.edu.utn.frba.dds.Domain.Helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @Test
    @DisplayName("pruebo todos los casos de contrasenas para ver si son validas o no")
    void esValidaTest() {
        Validador unValidador = new Validador();
        // es de las peores
        Assertions.assertFalse(unValidador.esValida("password"));
        // esta es muy corta
        Assertions.assertFalse(unValidador.esValida("123"));
        //esta es muy larga
        Assertions.assertFalse(unValidador.esValida("D$w9&XqPz4!sGv2@rN#lJ*cEoF+tH(3zL-xM/vK,nA.bQ1:wG^fR%yT&uI=8Y7U6iO;9P0"));
        //esta pasa
        Assertions.assertTrue(unValidador.esValida("thomas_ariel_luca"));
    }
}