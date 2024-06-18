package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.helpers.DateHelper;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * reporta todas las fallas de una heladera
 */
@AllArgsConstructor
@Setter
@Getter
public class ReporteFallasHeladera implements IReporte {

    private final String tituloReporte = "Cantidad de fallas por heladera esta semana";
    private IPDFGeneratorAdapter pdfGenerator;

    private IFallasTecnicasRepository fallasTecnicasRepository;

    private IAlertasRepository alertasRepository;

    public void generarPDF() {
        LocalDate hoy = LocalDate.now();

        Map<Heladera,Long> alertasPorHeladera = alertasRepository.buscarTodos()
            .stream().collect(Collectors.groupingBy(Alerta::getHeladera,Collectors.counting()));

        Map<Heladera,Long> fallasTecnicasPorHeladera = fallasTecnicasRepository.buscarTodos()
            .stream().collect(Collectors.groupingBy(FallaTecnica::getHeladera,Collectors.counting()));

        alertasPorHeladera.forEach(((heladera,cant) -> fallasTecnicasPorHeladera.merge(heladera,cant, Long::sum)));

        String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        pdfGenerator.generarPdf("reporte-fallas-vianda",tituloConFecha,this.generarEntradasInforme(alertasPorHeladera));
    }

    private String generarEntradasInforme(Map<Heladera,Long> incidnetesPorHeladera){
        StringBuilder stringBuilder = new StringBuilder();
        incidnetesPorHeladera.forEach((h,cant) -> stringBuilder.
            append(String.format("Heladera: %s cantidad de fallas: %d",h,cant)));
        return stringBuilder.toString();
    }

}