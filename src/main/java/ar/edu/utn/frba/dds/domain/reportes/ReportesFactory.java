package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.pdfs.ItextPdfGenerator;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportesFactory {

  private IViandasRepository viandasRepository;
  private IDonacionesViandaRepository donacionesViandaRepository;
  private IRedistribucionesViandaRepository redistribucionesViandaRepository;

  private IFallasTecnicasRepository fallasTecnicasRepository;
  private IAlertasRepository alertasRepository;

  private static final String RUTA_VIANDAS_X_COLAB = "reporte-viandas-colab.pdf";
  private static final String RUTA_VIANDAS_X_HELADERA = "reporte-viandas-heladera.pdf";
  private static final String RUTA_FALLAS_X_HELADERA = "reporte-fallas-heladera.pdf";

  public IReporte create(TipoReporte tipo) {


    switch (tipo) {
      case VIANDA_X_COLAB -> {
        return new ReporteViandasPorColaborador(RUTA_VIANDAS_X_COLAB, new ItextPdfGenerator(), this.viandasRepository);
      }
      case VIANDA_X_HELADERA -> {
        return new ReporteViandasPorHeladera(RUTA_VIANDAS_X_HELADERA, new ItextPdfGenerator(), this.donacionesViandaRepository,this.redistribucionesViandaRepository);
      }
      case FALLAS_HELADERA -> {
        return new ReporteFallasHeladera(RUTA_FALLAS_X_HELADERA, new ItextPdfGenerator(), fallasTecnicasRepository, alertasRepository);
      }
      default -> {
        return null;
      }
    }
  }
}
