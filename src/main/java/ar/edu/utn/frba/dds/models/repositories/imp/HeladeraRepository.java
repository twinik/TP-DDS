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
                "ON c.heladera.id = h.id where c.colaborador.id =:idColaborador",Heladera.class)
            .setParameter("idColaborador",idColaborador)
            .getResultList();
    }

    @Override
    public void guardar(Heladera heladera) {
        withTransaction(() -> entityManager().persist(heladera));
    }

    public void guardar(Heladera... heladera) {

        withTransaction(() -> {
            for (Heladera hela : heladera) {
                entityManager().persist(hela);
            }
        });
    }

    @Override
    public void actualizar(Heladera heladera) {
        withTransaction(() -> entityManager().merge(heladera));
    }

    @Override
    public void actualizar(List<Heladera> heladeras) {
        withTransaction(() -> heladeras.forEach(h -> entityManager().merge(h)));
    }

//  @Override
//  public List<Heladera> heladerasCercanas(Heladera heladera, int limite) {
//    return heladeras.stream()
//        .filter(Heladera::isActiva)
//        .sorted(Comparator.comparing(h -> h.getUbicacion().calcularDistanciaHasta(heladera.getUbicacion())))
//        .limit(limite)
//        .collect(Collectors.toList());
//  }

    @Override
    public void eliminar(Heladera heladera) {

        withTransaction(() -> {
            heladera.borrarLogico();
            entityManager().merge(heladera);
        });
    }
}
