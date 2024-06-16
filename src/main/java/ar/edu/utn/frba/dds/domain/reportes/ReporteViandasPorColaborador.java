package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.domain.reportes.IReporte;
import ar.edu.utn.frba.dds.repositories.IColaboradorRepository;
import ar.edu.utn.frba.dds.repositories.IViandaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Reporta las viandas donadas por un colaborador
 */
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ReporteViandasPorColaborador implements IReporte {

    private IPDFGeneratorAdapter pdfGenerator;

    private IViandaRepository viandasRepository;

    private IColaboradorRepository colaboradorRepository;

    public void generarPDF() {
        pdfGenerator.generarPdf("ejemplo","ejemplo","ejemplo");
    }

}