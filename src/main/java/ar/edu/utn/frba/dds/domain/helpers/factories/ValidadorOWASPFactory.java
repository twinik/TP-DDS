package ar.edu.utn.frba.dds.domain.helpers.factories;

import ar.edu.utn.frba.dds.domain.helpers.ValidadorClaves;
import ar.edu.utn.frba.dds.domain.helpers.validaciones.Validacion;
import ar.edu.utn.frba.dds.domain.helpers.validaciones.ValidacionLargoClaves;
import ar.edu.utn.frba.dds.domain.helpers.validaciones.ValidacionListaClavesNuevo;

import java.util.ArrayList;
import java.util.List;

public class ValidadorOWASPFactory implements ValidadorFactory{


    public ValidadorClaves crearValidador() {
        List<Validacion> estrategias = new ArrayList<>();
        estrategias.add(new ValidacionLargoClaves());
        estrategias.add(new ValidacionListaClavesNuevo());
        return new ValidadorClaves(estrategias);

    }
}

