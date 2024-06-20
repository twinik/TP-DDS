package ar.edu.utn.frba.dds.domain.suscripciones;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuscripcionVIandasRestantesTest {





  @Test
  @DisplayName("pruebo que lo que me devuelve la mock api sea correcto")
  void mandarNotificacion(){
    Suscripcion sucripcion;
    NotificationStrategy strategy = Mockito.mock(NotificationStrategy.class);
    doNothing().when(strategy).notificar(any(),any());
    sucripcion = new Suscripcion(new Colaborador(),
        strategy,new SuscripcionViandasRestantes(),2,
        new MedioDeContacto(CanalContacto.EMAIL,"mail"));

    List<Vianda> viandas = new ArrayList<>();

    Vianda vianda1 = new Vianda();
    viandas.add(vianda1);
    viandas.add(new Vianda());
    viandas.add(new Vianda());

    Heladera heladera = new Heladera(LocalDate.now());
    heladera.setViandas(viandas);
    heladera.agregarSuscripcion(sucripcion);
    heladera.quitarVianda(vianda1);

    verify(strategy,times(1)).notificar(any(),any());
  }
}
