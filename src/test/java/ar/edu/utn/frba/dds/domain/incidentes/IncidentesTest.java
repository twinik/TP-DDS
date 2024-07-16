package ar.edu.utn.frba.dds.domain.incidentes;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class IncidentesTest {

  private Heladera heladera;
  private Tecnico tecnico;

  private MedioDeContacto medio;

  @BeforeEach
  public void setUp() {
    heladera = new Heladera(LocalDate.now());
    heladera.setNombre("unaHeladera");
    tecnico = new Tecnico();
    tecnico.setNombre("jorge");
    tecnico.setApellido("lopez");
    ArrayList<MedioDeContacto> medios = new ArrayList<>();
    medio = new MedioDeContacto(CanalContacto.WHATSAPP,"+433434343");
    medios.add(medio);
    tecnico.setMedioContacto(medios);
  }

  @Test
  @DisplayName("Test de llamado de reporte de incidente")
  public void testInicializacionCorrecta() {
    TecnicosHelper helper = Mockito.mock(TecnicosHelper.class);
    Mockito.when(helper.findTecnicoMasCercano(any())).thenReturn(tecnico);
    NotificationStrategy strategy = Mockito.mock(NotificationStrategy.class);
    Mockito.doNothing().when(strategy).notificar(any(),any());

    NotificationStrategyFactory factory = Mockito.mock(NotificationStrategyFactory.class);
    Mockito.when(factory.create(any())).thenReturn(strategy);
    Alerta alerta = Alerta.of(heladera, LocalDateTime.now(),helper,factory,TipoAlerta.FRAUDE);
    alerta.reportar();
    verify(strategy,times(1)).notificar(eq(medio),any());

  }
}
