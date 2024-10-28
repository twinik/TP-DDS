package ar.edu.utn.frba.dds.models.domain.reportes;

import ar.edu.utn.frba.dds.models.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.models.repositories.IFallasTecnicasRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    Map<String, Long> alertasPorHeladera =
        alertasRepository.buscarAlertasAgrupadasPorHeladera(hoy.toLocalDate());

    Map<String, Long> fallasTecnicasPorHeladera =
        fallasTecnicasRepository.buscarFallasAgrupadasPorHeladera(hoy.toLocalDate());

    alertasPorHeladera.forEach((heladera, cant) -> fallasTecnicasPorHeladera.merge(heladera, cant, Long::sum));

    String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    pdfGenerator.generarPdf(this.rutaArchivo, tituloConFecha, this.generarEntradasInforme(fallasTecnicasPorHeladera));
  }

  @Override
  public String getTipo() {
    return "Fallas por heladera";
  }

  private String generarEntradasInforme(Map<String, Long> incidnetesPorHeladera) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\n");
    incidnetesPorHeladera.forEach((nombre, cant) -> stringBuilder
        .append(String.format("Heladera: %s cantidad de fallas: %d\n", nombre, cant)));
    return stringBuilder.toString();
  }

}