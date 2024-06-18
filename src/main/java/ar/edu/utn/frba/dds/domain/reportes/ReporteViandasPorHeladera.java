package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.helpers.DateHelper;
import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.repositories.IDonacionesVIandaRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesVIandaRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * reporta cuantas viandas tiene cada heladera
 */
@AllArgsConstructor
@Setter
@Getter
public class ReporteViandasPorHeladera implements IReporte {

    private final String tituloReporte = "Cantidad de Viandas Retiradas/Colocadas por Heladera";

    private IPDFGeneratorAdapter pdfGenerator;

    private IDonacionesVIandaRepository donacionesVIandaRepository;

    private IRedistribucionesVIandaRepository redistribucionesVIandaRepository;

    public void generarPDF() {

        //TODO THOMI

//        LocalDate hoy = LocalDate.now();
//
//
//        Map<Heladera,Long> viandasPorColaborador = donacionesVIandaRepository.buscarTodos()
//            .stream().collect(Collectors.groupingBy(DonacionVianda::get,Collectors.counting()));
//
//        Map<Colaborador, Long> redistribucionesPorColaborador = redistribucionesVIandaRepository.buscarTodos()
//            .stream().collect(Collectors.groupingBy(RedistribucionViandas::getColaborador,Collectors.
//                counting().accumulator(RedistribucionViandas::)));
//
//        String tituloConFecha = tituloReporte.concat(" fecha: " + hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//
//        pdfGenerator.generarPdf("reporte-viandas-colab",tituloConFecha,this.generarEntradasInforme(viandasPorColaborador))
    }

    private String generarEntradasInforme(){
        return null;
    }


}