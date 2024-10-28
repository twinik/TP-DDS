package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.models.repositories.IReportesRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ReportesRepository implements IReportesRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Reporte> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Reporte.class, id));
    }

    @Override
    public List<Reporte> buscarTodos() {
        return entityManager().createQuery("from Reporte where activo=:activo", Reporte.class)
                .setParameter("activo", true)
                .getResultList();
    }

    @Override
    public List<Reporte> buscarEntreFechas(LocalDate desde, LocalDate hasta) {
        return entityManager().createQuery("from Reporte where created_at between :desde and :hasta order by created_at desc", Reporte.class)
                .setParameter("desde", desde.atStartOfDay())
                .setParameter("hasta", hasta.atTime(LocalTime.MAX))
                .getResultList();
    }

    @Override
    public void guardar(Reporte reporte) {
        withTransaction(() -> entityManager().persist(reporte));
    }

    @Override
    public void guardar(List<Reporte> reportes) {
        withTransaction(() -> {
            for (Reporte reporte : reportes) {
                entityManager().persist(reporte);
            }
        });
    }

    @Override
    public void actualizar(Reporte reporte) {
        withTransaction(() -> entityManager().merge(reporte));
    }

    @Override
    public void eliminar(Reporte reporte) {

        withTransaction(() -> {
            reporte.borrarLogico();
            entityManager().merge(reporte);
        });
    }
}
