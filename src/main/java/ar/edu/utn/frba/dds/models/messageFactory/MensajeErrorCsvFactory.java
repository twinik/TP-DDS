package ar.edu.utn.frba.dds.models.messageFactory;

public class MensajeErrorCsvFactory {
  public static String generarMensaje(String cuerpo){
    return   cuerpo + ".<br>Le aseguramos que todas las filas anteriores a la errónea fueron cargadas con éxito. Si desea, puede eliminarlas cuando vuelva a intentar la carga.<br>Por favor, comúniquese con el equipo técnico si tiene dudas sobre el armado del archivo.";

  }
}
