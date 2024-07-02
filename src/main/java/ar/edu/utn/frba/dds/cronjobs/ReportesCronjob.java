package ar.edu.utn.frba.dds.cronjobs;


import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.domain.pdfs.ItextPdfGenerator;
import ar.edu.utn.frba.dds.domain.reportes.IReporte;
import ar.edu.utn.frba.dds.domain.reportes.ReporteFallasHeladera;
import ar.edu.utn.frba.dds.domain.reportes.ReporteViandasPorColaborador;
import ar.edu.utn.frba.dds.domain.reportes.ReporteViandasPorHeladera;
import ar.edu.utn.frba.dds.domain.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import java.util.ArrayList;
import java.util.List;

public class ReportesCronjob {
  public static void main(String[] args) {
    IPDFGeneratorAdapter ipdfGeneratorAdapter = new ItextPdfGenerator();
    IViandasRepository viandasRepository = (IViandasRepository) ServiceLocator.get("viandasRepository");
    List<IReporte> reportes = new ArrayList<>();
    reportes.add(new ReporteViandasPorColaborador("reporte-viandas-colab.pdf",ipdfGeneratorAdapter,viandasRepository));
    reportes.add(new ReporteViandasPorHeladera("reporte-viandas-heladera.pdf",ipdfGeneratorAdapter,(IDonacionesViandaRepository) ServiceLocator.get("donacionesViandaRepository"),(IRedistribucionesViandaRepository) ServiceLocator.get("redistribucionesViandaRepository")));
    reportes.add(new ReporteFallasHeladera("reporte-fallas-heladera.pdf",ipdfGeneratorAdapter,(IFallasTecnicasRepository) ServiceLocator.get("fallasTecnicasRepository"),(IAlertasRepository) ServiceLocator.get("alertasRepository")));

    reportes.forEach(IReporte::generarPDF);

  }
}
