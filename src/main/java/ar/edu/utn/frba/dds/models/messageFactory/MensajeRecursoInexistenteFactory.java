package ar.edu.utn.frba.dds.models.messageFactory;

public class MensajeRecursoInexistenteFactory {
  public static String generarMensaje() {
    return "no se encuentra el recurso solicitado";
  }

  public static String generarMensaje(String nombreRecurso) {
    return "no se encuentra el " + nombreRecurso + " solicitado";
  }

  public static String generarMensaje(String nombreRecurso, String id) {
    return "no se encuentra el " + nombreRecurso + " solicitado para el id: " + id;
  }
}
