package ar.edu.utn.frba.dds.helpers;

import java.security.SecureRandom;

/**
 * PasswordGenerator class permite generar una contrasenia.
 */

//TODO: no olivdarse que existe esto
public class PasswordGenerator {
    private static final String MAYUSC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSC = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITOS = "0123456789";
    private static final String CARACTERES_ESPECIALES = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/~`";

    // Combine all character sets
    private static final String CARACTERES_TOTALES = MAYUSC + MINUSC + DIGITOS + CARACTERES_ESPECIALES;

    // SecureRandom instance for generating random numbers
    private static final SecureRandom random = new SecureRandom();

    /**
     * @param tamanio largo de la contrasenia
     * @return genera una contrasenia de largo @tamanio y la devuelve
     */
    public static String generatePassword(int tamanio) {
        if (tamanio < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters");
        }

        StringBuilder password = new StringBuilder(tamanio);

        // Ensure the password contains at least one character from each set
        password.append(MAYUSC.charAt(random.nextInt(MAYUSC.length())));
        password.append(MINUSC.charAt(random.nextInt(MINUSC.length())));
        password.append(DIGITOS.charAt(random.nextInt(DIGITOS.length())));
        password.append(CARACTERES_ESPECIALES.charAt(random.nextInt(CARACTERES_ESPECIALES.length())));


        for (int i = 4; i < tamanio; i++) {
            password.append(CARACTERES_TOTALES.charAt(random.nextInt(CARACTERES_TOTALES.length())));
        }


        return shuffleString(password.toString());
    }


    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap characters[i] with characters[j]
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        String contra = generatePassword(30);
        System.out.println(contra);
    }


}
