package ar.edu.utn.frba.dds.models.domain.incidentes;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class IncidentesTest {

  private Heladera heladera;
  private Tecnico tecnico;

  private MedioDeContacto medio;

  private LocalDateTime fecha;

  private String output;

  @BeforeEach
  public void setUp() {
    this.heladera = new Heladera(LocalDate.now());
    this.heladera.setNombre("unaHeladera");
    this.tecnico = new Tecnico();
    this.tecnico.setNombre("jorge");
    this.tecnico.setApellido("lopez");
    ArrayList<MedioDeContacto> medios = new ArrayList<>();
    this.medio = new MedioDeContacto(CanalContacto.WHATSAPP, "+433434343");
    medios.add(medio);
    this.tecnico.setMedioContacto(medios);
    this.fecha = LocalDateTime.now();
  }

  @Test
  @DisplayName("Test de llamado de reporte de incidente, se debe notificar al tecnico y generar el mensaje adecuado")
  public void testInicializacionCorrecta() {
    TecnicosHelper helper = Mockito.mock(TecnicosHelper.class);
    Mockito.when(helper.findTecnicoMasCercano(any())).thenReturn(tecnico);
    NotificationStrategy strategy = Mockito.mock(NotificationStrategy.class);
    Mockito.doNothing().when(strategy).notificar(any(), any(), any());

    NotificationStrategyFactory factory = Mockito.mock(NotificationStrategyFactory.class);
    Mockito.when(factory.create(any())).thenReturn(strategy);
    Alerta alerta = Alerta.of(heladera, this.fecha, helper, factory, TipoAlerta.FRAUDE);
    alerta.reportar();
    this.output = "Estimado jorge, la heladera unaHeladera se ha averiado a las " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")) + ". Le solicitamos amablemente que acuda a repararla a la brevedad posible.";
    verify(strategy, times(1)).notificar(this.tecnico, "Se solicita una visita para arreglar heladera", this.output);

  }
}
