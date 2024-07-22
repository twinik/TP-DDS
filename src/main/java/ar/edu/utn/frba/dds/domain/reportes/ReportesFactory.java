package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.domain.pdfs.ItextPdfGenerator;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;

public class ReportesFactory {
  public static IReporte create(TipoReporte tipo) {

    IPDFGeneratorAdapter ipdfGeneratorAdapter = new ItextPdfGenerator();
    switch (tipo) {
      case VIANDA_X_COLAB -> {
        return new ReporteViandasPorColaborador("reporte-viandas-colab.pdf", ipdfGeneratorAdapter, (IViandasRepository) ServiceLocator.get("viandasRepository"));
      }
      case VIANDA_X_HELADERA -> {
        return new ReporteViandasPorHeladera("reporte-viandas-heladera.pdf", ipdfGeneratorAdapter, (IDonacionesViandaRepository) ServiceLocator.get("donacionesViandaRepository"), (IRedistribucionesViandaRepository) ServiceLocator.get("redistribucionesViandaRepository"));
      }
      case FALLAS_HELADERA -> {
        return new ReporteFallasHeladera("reporte-fallas-heladera.pdf", ipdfGeneratorAdapter, (IFallasTecnicasRepository) ServiceLocator.get("fallasTecnicasRepository"), (IAlertasRepository) ServiceLocator.get("alertasRepository"));
      }
      default -> {
        return null;
      }
    }
  }
}
