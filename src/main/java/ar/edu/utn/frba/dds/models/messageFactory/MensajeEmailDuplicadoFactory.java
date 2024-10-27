package ar.edu.utn.frba.dds.models.messageFactory;

public class MensajeEmailDuplicadoFactory {
  public static String generarMensaje() {
    return "Ya existe un usuario con ese mail";
  }

  public static String generarMensaje(String mail) {
    return "Ya existe otro usuario con el mail: " + mail;
  }
}
