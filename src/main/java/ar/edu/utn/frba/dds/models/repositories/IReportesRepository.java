package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.reportes.Reporte;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReportesRepository {
    Optional<Reporte> buscar(String id);

    List<Reporte> buscarTodos();

    List<Reporte> buscarEntreFechas(LocalDate desde, LocalDate hasta);

    void guardar(Reporte reporte);

    void guardar(List<Reporte> reportes);

    void actualizar(Reporte reporte);

    void eliminar(Reporte reporte);
}
