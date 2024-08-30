package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

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

  /**
   * Genera un reporte en formato PDF con la cantidad de viandas colocadas y retiradas por heladera.
   */
  public void generarPDF() {
    LocalDate hoy = LocalDate.now();

    Map<Heladera, Long> viandasColocadasPorHeladera =
        donacionesViandaRepository
            .buscarTodos()
            .stream()
            .filter(donacion -> DateHelper.esLaMismaSemana(LocalDate.now(),donacion.getFecha()))
            .collect(Collectors.groupingBy(donacion -> donacion.getVianda().getHeladera(), Collectors.counting()));

    viandasColocadasPorHeladera
        .putAll(redistribucionesViandaRepository
            .buscarTodos()
            .stream()
            .filter(redistribucionViandas -> DateHelper.esLaMismaSemana(LocalDate.now(),redistribucionViandas.getFecha()))
            .collect(Collectors.groupingBy(RedistribucionViandas::getHeladeraDestino, Collectors.summingLong(RedistribucionViandas::getCantidad))));

    Map<Heladera, Long> viandasRetiradasPorHeladera =
        redistribucionesViandaRepository
            .buscarTodos()
            .stream()
            .filter(redistribucionViandas -> DateHelper.esLaMismaSemana(LocalDate.now(),redistribucionViandas.getFecha()))
            .collect(Collectors.groupingBy(RedistribucionViandas::getHeladeraOrigen, Collectors.counting()));

    String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    String entradasInformeColocadas = this.generarEntradasInforme(viandasColocadasPorHeladera, "Colocadas");
    String entradasInformeRetiradas = this.generarEntradasInforme(viandasRetiradasPorHeladera, "Retiradas");

    pdfGenerator.generarPdf(this.rutaArchivo, tituloConFecha, entradasInformeColocadas + "\n" + entradasInformeRetiradas);
  }

  private String generarEntradasInforme(Map<Heladera, Long> viandasPorHeladera, String tipo) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\n");
    stringBuilder.append(String.format("Viandas %s:\n", tipo));
    viandasPorHeladera.forEach((heladera, cantidad) -> stringBuilder
        .append(String.format("Heladera: %s cantidad de viandas: %d\n", heladera.getNombre(), cantidad)));
    return stringBuilder.toString();
  }
}