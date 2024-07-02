package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reporta las viandas donadas por un colaborador
 */
@AllArgsConstructor
@NoArgsConstructor
public class ReporteViandasPorColaborador implements IReporte {

  private String rutaArchivo;
  private final String tituloReporte = "Viandas donadas por colaborador - Semana ";

  private IPDFGeneratorAdapter pdfGenerator;

  private IViandasRepository viandasRepository;


  public void generarPDF() {
    LocalDate hoy = LocalDate.now();
    List<Vianda> viandasDonadasEstaSemana = viandasRepository.buscarTodos()
        .stream()
        .filter(v -> DateHelper.esLaMismaSemana(v.getFechaDonacion(), hoy))
        .toList();

    Map<Colaborador, Long> viandasPorColaborador = viandasDonadasEstaSemana
        .stream().collect(Collectors.groupingBy(Vianda::getColaborador, Collectors.counting()));

    String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    pdfGenerator.generarPdf(this.rutaArchivo, tituloConFecha, this.generarEntradasInforme(viandasPorColaborador));


  }

  private String generarEntradasInforme(Map<Colaborador, Long> viandasPorColaborador) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\n");
    viandasPorColaborador.forEach((colab, cant) -> stringBuilder
        .append(String.format("%s ha donado %d vianda(s)\n", colab.getNombreYapellido(), cant)));
    return stringBuilder.toString();
  }

}