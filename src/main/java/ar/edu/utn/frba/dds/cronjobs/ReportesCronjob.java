package ar.edu.utn.frba.dds.cronjobs;


import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.FALLAS_HELADERA;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_COLAB;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_HELADERA;

import ar.edu.utn.frba.dds.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.repositories.IReportesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportesCronjob {
    public static void main(String[] args) {

        // PARA QUE FUNCIONE BIEN HAY QUE CORRERLO TODOS LOS SABADOS A ULTIMA HORA

        ReportesFactory reportesFactory = ServiceLocator.get("reportesFactory", ReportesFactory.class);
        IReportesRepository repository = ServiceLocator.get("reportesRepository", IReportesRepository.class);
        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reportesFactory.create(VIANDA_X_COLAB, LocalDate.now()));
        reportes.add(reportesFactory.create(VIANDA_X_HELADERA, LocalDate.now()));
        reportes.add(reportesFactory.create(FALLAS_HELADERA, LocalDate.now()));
        reportes.forEach(Reporte::generarPDF);

        repository.guardar(reportes);

    }
}
