package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.repositories.ISolicitudesAperturaHeladeraRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolcitudesAperturaHeladeraRepository implements ISolicitudesAperturaHeladeraRepository {

  private List<SolicitudAperturaHeladera> solcitudes;


  public SolcitudesAperturaHeladeraRepository() {
    this.solcitudes = new ArrayList<>();
  }

  @Override
  public Optional<SolicitudAperturaHeladera> buscar(long id) {
    return this.solcitudes.stream().filter(s -> s.getId() == id).findFirst();
  }

  @Override
  public List<SolicitudAperturaHeladera> buscarTodos() {
    return this.solcitudes;
  }

  @Override
  public void guardar(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    solcitudes.add(solicitudAperturaHeladera);
  }

  @Override
  public void actualizar(SolicitudAperturaHeladera solicitudAperturaHeladera) {

  }

  @Override
  public void eliminar(SolicitudAperturaHeladera solicitudAperturaHeladera) {

  }
}
