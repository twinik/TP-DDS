package ar.edu.utn.frba.dds.Domain.Helpers;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@NoArgsConstructor
public class Validador {

    //TODO: el lector del properties
    private static final File archivo = new File("src/main/java/ar/edu/utn/frba/dds/Domain/Assets/top10000PeoresContrasenas");

    private static final Integer LONGITUD_MINIMA = 8;
    private static final Integer LONGITUD_MAXIMA = 64;
    private boolean perteneceA100000Peores(String contrasena) {
        String linea;
        try (Scanner entrada = new Scanner(archivo)) {
            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                if (linea.equals(contrasena)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean esMuyCorta (String contrasena){
        return contrasena.length() < LONGITUD_MINIMA;
    }

    private boolean esMuyLarga(String contrasena){
        return contrasena.length() > LONGITUD_MAXIMA;
    }

    public boolean esValida (String contrasena)  {
        return !this.perteneceA100000Peores(contrasena) &&
                !this.esMuyLarga(contrasena) &&
                !this.esMuyCorta(contrasena);
    }


}