package ar.edu.utn.frba.dds.cronjobs;


import static ar.edu.utn.frba.dds.models.domain.reportes.TipoReporte.*;

import ar.edu.utn.frba.dds.models.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.models.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.models.repositories.IReportesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * genera los reportes semanales con estadisticas
 * PARA QUE FUNCIONE BIEN HAY QUE CORRERLO TODOS LOS SABADOS A ULTIMA HORA
 */
public class ReportesCronjob {
  public static void main(String[] args) {

    ReportesFactory reportesFactory = ServiceLocator.get(ReportesFactory.class);
    IReportesRepository repository = ServiceLocator.get(IReportesRepository.class);
    List<Reporte> reportes = new ArrayList<>();
    reportes.add(reportesFactory.create(VIANDA_X_COLAB, LocalDate.now()));
    reportes.add(reportesFactory.create(VIANDA_X_HELADERA, LocalDate.now()));
    reportes.add(reportesFactory.create(FALLAS_HELADERA, LocalDate.now()));
    reportes.forEach(Reporte::generarPDF);

    repository.guardar(reportes);

  }
}
