package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.domain.emailSending.SendGridMailSender;
import ar.edu.utn.frba.dds.repositories.IFormasColaboracionRespository;
import ar.edu.utn.frba.dds.repositories.imp.ColaboradoresRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

class CargadorDeColaboracionesTest {
  private CargaColaboracionCsvReader csvReader;
  private SendGridMailSender mailSender;
  private CargadorDeColaboraciones cargador;

  private ColaboradoresRepository repositorio;
  private IFormasColaboracionRespository formasColaboracionRespository;
  @BeforeEach
  void setUp() throws IOException {
    csvReader = new CargaColaboracionCsvReader();
    mailSender = mock(SendGridMailSender.class);
    doNothing().when(mailSender).enviarMail(any());
    repositorio = new ColaboradoresRepository();
    formasColaboracionRespository = mock(IFormasColaboracionRespository.class);
    when(formasColaboracionRespository.buscar("DONACION_DINERO")).thenReturn(Optional.of(new FormaColaboracion(1L, "DONACION_DINERO")));
    when(formasColaboracionRespository.buscar("DONACION_VIANDA")).thenReturn(Optional.of(new FormaColaboracion(2L, "DONACION_VIANDA")));
    when(formasColaboracionRespository.buscar("REGISTRO_PERSONA")).thenReturn(Optional.of(new FormaColaboracion(3L, "REGISTRO_PERSONA")));
    when(formasColaboracionRespository.buscar("REDISTRIBUCION_VIANDA")).thenReturn(Optional.of(new FormaColaboracion(4L, "REDISTRIBUCION_VIANDA")));
    cargador = new CargadorDeColaboraciones("src/main/java/ar/edu/utn/frba/dds/domain/assets/cargacolaboraciones.csv", csvReader, mailSender,repositorio,formasColaboracionRespository, (ICalculadorPuntos) ServiceLocator.get("calculadorPuntos"));
  }

  @Test
  void pruebaJsonToDonacionDinero() throws IOException {
    String json = "{\"monto\": 10000, \"frecuencia\": \"DIARIA\", \"fecha\": \"09/12/2018\"}";
    CargaColaboracion carga = new CargaColaboracion();
    carga.setFormaColaboracion("DONACION_DINERO");
    carga.setJsonColaboracion(json);
    DonacionDinero donacion = (DonacionDinero) CargaToColaboracionMapper.colaboracionFromCarga(carga, new Colaborador());
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