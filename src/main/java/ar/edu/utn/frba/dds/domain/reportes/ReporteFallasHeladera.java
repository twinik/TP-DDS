package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * reporta todas las fallas de una heladera.
 */
@AllArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue("falla_heladeras")
@NoArgsConstructor
public class ReporteFallasHeladera extends Reporte {

  @Transient
  private final String tituloReporte = "Cantidad de fallas por heladera - Semana ";
  @Transient
  private IPDFGeneratorAdapter pdfGenerator;

  @Transient
  private IFallasTecnicasRepository fallasTecnicasRepository;

  @Transient
  private IAlertasRepository alertasRepository;

  public ReporteFallasHeladera(String rutaArchivo, IPDFGeneratorAdapter pdfGenerator, IFallasTecnicasRepository fallasTecnicasRepository, IAlertasRepository alertasRepository) {
    super(rutaArchivo);
    this.pdfGenerator = pdfGenerator;
    this.fallasTecnicasRepository = fallasTecnicasRepository;
    this.alertasRepository = alertasRepository;
  }

  public void generarPDF() {
    LocalDateTime hoy = LocalDateTime.now();

    Map<Heladera, Long> alertasPorHeladera =
        alertasRepository
            .buscarTodos()
            .stream()
            .filter(a -> DateHelper.esLaMismaSemana(a.getTimestamp(), hoy))
            .collect(Collectors.groupingBy(Alerta::getHeladera, Collectors.counting()));

    Map<Heladera, Long> fallasTecnicasPorHeladera =
        fallasTecnicasRepository
            .buscarTodos()
            .stream()
            .filter(f -> DateHelper.esLaMismaSemana(f.getTimestamp(), hoy))
            .collect(Collectors.groupingBy(FallaTecnica::getHeladera, Collectors.counting()));

    alertasPorHeladera.forEach(((heladera, cant) -> fallasTecnicasPorHeladera.merge(heladera, cant, Long::sum)));

    String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    pdfGenerator.generarPdf(this.rutaArchivo, tituloConFecha, this.generarEntradasInforme(alertasPorHeladera));
  }

  private String generarEntradasInforme(Map<Heladera, Long> incidnetesPorHeladera) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\n");
    incidnetesPorHeladera.forEach((h, cant) -> stringBuilder
        .append(String.format("Heladera: %s cantidad de fallas: %d", h.getNombre(), cant)));
    return stringBuilder.toString();
  }

}