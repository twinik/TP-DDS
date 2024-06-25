package ar.edu.utn.frba.dds.domain.reportes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestReporte {

  @Test
  @DisplayName("pruebo que lo que me devuelve la mock api sea correcto")
  void generarReporte(){
    ReporteViandasPorColaborador reporte = new ReporteViandasPorColaborador();
    IPDFGeneratorAdapter generador = Mockito.mock(IPDFGeneratorAdapter.class);
    reporte.setPdfGenerator(generador);
    reporte.setViandasRepository((IViandasRepository) ServiceLocator.get("viandasRepository"));
    doNothing().when(generador).generarPdf(any(),any(),any());
    reporte.generarPDF();
    verify(generador,times(1)).generarPdf(any(),any(),any());
  }
}
