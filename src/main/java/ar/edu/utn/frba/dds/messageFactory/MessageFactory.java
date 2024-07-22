package ar.edu.utn.frba.dds.messageFactory;

import static java.lang.String.format;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Genera mensajes relevantes al dominio
 */
public class MessageFactory {
  public static String GenerarMensajeHeladerasRecomendadas(List<Heladera> heladeras) {
    StringBuilder builder = new StringBuilder();
    heladeras.forEach(h -> builder.append(format("Ir a heladera %s que le sobran %d cupos de viandas\n", h.getNombre(), h.getCuposLibresViandas()))); //TODO factoryMessage
    return builder.toString();
  }

  public static String generarMensajeViandasFaltantes(int viandasFaltantes, Heladera heladera) {
    return "Faltan " + viandasFaltantes + " viandas para que la heladera " +
        heladera.getNombre() + " alcance su capacidad maxima";
  }

  public static String generarMensajeViandasRestantes(Heladera heladera) {
    return "Quedan " + heladera.getViandas().size() + " viandas en la heladera " + heladera.getNombre();
  }

  public static String generarMensajeParaTecnicosPorIncidente(Tecnico tecnicoAContactar, Heladera heladera, LocalDateTime timestamp){
    return format("Hola %s se rompio la heladera %s a las %s y necesitamos que por favor venga a repararla",
        tecnicoAContactar.getNombre(), heladera.getNombre(), timestamp.toString());
  }

  public static String mensajeExcepcionDireccionTarjeta(){
    return "Debe tener seteada una direccion para poder enviarle la tarjeta";
  }

  public static String mensajeExcepcionTipoDocumento(){
    return "Tipo de Documento Invalido";
  }



}
