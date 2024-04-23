package ar.edu.utn.frba.dds.domain.helpers.validaciones;

import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ValidacionListaClaves implements Validacion {

    @Getter
    private String motivo = "La clave aparece en la lista de las 10.000 peores contrasenias";
    private ConfigReader config;

    public ValidacionListaClaves(){
        this.config = new ConfigReader();
    }

    public boolean validar(String clave) {
        File archivo = null;
        try {
            archivo = new File(this.config.getProperty("worstPasswordsFilePath"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String linea;
        try (Scanner entrada = new Scanner(archivo)) {
            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                if (linea.equals(clave)) {
                    return false;
                }
            }
        } catch (
        FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
