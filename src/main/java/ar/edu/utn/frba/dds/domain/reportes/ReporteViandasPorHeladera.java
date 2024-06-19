package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * ReporteViandasPorHeladera class representa un reporte de viandas por heladera.
 */

@AllArgsConstructor
@Setter
@Getter
public class ReporteViandasPorHeladera implements IReporte {

    private final String tituloReporte = "Cantidad de Viandas Retiradas/Colocadas por Heladera";

    private IPDFGeneratorAdapter pdfGenerator;

    private IDonacionesViandaRepository donacionesViandaRepository;

    private IRedistribucionesViandaRepository redistribucionesViandaRepository;

    public void generarPDF() {
        LocalDate hoy = LocalDate.now();

        Map<Heladera, Long> viandasDonadasPorHeladera = donacionesViandaRepository.buscarTodos()
            .stream().collect(Collectors.groupingBy(donacion -> donacion.getVianda().getHeladera(), Collectors.counting()));

        Map<Heladera, Long> viandasRedistribuidasPorHeladera = redistribucionesViandaRepository.buscarTodos()
            .stream().collect(Collectors.groupingBy(redistribucion -> redistribucion.getHeladeraDestino(), Collectors.counting()));

        String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        String entradasInformeDonaciones = this.generarEntradasInforme(viandasDonadasPorHeladera, "Donadas");
        String entradasInformeRedistribuciones = this.generarEntradasInforme(viandasRedistribuidasPorHeladera, "Redistribuidas");

        pdfGenerator.generarPdf("reporte-viandas-heladera", tituloConFecha, entradasInformeDonaciones + "\n" + entradasInformeRedistribuciones);
    }

    private String generarEntradasInforme(Map<Heladera, Long> viandasPorHeladera, String tipo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Viandas %s:\n", tipo));
        viandasPorHeladera.forEach((heladera, cantidad) -> stringBuilder
            .append(String.format("Heladera: %s cantidad de viandas: %d\n", heladera, cantidad)));
        return stringBuilder.toString();
    }
}

/* Otra forma de hacerlo TOP
    public void generarPDF() {
        LocalDate hoy = LocalDate.now();

        Map<Heladera, Long> viandasDonadasPorHeladera = donacionesViandaRepository.buscarTodos()
                .stream().collect(Collectors.groupingBy(donacion -> donacion.getVianda().getHeladera(), Collectors.counting()));

        Map<Heladera, Long> viandasRedistribuidasPorHeladera = redistribucionesViandaRepository.buscarTodos()
                .stream().collect(Collectors.groupingBy(redistribucion -> redistribucion.getHeladeraDestino(), Collectors.counting()));

        Map<Heladera, Long> viandasPorHeladera = new HashMap<>(viandasDonadasPorHeladera);
        viandasRedistribuidasPorHeladera.forEach((heladera, cantidad) -> viandasPorHeladera.merge(heladera, cantidad, Long::sum));

        String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        pdfGenerator.generarPdf("reporte-viandas-heladera", tituloConFecha, this.generarEntradasInforme(viandasPorHeladera));
    }

    private String generarEntradasInforme(Map<Heladera, Long> viandasPorHeladera) {
        StringBuilder stringBuilder = new StringBuilder();
        viandasPorHeladera.forEach((heladera, cantidad) -> stringBuilder
                .append(String.format("Heladera: %s cantidad de viandas: %d\n", heladera, cantidad)));
        return stringBuilder.toString();
        }
    }
}

 */