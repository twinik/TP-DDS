package ar.edu.utn.frba.dds.helpers;

public class DniHelper {


    /**
     * Verifica si el string dado contiene entre 7 y 8 dígitos numéricos.
     *
     * @param input El string a validar.
     * @return true si el string tiene entre 7 y 8 caracteres y es numérico, false en caso contrario.
     */
    public static boolean esValido(String input) {
        if (input == null) {
            return false;
        }

        // Verificar si la longitud está entre 7 y 8 caracteres
        if (input.length() < 7 || input.length() > 8) {
            return false;
        }

        // Verificar que todos los caracteres sean numéricos
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Ejemplos de prueba
        String prueba1 = "1234567";
        String prueba2 = "12345678";
        String prueba3 = "1234abc";
        String prueba4 = "123456789";

        System.out.println("Prueba 1 (1234567): " + esValido(prueba1));  // true
        System.out.println("Prueba 2 (12345678): " + esValido(prueba2)); // true
        System.out.println("Prueba 3 (1234abc): " + esValido(prueba3));  // false
        System.out.println("Prueba 4 (123456789): " + esValido(prueba4)); // false
    }
}
