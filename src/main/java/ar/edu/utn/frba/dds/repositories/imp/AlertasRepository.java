package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlertasRepository implements IAlertasRepository {

  private List<Alerta> alertas;

  public AlertasRepository() {
    alertas = new ArrayList<>();
  }

  @Override
  public Optional<Alerta> buscar(int id) {
    return Optional.empty();
  }

  @Override
  public List<Alerta> buscarTodos() {
    return this.alertas;
  }

  @Override
  public void guardar(Alerta alerta) {
    this.alertas.add(alerta);
  }

  @Override
  public void actualizar(Alerta alerta) {

  }

  @Override
  public void eliminar(Alerta alerta) {

  }
}
