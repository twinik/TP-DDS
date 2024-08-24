package ar.edu.utn.frba.dds.repositories.imp;


import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.repositories.IUsuariosRepository;
import java.util.List;
import java.util.Optional;

/**
 * ISuscripcionesRepository interface permite interactuar con las suscripciones.
 */
public class SuscripcionesRepository implements ISuscripcionesRepository {

  @Override
  public Optional<Suscripcion> buscar(long id) {
    return Optional.empty();
  }

  @Override
  public List<Suscripcion> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(Suscripcion suscripcion) {

  }

  @Override
  public void actualizar(Suscripcion suscripcion) {

  }

  @Override
  public void eliminar(Suscripcion suscripcion) {

  }
}
