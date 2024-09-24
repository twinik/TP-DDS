package ar.edu.utn.frba.dds.brokers.dtos;

import lombok.Getter;

@Getter
public class AperturaHeladeraBrokerDTO {
    // formato: ID_HELADERA;ID_TARJETA_COLABORADOR;ID_SOLICITUD_APERTURA;TIMESTAMP
    public String idHeladera;
    public String idTarjetaColaborador;
    public String idSolicitudApertura;
    public long timestamp;

    private AperturaHeladeraBrokerDTO(String idHeladera, String idTarjetaColaborador, String idSolicitudApertura, long timestamp) {
        this.idHeladera = idHeladera;
        this.idTarjetaColaborador = idTarjetaColaborador;
        this.idSolicitudApertura = idSolicitudApertura;
        this.timestamp = timestamp;
    }

    public static AperturaHeladeraBrokerDTO fromString(String s) {
        String[] fields = s.split(";");
        return new AperturaHeladeraBrokerDTO(fields[0], fields[1], fields[2], Long.parseLong(fields[3]));
    }

    public String toString() {
        return String.format("Id: %s -- Id Tarjeta: %s --- IdSolcitud: %s -- timestamp: %d", this.idHeladera, this.idTarjetaColaborador, this.idSolicitudApertura, this.timestamp);
    }
}
