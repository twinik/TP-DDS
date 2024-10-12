package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.domain.tarjetas.FrecuenciaDiaria;
import ar.edu.utn.frba.dds.models.domain.tarjetas.FrecuenciaUso;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FrecuenciaUsoAttributeConverter implements AttributeConverter<FrecuenciaUso, String> {

    @Override
    public String convertToDatabaseColumn(FrecuenciaUso frecuenciaUso) {
        if (frecuenciaUso == null) return null;
        if (frecuenciaUso instanceof FrecuenciaDiaria) {
            return "FRECUENCIA_DIARIA";
        }
        return null;
    }

    @Override
    public FrecuenciaUso convertToEntityAttribute(String s) {
        if (s == null) return null;
        switch (s) {
            case "FRECUENCIA_DIARIA" -> {
                return new FrecuenciaDiaria();
            }
            default -> {
                return null;
            }
        }
    }

}