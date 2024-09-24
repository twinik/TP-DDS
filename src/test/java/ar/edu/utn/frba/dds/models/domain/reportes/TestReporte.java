package ar.edu.utn.frba.dds.models.domain.reportes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ar.edu.utn.frba.dds.models.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.models.domain.reportes.ReporteViandasPorColaborador;
import ar.edu.utn.frba.dds.models.repositories.IViandasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestReporte {

    @Test
    @DisplayName("pruebo que lo que me devuelve la mock api sea correcto")
    void generarReporte() {
        ReporteViandasPorColaborador reporte = new ReporteViandasPorColaborador();
        IPDFGeneratorAdapter generador = Mockito.mock(IPDFGeneratorAdapter.class);
        reporte.setPdfGenerator(generador);
        reporte.setViandasRepository(ServiceLocator.get(IViandasRepository.class));
        doNothing().when(generador).generarPdf(any(), any(), any());
        reporte.generarPDF();
        verify(generador, times(1)).generarPdf(any(), any(), any());
    }
}
