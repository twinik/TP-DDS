package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import ar.edu.utn.frba.dds.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.helpers.LocalDateTypeAdapter;
import ar.edu.utn.frba.dds.domain.utils.FormaColaboracionMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;

public class CargaToColaboracionMapper {
  public static Colaboracion colaboracionFromCarga(CargaColaboracion carga) {

    Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
        .create();

    switch (FormaColaboracionMapper.obtenerFormaColaboracion(carga.getFormaColaboracion())) {
      case DONACION_DINERO -> {
        return gson.fromJson(carga.getJsonColaboracion(), DonacionDinero.class);
      }
      case DONACION_VIANDA -> {
        return gson.fromJson(carga.getJsonColaboracion(), DonacionVianda.class);
      }
      case REDISTRIBUCION_VIANDA -> {
        return gson.fromJson(carga.getJsonColaboracion(), RedistribucionViandas.class);
      }
      case REGISTRO_PERSONA -> {
        return gson.fromJson(carga.getJsonColaboracion(), AltaPersonaVulnerable.class);
      }
      default -> throw new RuntimeException("Forma de Colaboracion Invalida");
    }
  }
}
