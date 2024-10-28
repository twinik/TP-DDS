package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.models.repositories.IRespuestasCampoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class RespuestasCampoRepository implements IRespuestasCampoRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<RespuestaACampo> buscar(String id) {
    return Optional.ofNullable(entityManager().find(RespuestaACampo.class, id));
  }

  @Override
  public List<RespuestaACampo> buscarTodos() {
    return entityManager().createQuery("from RespuestaACampo where activo=:activo", RespuestaACampo.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(RespuestaACampo respuestaACampo) {
    withTransaction(() -> entityManager().persist(respuestaACampo));
  }

  @Override
  public void actualizar(RespuestaACampo respuestaACampo) {
    withTransaction(() -> entityManager().merge(respuestaACampo));
  }

  @Override
  public void eliminar(RespuestaACampo respuestaACampo) {

    withTransaction(() -> {
      respuestaACampo.borrarLogico();
      entityManager().merge(respuestaACampo);
    });
  }

}