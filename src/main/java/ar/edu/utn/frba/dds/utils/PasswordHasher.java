package ar.edu.utn.frba.dds.utils;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordHasher {

    // Hashes a plain-text password
    public static String hashPassword(String plainPassword) {
      // Generate a salt and hash the password
      return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verifies a plain-text password against a hashed password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
      // Check if the plain password matches the hashed password
      return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    // Example usage
    public static void main(String[] args) {
      String password = "my_secure_password";

      // Hash the password
      String hashedPassword = hashPassword(password);
      System.out.println("Hashed Password: " + hashedPassword);

      // Verify the password
      boolean isMatch = verifyPassword(password, hashedPassword);
      System.out.println("Password matches: " + isMatch);

      // Verify a wrong password
      boolean isMatchWrong = verifyPassword("wrong_password", hashedPassword);
      System.out.println("Wrong password matches: " + isMatchWrong);
    }
  }

