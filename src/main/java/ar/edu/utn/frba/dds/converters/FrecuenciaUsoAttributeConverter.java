package ar.edu.utn.frba.dds.converters;

import ar.edu.utn.frba.dds.domain.tarjetas.FrecuenciaDiaria;
import ar.edu.utn.frba.dds.domain.tarjetas.FrecuenciaUso;
import javax.persistence.AttributeConverter;

public class FrecuenciaUsoAttributeConverter implements AttributeConverter<FrecuenciaUso,String> {

    @Override
    public String convertToDatabaseColumn(FrecuenciaUso frecuenciaUso) {
        // TODO: ESTA HORRIBLE ESTO
        return frecuenciaUso.getClass().toString();
    }

    @Override
    public FrecuenciaUso convertToEntityAttribute(String s) {
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