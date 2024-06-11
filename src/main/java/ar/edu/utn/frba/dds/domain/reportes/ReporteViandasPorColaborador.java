package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.domain.reportes.IReporte;
import ar.edu.utn.frba.dds.repositories.IColaboradorRepository;
import ar.edu.utn.frba.dds.repositories.IViandaRepository;
import lombok.AllArgsConstructor;

/**
 * Reporta las viandas donadas por un colaborador
 */
@AllArgsConstructor
public class ReporteViandasPorColaborador implements IReporte {

    private IPDFGeneratorAdapter pdfGenerator;

    private IViandaRepository viandasRepository;

    private IColaboradorRepository colaboradorRepository;

    public void generarPDF() {
        // TODO implement here
    }

}