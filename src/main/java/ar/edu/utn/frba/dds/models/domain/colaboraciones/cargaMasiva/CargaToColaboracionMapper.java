package ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.IPuntajeCalculable;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.helpers.LocalDateTimeTypeAdapter;
import ar.edu.utn.frba.dds.helpers.LocalDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * CargaToColaboracionMapper class se encarga de mapear una carga a una colaboracion.
 */
public class CargaToColaboracionMapper {

    /**
     * colaboracionFromCarga mapea una carga a una colaboracion.
     */
    public static IPuntajeCalculable colaboracionFromCarga(CargaColaboracion carga, Colaborador colaborador) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();

        switch (carga.getFormaColaboracion()) {
            case "DONACION_DINERO" -> {
                DonacionDinero c = gson.fromJson(carga.getJsonColaboracion(), DonacionDinero.class);
                c.setColaborador(colaborador);
                return c;
            }
            case "DONACION_VIANDA" -> {
                DonacionVianda c = gson.fromJson(carga.getJsonColaboracion(), DonacionVianda.class);
                c.setColaborador(colaborador);
                return c;
            }
            case "REDISTRIBUCION_VIANDA" -> {
                RedistribucionViandas c = gson.fromJson(carga.getJsonColaboracion(), RedistribucionViandas.class);
                c.setColaborador(colaborador);
                return c;
            }
            case "REGISTRO_PERSONA" -> {
                AltaPersonaVulnerable c = gson.fromJson(carga.getJsonColaboracion(), AltaPersonaVulnerable.class);
                c.setColaborador(colaborador);
                return c;
            }
            default -> throw new RuntimeException("Forma de Colaboracion Invalida");
        }
    }
}
