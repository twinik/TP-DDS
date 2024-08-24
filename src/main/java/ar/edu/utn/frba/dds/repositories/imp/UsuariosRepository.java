package ar.edu.utn.frba.dds.repositories.imp;


import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.repositories.IUsuariosRepository;
import java.util.List;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
public class UsuariosRepository implements IUsuariosRepository {

  @Override
  public Optional<Usuario> buscar(long id) {
    return Optional.empty();
  }

  @Override
  public List<Usuario> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(Usuario usuario) {

  }

  @Override
  public void actualizar(Usuario usuario) {

  }

  @Override
  public void eliminar(Usuario usuario) {

  }
}
