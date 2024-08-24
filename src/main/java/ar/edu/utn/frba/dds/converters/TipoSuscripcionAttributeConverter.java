package ar.edu.utn.frba.dds.converters;

import ar.edu.utn.frba.dds.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.domain.notifications.MailNotificationStrategy;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.notifications.TelegramNotificacionStrategy;
import ar.edu.utn.frba.dds.domain.notifications.WhatsappSenderStrategy;
import ar.edu.utn.frba.dds.domain.suscripciones.ITipoSuscripcion;
import ar.edu.utn.frba.dds.domain.suscripciones.SuscripcionDesperfectoHeladera;
import ar.edu.utn.frba.dds.domain.suscripciones.SuscripcionViandasFaltantes;
import ar.edu.utn.frba.dds.domain.suscripciones.SuscripcionViandasRestantes;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoSuscripcionAttributeConverter implements AttributeConverter<ITipoSuscripcion,String> {
  @Override
  public String convertToDatabaseColumn(ITipoSuscripcion tipoSuscripcion) {
    if(tipoSuscripcion instanceof SuscripcionDesperfectoHeladera) return "SUSCRIPCION_DESPERFECTO";
    if(tipoSuscripcion instanceof SuscripcionViandasRestantes) return "SUSCRIPCION_VIANDAS_RESTANTES";
    if(tipoSuscripcion instanceof SuscripcionViandasFaltantes) return "SUSCRIPCION_VIANDAS_FALTANTES";
    return null;
  }

  @Override
  public ITipoSuscripcion convertToEntityAttribute(String s) {
    switch (s){
      case "SUSCRIPCION_DESPERFECTO" -> {
        return new SuscripcionDesperfectoHeladera((RecomendadorHeladeras) ServiceLocator.get("RecomendadorHeladeras"));
      }
      case "SUSCRIPCION_VIANDAS_RESTANTES" -> {
        return new SuscripcionViandasRestantes();
      }
      case "SUSCRIPCION_VIANDAS_FALTANTES" -> {
        return new SuscripcionViandasFaltantes();
      }
      default -> {
        return null;
      }
    }
  }
}