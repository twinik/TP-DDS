package ar.edu.utn.frba.dds.Domain.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Validador {
    private static final File archivo = new File("src/main/java/ar/edu/utn/frba/dds/Domain/Assets/top10000PeoresContrasenas");

    private static Integer LONGITUD_MINIMA = 8;
    private static Integer LONGITUD_MAXIMA = 64;
    private static boolean perteneceA100000Peores(String contrasena) {
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

    private static boolean esMuyCorta (String contrasena){
        return contrasena.length() < LONGITUD_MINIMA;
    }

    private static boolean esMuyLarga(String contrasena){
        return contrasena.length() > LONGITUD_MAXIMA;
    }

    public static boolean esValida (String contrasena)  {
        return !perteneceA100000Peores(contrasena) && !esMuyLarga(contrasena) && !esMuyCorta(contrasena);
    }


}