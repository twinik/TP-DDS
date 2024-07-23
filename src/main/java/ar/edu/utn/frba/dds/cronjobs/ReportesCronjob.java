package ar.edu.utn.frba.dds.cronjobs;


import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.FALLAS_HELADERA;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_COLAB;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_HELADERA;x
import ar.edu.utn.frba.dds.domain.reportes.IReporte;
import ar.edu.utn.frba.dds.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.util.ArrayList;
import java.util.List;

public class ReportesCronjob {
  public static void main(String[] args) {

    ReportesFactory reportesFactory = (ReportesFactory) ServiceLocator.get("reportesFactory");

    List<IReporte> reportes = new ArrayList<>();
    reportes.add(reportesFactory.create(VIANDA_X_COLAB));
    reportes.add(reportesFactory.create(VIANDA_X_HELADERA));
    reportes.add(reportesFactory.create(FALLAS_HELADERA));
    reportes.forEach(IReporte::generarPDF);

  }
}
