package ar.edu.utn.frba.dds.cronjobs;


import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.FALLAS_HELADERA;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_COLAB;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_HELADERA;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.domain.pdfs.ItextPdfGenerator;
import ar.edu.utn.frba.dds.domain.reportes.IReporte;
import ar.edu.utn.frba.dds.domain.reportes.ReporteFallasHeladera;
import ar.edu.utn.frba.dds.domain.reportes.ReporteViandasPorColaborador;
import ar.edu.utn.frba.dds.domain.reportes.ReporteViandasPorHeladera;
import ar.edu.utn.frba.dds.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.domain.reportes.TipoReporte;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import java.util.ArrayList;
import java.util.List;

public class ReportesCronjob {
  public static void main(String[] args) {
    List<IReporte> reportes = new ArrayList<>();
    reportes.add(ReportesFactory.create(VIANDA_X_COLAB));
    reportes.add(ReportesFactory.create(VIANDA_X_HELADERA));
    reportes.add(ReportesFactory.create(FALLAS_HELADERA));
    reportes.forEach(IReporte::generarPDF);

  }
}
