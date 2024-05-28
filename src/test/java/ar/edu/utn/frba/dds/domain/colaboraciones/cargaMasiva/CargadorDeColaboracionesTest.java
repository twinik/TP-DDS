package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.utils.SendGridMailSender;
import ar.edu.utn.frba.dds.repositories.imp.ColaboradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;

class CargadorDeColaboracionesTest {
  private CargaColaboracionCsvReader csvReader;
  private SendGridMailSender mailSender;
  private CargadorDeColaboraciones cargador;

  private ColaboradorRepository repositorio;
  @BeforeEach
  void setUp() throws IOException {
    csvReader = new CargaColaboracionCsvReader();
    mailSender = mock(SendGridMailSender.class);
    doNothing().when(mailSender).enviarMail(any());
    repositorio = new ColaboradorRepository();
    cargador = new CargadorDeColaboraciones("src/main/java/ar/edu/utn/frba/dds/domain/assets/cargacolaboraciones.csv", csvReader, mailSender,repositorio);
  }

  @Test
  void pruebaJsonToDonacionDinero() throws IOException {
    String json = "{\"monto\": 10000, \"frecuencia\": \"DIARIA\", \"fecha\": \"09/12/2018\"}";
    CargaColaboracion carga = new CargaColaboracion();
    carga.setFormaColaboracion("DINERO");
    carga.setJsonColaboracion(json);
    DonacionDinero donacion = (DonacionDinero) CargaToColaboracionMapper.colaboracionFromCarga(carga);
    assertEquals(10000, donacion.getMonto());
    assertEquals(FrecuenciaDonacion.DIARIA, donacion.getFrecuencia());
    assertEquals(LocalDate.of(2018, 12, 9), donacion.getFecha());
  }

  @Test
  @DisplayName("Carga de colaboraciones")
  void cargarColaboraciones() throws IOException {
    assertEquals(19, cargador.cargarColaboraciones().size());
    assertEquals(3, repositorio.buscarTodos().size());
    verify(mailSender, times(3)).enviarMail(any());
  }
}