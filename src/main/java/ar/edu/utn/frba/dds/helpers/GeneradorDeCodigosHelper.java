package ar.edu.utn.frba.dds.helpers;

import java.security.SecureRandom;

public class GeneradorDeCodigosHelper {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generarAlfanumericoUnico(int tamanio) {
        StringBuilder stringBuilder = new StringBuilder(tamanio);
        for (int i = 0; i < tamanio; i++) {
            int randomIndex = RANDOM.nextInt(CARACTERES.length());
            stringBuilder.append(CARACTERES.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public static boolean esCodigoValido(String codigo, Integer longitud) {

        if (codigo == null || codigo.length() != longitud) {
            return false;
        }


        for (char c : codigo.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String prueba = GeneradorDeCodigosHelper.generarAlfanumericoUnico(20);
        System.out.println("Código generado: " + prueba);

        String codigoAValidar = "abc123DEF45";
        boolean esValido = GeneradorDeCodigosHelper.esCodigoValido(codigoAValidar, 11);
        System.out.println("¿Es válido el código '" + codigoAValidar + "'? " + esValido);
    }


}
