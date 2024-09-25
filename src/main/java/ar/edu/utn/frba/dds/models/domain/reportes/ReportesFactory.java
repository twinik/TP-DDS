package ar.edu.utn.frba.dds.models.domain.reportes;

import ar.edu.utn.frba.dds.models.domain.pdfs.ItextPdfGenerator;
import ar.edu.utn.frba.dds.models.repositories.*;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
public class ReportesFactory {

    private static final String RUTA_VIANDAS_X_COLAB = "reporte-viandas-colab";
    private static final String RUTA_VIANDAS_X_HELADERA = "reporte-viandas-heladera";
    private static final String RUTA_FALLAS_X_HELADERA = "reporte-fallas-heladera";
    private IViandasRepository viandasRepository;
    private IDonacionesViandaRepository donacionesViandaRepository;
    private IRedistribucionesViandaRepository redistribucionesViandaRepository;
    private IFallasTecnicasRepository fallasTecnicasRepository;
    private IAlertasRepository alertasRepository;

    private String generarStringRuta(String nombre, LocalDate fecha) {
        return nombre + '-' + fecha.toString() + ".pdf";
    }

    public Reporte create(TipoReporte tipo, LocalDate fecha) {


        switch (tipo) {
            case VIANDA_X_COLAB -> {
                return new ReporteViandasPorColaborador(generarStringRuta(RUTA_VIANDAS_X_COLAB, fecha), new ItextPdfGenerator(), this.viandasRepository);
            }
            case VIANDA_X_HELADERA -> {
                return new ReporteViandasPorHeladera(generarStringRuta(RUTA_VIANDAS_X_HELADERA, fecha), new ItextPdfGenerator(), this.donacionesViandaRepository, this.redistribucionesViandaRepository);
            }
            case FALLAS_HELADERA -> {
                return new ReporteFallasHeladera(generarStringRuta(RUTA_FALLAS_X_HELADERA, fecha), new ItextPdfGenerator(), fallasTecnicasRepository, alertasRepository);
            }
            default -> {
                return null;
            }
        }
    }
}
