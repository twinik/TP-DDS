package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reporta las viandas donadas por un colaborador
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("viandas_x_colab")
public class ReporteViandasPorColaborador extends Reporte {
    @Transient
    private final String tituloReporte = "Viandas donadas por colaborador - Semana ";
    @Transient
    private IPDFGeneratorAdapter pdfGenerator;
    @Transient
    private IViandasRepository viandasRepository;


    public ReporteViandasPorColaborador(String rutaArchivo, IPDFGeneratorAdapter pdfGenerator, IViandasRepository viandasRepository) {
        super(rutaArchivo);
        this.pdfGenerator = pdfGenerator;
        this.viandasRepository = viandasRepository;
    }

/*    public void generarPDF() {
        LocalDate hoy = LocalDate.now();
        List<Vianda> viandasDonadasEstaSemana = viandasRepository.buscarTodosMismaSemana(LocalDate.now());

        Map<Colaborador, Long> viandasPorColaborador = viandasDonadasEstaSemana
                .stream().collect(Collectors.groupingBy(Vianda::getColaborador, Collectors.counting()));

        String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        pdfGenerator.generarPdf(this.rutaArchivo, tituloConFecha, this.generarEntradasInforme(viandasPorColaborador));


    }
*/

    public void generarPDF() {
        LocalDate hoy = LocalDate.now();
        Map<String,Long> viandasPorColaborador = viandasRepository.buscarViandasAgrupadasPorColaborador(hoy);

        String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        pdfGenerator.generarPdf(this.rutaArchivo, tituloConFecha, this.generarEntradasInforme(viandasPorColaborador));
    }

    private String generarEntradasInforme(Map<String, Long> viandasPorColaborador) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        viandasPorColaborador.forEach((nom_completo, cant) -> stringBuilder
                .append(String.format("%s ha donado %d vianda(s)\n", nom_completo, cant)));
        return stringBuilder.toString();
    }


}