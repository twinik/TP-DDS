package ar.edu.utn.frba.dds.domain.suscripciones;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.RecomendadorHeladeras;
import lombok.AllArgsConstructor;
import java.util.List;

/**
 * suscripcion que notifica si hubo un desperfecto en la heladera
 */
@AllArgsConstructor
public class SuscripcionDesperfectoHeladera implements ITipoSuscripcion {
  private RecomendadorHeladeras recomendadorHeladeras;

  public void notificar(Heladera heladera, Suscripcion suscripcion) {
    if (!heladera.isActiva()) {
      suscripcion.getNotificacionStrategy().notificar(suscripcion.getMedioDeContacto(),
          GenerarMensajeHeladerasRecomendadas(heladera)); //Este metodo deberia ser un factory
    }
  }

  private String GenerarMensajeHeladerasRecomendadas(Heladera heladera) {
    List<Heladera> heladeras = recomendadorHeladeras.recomendarCombinacionHeladeras(heladera);
    StringBuilder builder = new StringBuilder();
    heladeras.forEach(h -> builder.append(String.
        format("Ir a heladera %s que le sobran %d cupos de viandas\n", h.getNombre(), h.getCuposLibresViandas()))); //TODO factoryMessage
    return builder.toString();
  }

}