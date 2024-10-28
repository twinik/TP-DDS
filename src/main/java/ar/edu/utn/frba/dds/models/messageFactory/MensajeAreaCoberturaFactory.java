package ar.edu.utn.frba.dds.models.messageFactory;

import java.nio.charset.StandardCharsets;

public class MensajeAreaCoberturaFactory {
  public static String generarMensaje(){
    return new String("Falta el punto de referencia en el área de cobertura".getBytes(), StandardCharsets.UTF_8);
  }
}
