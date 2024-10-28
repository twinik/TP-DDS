package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.heladeras.IngresoVianda;
import ar.edu.utn.frba.dds.models.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.models.repositories.ISolicitudesAperturaHeladeraRepository;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.time.LocalDateTime;

@AllArgsConstructor
public class SolicitudAperturaHeladeraService {
    private ISolicitudesAperturaHeladeraRepository solicitudesAperturaHeladeraRepository;


    public void generarSolicitud(IngresoVianda ingreso) {
        SolicitudAperturaHeladera solicitudAperturaHeladera = new SolicitudAperturaHeladera();
        solicitudAperturaHeladera.setColaborador(ingreso.getColaborador());
        solicitudAperturaHeladera.setHeladera(ingreso.getHeladera());
        solicitudAperturaHeladera.setMotivo("apertura para ingresar una donacion");
        solicitudAperturaHeladera.setViandas(ingreso);
        solicitudAperturaHeladera.setTimestamp(LocalDateTime.now());
        this.publicarABroker(solicitudAperturaHeladera);
    }

    public void generarSolicitud(RedistribucionViandas redistribucionViandas) {
        // ORIGEN
        SolicitudAperturaHeladera solicitudAperturaHeladeraOrigen = new SolicitudAperturaHeladera();
        solicitudAperturaHeladeraOrigen.setColaborador(redistribucionViandas.getColaborador());
        solicitudAperturaHeladeraOrigen.setRedistribucionViandas(redistribucionViandas);
        solicitudAperturaHeladeraOrigen.setHeladera(redistribucionViandas.getHeladeraOrigen());
        solicitudAperturaHeladeraOrigen.setMotivo("Apertura para redistribuir viandas");
        solicitudAperturaHeladeraOrigen.setTimestamp(LocalDateTime.now());

        //DESTINO
        SolicitudAperturaHeladera solicitudAperturaDestino = new SolicitudAperturaHeladera();
        solicitudAperturaDestino.setColaborador(solicitudAperturaHeladeraOrigen.getColaborador());
        solicitudAperturaDestino.setHeladera(redistribucionViandas.getHeladeraDestino());
        solicitudAperturaDestino.setRedistribucionViandas(redistribucionViandas);
        solicitudAperturaDestino.setMotivo(solicitudAperturaHeladeraOrigen.getMotivo());
        solicitudAperturaDestino.setTimestamp(LocalDateTime.now());


        this.publicarABroker(solicitudAperturaHeladeraOrigen);
        this.publicarABroker(solicitudAperturaDestino);
    }

    private void publicarABroker(SolicitudAperturaHeladera solicitudAperturaHeladera) {
        this.solicitudesAperturaHeladeraRepository.guardar(solicitudAperturaHeladera);
        try {
            solicitudAperturaHeladera.publicarSolicitudABroker();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
