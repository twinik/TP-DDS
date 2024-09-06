package ar.edu.utn.frba.dds.cronjobs;


import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.FALLAS_HELADERA;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_COLAB;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_HELADERA;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.repositories.*;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * genera los reportes semanales con estadisticas
 * PARA QUE FUNCIONE BIEN HAY QUE CORRERLO TODOS LOS SABADOS A ULTIMA HORA
 */
public class ReportesCronjob {
    public static void main(String[] args) {

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
