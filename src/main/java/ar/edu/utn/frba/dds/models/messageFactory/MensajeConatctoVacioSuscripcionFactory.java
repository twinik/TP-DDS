package ar.edu.utn.frba.dds.models.messageFactory;

public class MensajeConatctoVacioSuscripcionFactory {
  public static String generarMensaje(String contacto) {
    return "Nunca cargaste tu contacto de " + contacto + " por lo tanto no te puedes suscribir de esta forma";
  }
}
