package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.repositories.IHeladeraRepository;
import lombok.AllArgsConstructor;

/**
 * reporta todas las fallas de una heladera
 */
@AllArgsConstructor
public class ReporteFallasHeladera implements IReporte {

    private IPDFGeneratorAdapter pdfGenerator;

    private IHeladeraRepository heladerasRepository;

    public void generarPDF() {
        // TODO implement here
    }

}