package ar.edu.utn.frba.dds.brokers.dtos;

import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import lombok.Getter;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;

@Getter
public class AperturaHeladeraBrokerDTO {
  // formato: ID_HELADERA;ID_TARJETA_COLABORADOR;ID_SOLICITUD_APERTURA;TIMESTAMP
  public int idHeladera;
  public String idTarjetaColaborador;
  public int idSolicitudApertura;
  public long timestamp;

  private AperturaHeladeraBrokerDTO(int idHeladera, String idTarjetaColaborador, int idSolicitudApertura, long timestamp) {
    this.idHeladera = idHeladera;
    this.idTarjetaColaborador = idTarjetaColaborador;
    this.idSolicitudApertura = idSolicitudApertura;
    this.timestamp = timestamp;
  }

  public static AperturaHeladeraBrokerDTO fromString(String s) {
    String[] fields = s.split(";");
    return new AperturaHeladeraBrokerDTO(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]), Long.parseLong(fields[3]));
  }

  public String toString(){
    return String.format("Id: %d -- Id Tarjeta: %s --- IdSolcitud: %d -- timestamp: %d",this.idHeladera,this.idTarjetaColaborador,this.idSolicitudApertura,this.timestamp);
  }
}
