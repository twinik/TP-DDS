package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 *
 */
@Setter
@Getter
public class CargadorDeColaboraciones {

  /**
   * Default constructor
   */
  public CargadorDeColaboraciones() {
  }

  /**
   *
   */
  private CsvReaderAdapter csvReaderAdapter;

  private MailSenderAdapter mailSenderAdapter;

  /**
   * @param path
   * @return
   */
  public List<Colaboracion> cargarColaboraciones(String path) {
    // TODO implement here
    return null;
  }

}