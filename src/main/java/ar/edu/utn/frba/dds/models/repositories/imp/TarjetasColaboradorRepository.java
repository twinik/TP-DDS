package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasColaboradorRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor

public class TarjetasColaboradorRepository implements ITarjetasColaboradorRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<TarjetaColaborador> buscarPorCodigo(String codigo) {
    try {
      TarjetaColaborador t = (TarjetaColaborador) entityManager().createQuery("from TarjetaColaborador where codigo=:codigo")
          .setParameter("codigo", codigo)
          .getSingleResult();
      return Optional.ofNullable(t);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<TarjetaColaborador> buscar(String id) {
    return Optional.ofNullable(entityManager().find(TarjetaColaborador.class, id));
  }

  @Override
  public List<TarjetaColaborador> buscarTodos() {
    return entityManager().createQuery("from TarjetaColaborador where activo=:activo", TarjetaColaborador.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(TarjetaColaborador tarjeta) {
    withTransaction(() -> entityManager().persist(tarjeta));
  }

  @Override
  public void actualizar(TarjetaColaborador tarjeta) {
    withTransaction(() -> entityManager().merge(tarjeta));
  }

  @Override
  public void eliminar(TarjetaColaborador tarjeta) {

    withTransaction(() -> {
      tarjeta.borrarLogico();
      entityManager().merge(tarjeta);
    });
  }
}
