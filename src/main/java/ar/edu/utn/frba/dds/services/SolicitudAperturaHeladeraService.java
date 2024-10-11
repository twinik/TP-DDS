package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.IngresoVianda;
import ar.edu.utn.frba.dds.models.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.models.repositories.ISolicitudesAperturaHeladeraRepository;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.time.LocalDateTime;

@AllArgsConstructor
public class SolicitudAperturaHeladeraService {
  private ISolicitudesAperturaHeladeraRepository solicitudesAperturaHeladeraRepository;
  private ColaboradoresService colaboradoresService;


  public void generarSolicitud(Vianda vianda, String idColaborador) {
    SolicitudAperturaHeladera solicitudAperturaHeladera = new SolicitudAperturaHeladera();
    Colaborador colaborador = this.colaboradoresService.obtenerColaborador(idColaborador);
    solicitudAperturaHeladera.setColaborador(colaborador);
    solicitudAperturaHeladera.setHeladera(vianda.getHeladera());
    solicitudAperturaHeladera.setMotivo("apertura para ingresar una donacion");
    solicitudAperturaHeladera.setViandas(new IngresoVianda(vianda.getFechaDonacion(),colaborador));
    solicitudAperturaHeladera.agregarViandas(vianda);
    solicitudAperturaHeladera.setTimestamp(LocalDateTime.now());
    this.solicitudesAperturaHeladeraRepository.guardar(solicitudAperturaHeladera);
    try {
      solicitudAperturaHeladera.publicarSolicitudABroker();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
