package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DonacionesVIandaRepository implements IDonacionesViandaRepository {

  private List<DonacionVianda> donaciones;

  public DonacionesVIandaRepository() {
    this.donaciones = new ArrayList<>();
  }

  @Override
  public Optional<DonacionVianda> buscar(long id) {
    return donaciones.stream().filter(d -> d.getId() == id).findFirst();
  }

  @Override
  public List<DonacionVianda> buscarTodos() {
    return this.donaciones;
  }

  @Override
  public void guardar(DonacionVianda donacionVianda) {
    this.donaciones.add(donacionVianda);
  }

  @Override
  public void actualizar(DonacionVianda donacionVianda) {

  }

  @Override
  public void eliminar(DonacionVianda donacionVianda) {

  }
}
