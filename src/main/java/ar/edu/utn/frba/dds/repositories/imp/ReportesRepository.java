package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.repositories.IReportesRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class ReportesRepository implements IReportesRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<Reporte> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(Reporte.class,id));
  }

  @Override
  public List<Reporte> buscarTodos() {
    return entityManager().createQuery("from Reporte where activo=:activo", Reporte.class)
        .setParameter("activo",true)
        .getResultList();
  }

  @Override
  public void guardar(Reporte reporte) {
    withTransaction(()-> entityManager().persist(reporte));
  }

  @Override
  public void guardar(List<Reporte> reportes) {
      withTransaction(()->{
        for (Reporte reporte : reportes){
          entityManager().persist(reporte);
        }
      });
  }

  @Override
  public void actualizar(Reporte reporte) {
    withTransaction(()-> entityManager().merge(reporte));
  }

  @Override
  public void eliminar(Reporte reporte) {
    reporte.borrarLogico();
    withTransaction(()-> entityManager().merge(reporte));
  }
}
