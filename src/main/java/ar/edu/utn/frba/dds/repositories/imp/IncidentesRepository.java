package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.repositories.IIncidenteRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncidentesRepository implements IIncidenteRepository {

  private List<Incidente> incidentes;

  public IncidentesRepository() {
    incidentes = new ArrayList<>();
  }

  @Override
  public Optional<Incidente> buscar(Long id) {
    return this.incidentes.stream().filter(v -> v.getId() == id).findFirst();
  }

  @Override
  public List<Incidente> buscarTodos() {
    return this.incidentes;
  }

  @Override
  public void guardar(Incidente incidente) {
    this.incidentes.add(incidente);
  }

  @Override
  public void actualizar(Incidente incidente) {}

  @Override
  public void eliminar(Incidente incidente) {
  }
}
