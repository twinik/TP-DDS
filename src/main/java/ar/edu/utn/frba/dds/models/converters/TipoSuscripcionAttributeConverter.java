package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.suscripciones.ITipoSuscripcion;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionDesperfectoHeladera;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionViandasFaltantes;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionViandasRestantes;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoSuscripcionAttributeConverter implements AttributeConverter<ITipoSuscripcion, String> {
    @Override
    public String convertToDatabaseColumn(ITipoSuscripcion tipoSuscripcion) {
        if (tipoSuscripcion == null) return null;
        if (tipoSuscripcion instanceof SuscripcionDesperfectoHeladera) return "SUSCRIPCION_DESPERFECTO";
        if (tipoSuscripcion instanceof SuscripcionViandasRestantes) return "SUSCRIPCION_VIANDAS_RESTANTES";
        if (tipoSuscripcion instanceof SuscripcionViandasFaltantes) return "SUSCRIPCION_VIANDAS_FALTANTES";
        return null;
    }

    @Override
    public ITipoSuscripcion convertToEntityAttribute(String s) {
        if (s == null) return null;
        switch (s) {
            case "SUSCRIPCION_DESPERFECTO" -> {
                return new SuscripcionDesperfectoHeladera(ServiceLocator.get(RecomendadorHeladeras.class));
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