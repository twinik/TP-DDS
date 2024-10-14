package ar.edu.utn.frba.dds.models.domain.reportes;

import ar.edu.utn.frba.dds.models.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.models.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.models.repositories.IRedistribucionesViandaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * ReporteViandasPorHeladera class representa un reporte de viandas por heladera.
 */

@AllArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue("viandas_x_heladera")
@NoArgsConstructor
public class ReporteViandasPorHeladera extends Reporte {
    @Transient
    private final String tituloReporte = "Cantidad de viandas retiradas/colocadas por heladera";
    @Transient
    private IPDFGeneratorAdapter pdfGenerator;
    @Transient
    private IDonacionesViandaRepository donacionesViandaRepository;
    @Transient
    private IRedistribucionesViandaRepository redistribucionesViandaRepository;

    public ReporteViandasPorHeladera(String rutaArchivo, IPDFGeneratorAdapter pdfGenerator, IDonacionesViandaRepository donacionesViandaRepository, IRedistribucionesViandaRepository redistribucionesViandaRepository) {
        super(rutaArchivo);
        this.pdfGenerator = pdfGenerator;
        this.donacionesViandaRepository = donacionesViandaRepository;
        this.redistribucionesViandaRepository = redistribucionesViandaRepository;
    }

    public void generarPDF() {
        LocalDate hoy = LocalDate.now();

        // Use the new method to get the donations grouped by collaborator
        Map<String, Long> viandasColocadasPorHeladera = donacionesViandaRepository.buscarDonacionesAgrupadasPorHeladera(hoy);

        viandasColocadasPorHeladera
                .putAll(redistribucionesViandaRepository.buscarViandasColocadasPorHeladera(hoy));

        Map<String, Long> viandasRetiradasPorHeladera = redistribucionesViandaRepository.buscarViandasRetiradasPorHeladera(hoy);


        String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        String entradasInformeColocadas = this.generarEntradasInforme(viandasColocadasPorHeladera, "Colocadas");
        String entradasInformeRetiradas = this.generarEntradasInforme(viandasRetiradasPorHeladera, "Retiradas");

        pdfGenerator.generarPdf(this.rutaArchivo, tituloConFecha, entradasInformeColocadas + "\n" + entradasInformeRetiradas);
    }

    @Override
    public String getTipo() {
        return "Viandas por heladera";
    }

    private String generarEntradasInforme(Map<String, Long> viandasPorHeladera, String tipo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(String.format("Viandas %s:\n", tipo));
        viandasPorHeladera.forEach((heladera, cantidad) -> stringBuilder
                .append(String.format("Heladera: %s cantidad de viandas: %d\n", heladera, cantidad)));
        return stringBuilder.toString();
    }
}