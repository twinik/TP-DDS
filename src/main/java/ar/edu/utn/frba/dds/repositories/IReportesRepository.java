package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.reportes.Reporte;
import java.util.List;
import java.util.Optional;

public interface IReportesRepository {
  Optional<Reporte> buscar(Long id);

  List<Reporte> buscarTodos();

  void guardar(Reporte reporte);

  void guardar(List<Reporte> reportes);

  void actualizar(Reporte reporte);

  void eliminar(Reporte reporte);
}
