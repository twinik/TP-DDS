package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.IViandaRepository;
import lombok.AllArgsConstructor;

/**
 * reporta cuantas viandas tiene cada heladera
 */
@AllArgsConstructor
public class ReporteViandasPorHeladera implements IReporte {

    private IPDFGeneratorAdapter pdfGenerator;

    private IHeladeraRepository heladerasRepository;

    private IViandaRepository viandasRepository;

    public void generarPDF() {
        // TODO implement here
    }


}