package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ColaboradorRepository class permite interactuar con los colaboradores.
 */
public class ColaboradoresRepository implements IColaboradoresRepository {
  private List<Colaborador> colaboradores;

  public ColaboradoresRepository() {
    colaboradores = new ArrayList<>();
  }

  @Override
  public Optional<Colaborador> buscar(TipoDocumento tipoDocumento, Integer documento) {
    return this.colaboradores.stream().filter(c -> c.getUsuario().getDocumento().equals(documento) && c.getUsuario().getTipoDocumento().equals(tipoDocumento)).findFirst();
  }
  @Override
  public Optional<Colaborador> buscar(long id) {
    return this.colaboradores.stream().filter(c -> c.getId() == id).findFirst();
  }



  @Override
  public List<Colaborador> buscarTodos() {
    return colaboradores;
  }

  @Override
  public void guardar(Colaborador colaborador) {
    this.colaboradores.add(colaborador);
  }

  @Override
  public void actualizar(Colaborador colaborador) {
  }

  @Override
  public void eliminar(Colaborador colaborador) {
    this.colaboradores.remove(colaborador);
  }
}
