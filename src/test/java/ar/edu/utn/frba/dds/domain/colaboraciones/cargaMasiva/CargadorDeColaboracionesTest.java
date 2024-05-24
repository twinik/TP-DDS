package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;

class CargadorDeColaboracionesTest {
  private CargaColaboracionCsvReader csvReader;
  private MailSender mailSender;
  private CargadorDeColaboraciones cargador;

  @BeforeEach
  void setUp() throws IOException {
    csvReader = new CargaColaboracionCsvReader();
    mailSender = new MailSender();
    cargador = new CargadorDeColaboraciones(csvReader, mailSender);
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
    assertEquals(7, cargador.cargarColaboraciones().size());
  }
}