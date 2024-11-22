package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class HeladeraRepository implements IHeladerasRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<Heladera> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Heladera.class, id));
  }

  @Override
  public void refresh(List<Heladera> heladeras) {
    heladeras.forEach(h -> entityManager().refresh(h));
  }

  @Override
  public void refresh(Heladera heladera) {
    entityManager().refresh(heladera);
  }

  @Override
  public List<Heladera> buscarTodos() {
    return entityManager().createQuery("from Heladera where activo=:activo", Heladera.class).
        setParameter("activo", true)
        .getResultList();
  }

  public Heladera buscarPorNombre(String nombre) {
    return entityManager().createQuery("from Heladera where nombre=:nombre", Heladera.class).
        setParameter("nombre", nombre)
        .getSingleResult();
  }

  @Override
  public List<Heladera> buscarPorColaborador(String idColaborador) {
    return entityManager().createQuery("SELECT h from Heladera h INNER JOIN ColocacionHeladeras c " +
            "ON c.heladera.id = h.id where c.colaborador.id =:idColaborador and h.activo=:activo", Heladera.class)
        .setParameter("idColaborador", idColaborador)
        .setParameter("activo", true)
        .getResultList();
  }

  public List<Heladera> buscarConAlertas() {
    return entityManager().createQuery(
            "SELECT DISTINCT h FROM Heladera h " +
                "JOIN Incidente i ON h.id = i.heladera.id " +
                "WHERE TYPE(i) = Alerta", Heladera.class)
        .getResultList();
  }

  @Override
  public void guardar(Heladera heladera) {
    withTransaction(() -> entityManager().persist(heladera));
  }

  @Override
  public void actualizar(Heladera heladera) {
    withTransaction(() -> entityManager().merge(heladera));
  }

  @Override
  public void actualizar(List<Heladera> heladeras) {
    withTransaction(() -> heladeras.forEach(h -> entityManager().merge(h)));
  }

  @Override
  public void eliminar(Heladera heladera) {
    withTransaction(() -> {
      heladera.borrarLogico();
      entityManager().merge(heladera);
    });
  }

  @Override
  public void detach(Heladera h) {
    entityManager().detach(h);
  }
}
