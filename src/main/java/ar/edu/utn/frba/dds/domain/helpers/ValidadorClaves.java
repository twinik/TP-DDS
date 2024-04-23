package ar.edu.utn.frba.dds.domain.helpers;

import ar.edu.utn.frba.dds.domain.helpers.validaciones.Validacion;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ValidadorClaves {

    private List<Validacion> validaciones;

    @Getter
    private MotivoNoValido motivoNoValida;

    public ValidadorClaves() {
        this.validaciones = new ArrayList<>();
    }

    public void agregarValidaciones(Validacion... validaciones) {
        Collections.addAll(this.validaciones, validaciones);
    }

    public boolean esValida(String contrasena) {
        for(Validacion v : validaciones) {
            if (!v.validar(contrasena)) {
                this.motivoNoValida = v.getMotivo();
                return false;
            }
        }
        return true;
    }


}