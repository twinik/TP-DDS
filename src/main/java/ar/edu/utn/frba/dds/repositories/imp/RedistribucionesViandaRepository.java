package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesVIandaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RedistribucionesViandaRepository implements IRedistribucionesVIandaRepository {

  private List<RedistribucionViandas> redistribucionViandas;

  public RedistribucionesViandaRepository() {
    this.redistribucionViandas = new ArrayList<>();
  }

  @Override
  public Optional<RedistribucionViandas> buscar(int id) {
    return Optional.empty();
  }

  @Override
  public List<RedistribucionViandas> buscarTodos() {
    return this.redistribucionViandas;
  }

  @Override
  public void guardar(RedistribucionViandas redistribucionViandas) {
      this.redistribucionViandas.add(redistribucionViandas);
  }

  @Override
  public void actualizar(RedistribucionViandas redistribucionViandas) {

  }

  @Override
  public void eliminar(RedistribucionViandas redistribucionViandas) {

  }
}
