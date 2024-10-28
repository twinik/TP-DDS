package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.RegistroTemperatura;
import ar.edu.utn.frba.dds.models.repositories.IRegistrosTemperaturaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class RegistrosTemperaturaRepository implements IRegistrosTemperaturaRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<RegistroTemperatura> buscar(String id) {
    return Optional.ofNullable(entityManager().find(RegistroTemperatura.class, id));
  }

  @Override
  public List<RegistroTemperatura> buscarTodos() {
    return entityManager().createQuery("from RegistroTemperatura where activo=:activo", RegistroTemperatura.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(RegistroTemperatura registroTemperatura) {
    withTransaction(() -> entityManager().persist(registroTemperatura));
  }

  @Override
  public void actualizar(RegistroTemperatura registroTemperatura) {
    withTransaction(() -> entityManager().merge(registroTemperatura));
  }

  @Override
  public void eliminar(RegistroTemperatura registroTemperatura) {

    withTransaction(() -> {
      registroTemperatura.borrarLogico();
      entityManager().merge(registroTemperatura);
    });
  }

}
