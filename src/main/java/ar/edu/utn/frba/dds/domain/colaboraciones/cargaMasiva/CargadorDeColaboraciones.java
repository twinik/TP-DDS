package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import ar.edu.utn.frba.dds.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.helpers.LocalDateTypeAdapter;
import ar.edu.utn.frba.dds.domain.utils.FormaColaboracionMapper;
import ar.edu.utn.frba.dds.domain.utils.MailSenderAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 *
 */
@Setter
@Getter
public class CargadorDeColaboraciones {
  private CSVReaderAdapter csvReader;
  private MailSenderAdapter mailSender;
  private String filePath;
  private String separator;

  public CargadorDeColaboraciones() {}

  public CargadorDeColaboraciones(CSVReaderAdapter csvReader, MailSenderAdapter mailAdapter) throws IOException {
    this.csvReader = csvReader;
    this.mailSender = mailAdapter;
    this.filePath = new ConfigReader("cargacolaboraciones.properties").getProperty("cargadorColaboracionesFilePath");
    this.separator = new ConfigReader("cargacolaboraciones.properties").getProperty("separator");
  }

  /**
   *
   */
  public List<Colaboracion> cargarColaboraciones() throws IOException {
    List<Object> registros = csvReader.readCsv(filePath, separator);

    ArrayList<Colaboracion> colaboraciones = new ArrayList<Colaboracion>();

    for (Object reg : registros) {
      CargaColaboracion cargaColaboracion = (CargaColaboracion) reg;

      colaboraciones.add(colaboracionFromCarga(cargaColaboracion));

      //TODO: chequear si el colaborador no tiene usuario (repository?) => mandar mail
    }
    return colaboraciones;
  }

  public Colaboracion colaboracionFromCarga(CargaColaboracion carga) {

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