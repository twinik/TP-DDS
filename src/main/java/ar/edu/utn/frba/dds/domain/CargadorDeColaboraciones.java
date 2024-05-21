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
  private CSVReaderAdapter csvReader;
  private MailSenderAdapter mailSender;

  public CargadorDeColaboraciones(CSVReaderAdapter csvReader, MailSenderAdapter mailAdapter) {
    this.csvReader = csvReader;
    this.mailSender = mailAdapter;
  }



  /**
   * @param path
   * @return
   */
  public List<Colaboracion> cargarColaboraciones(String path) {
    // TODO implement here
    return null;
  }

}