package ar.edu.utn.frba.dds.models.messageFactory;

public class MensajeErrorCsvFactory {
  public static String generarMensaje(String cuerpo) {
    return cuerpo + ".<br>Por favor, comúniquese con el equipo técnico si tiene dudas sobre el armado del archivo.";

  }
}
