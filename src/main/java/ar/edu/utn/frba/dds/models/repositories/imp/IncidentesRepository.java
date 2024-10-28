package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.repositories.IIncidentesRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class IncidentesRepository implements IIncidentesRepository, WithSimplePersistenceUnit {

    @Override
    public Optional<Incidente> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Incidente.class, id));
    }

    @Override
    public List<Incidente> buscarTodos() {
        return entityManager().createQuery("from Incidente where solucionado=:solucionado and activo=:activo order by timestamp", Incidente.class)
                .setParameter("solucionado", false)
                .setParameter("activo", true)
                .getResultList();
    }

    @Override
    public List<Incidente> buscarPorHeladera(String id) {
        return entityManager().createQuery("from Incidente where solucionado=:solucionado and heladera.id=:id and activo=:activo", Incidente.class)
                .setParameter("solucionado", false)
                .setParameter("activo", true)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Long cantidadNoSolucionadosPorHeladera(Heladera h) {
        return entityManager().createQuery("select count(*) from Incidente i where solucionado=:solucionado and heladera=:hel and activo=:activo", Long.class)
                .setParameter("solucionado", false)
                .setParameter("activo", true)
                .setParameter("hel", h)
                .getSingleResult();
    }

    @Override
    public void actualizar(Incidente incidente) {
        withTransaction(() -> {
                    entityManager().merge(incidente);
                }
        );
        entityManager().refresh(incidente);
    }

}
