package ar.edu.utn.frba.dds.Domain.Helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @Test
    @DisplayName("pruebo todos los casos de contrasenas para ver si son validas o no")
    void esValidaTest() {
        // es de las peores
        Assertions.assertFalse(Validador.esValida("password"));
        // esta es muy corta
        Assertions.assertFalse(Validador.esValida("123"));
        //esta es muy larga
        Assertions.assertFalse(Validador.esValida("D$w9&XqPz4!sGv2@rN#lJ*cEoF+tH(3zL-xM/vK,nA.bQ1:wG^fR%yT&uI=8Y7U6iO;9P0"));
        //esta pasa
        Assertions.assertTrue(Validador.esValida("thomas_ariel_luca"));
    }
}