package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.repositories.IColaboradorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ColaboradorRepository class permite interactuar con los colaboradores.
 */
public class ColaboradorRepository implements IColaboradorRepository {
  private List<Colaborador> colaboradores;

  public ColaboradorRepository() {
    colaboradores = new ArrayList<Colaborador>();
  }

  @Override
  public Optional<Colaborador> buscar(TipoDocumento tipoDocumento, Integer documento) {
    return this.colaboradores.stream().filter(c -> c.getUsuario().getDocumento().equals(documento) && c.getUsuario().getTipoDocumento().equals(tipoDocumento)).findFirst();
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
