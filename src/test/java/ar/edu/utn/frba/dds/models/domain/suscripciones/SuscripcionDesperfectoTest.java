package ar.edu.utn.frba.dds.models.domain.suscripciones;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.models.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionDesperfectoHeladera;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuscripcionDesperfectoTest {

    @Test
    @DisplayName("pruebo que notifique el observer ante evento")
    void mandarNotificacion() {
        Suscripcion sucripcion;
        RecomendadorHeladeras recomendadorHeladeras = Mockito.mock(RecomendadorHeladeras.class);

        NotificationStrategy strategy = Mockito.mock(NotificationStrategy.class);
        doNothing().when(strategy).notificar(any(), any());

        sucripcion = new Suscripcion(new Colaborador(),
                strategy, new SuscripcionDesperfectoHeladera(recomendadorHeladeras), 2);


        Heladera heladera = new Heladera(LocalDate.now());
        heladera.setHeladeraActiva(true);
        heladera.agregarSuscripcion(sucripcion);
        heladera.inhabilitar();


        verify(strategy, times(1)).notificar(any(), any());
    }

    @Test
    @DisplayName("pruebo que no notifque el observer cuando no debe")
    void noMandaNada() {
        Suscripcion sucripcion;
        RecomendadorHeladeras recomendadorHeladeras = Mockito.mock(RecomendadorHeladeras.class);
        NotificationStrategy strategy = Mockito.mock(NotificationStrategy.class);
        doNothing().when(strategy).notificar(any(), any());
        sucripcion = new Suscripcion(new Colaborador(),
                strategy, new SuscripcionDesperfectoHeladera(recomendadorHeladeras), 2);

        List<Vianda> viandas = new ArrayList<>();

        viandas.add(new Vianda());

        Heladera heladera = new Heladera(LocalDate.now());
        heladera.setCapacidadViandas(5);
        heladera.agregarSuscripcion(sucripcion);
        heladera.setViandas(viandas);
        heladera.agregarVianda(new Vianda());


        verify(strategy, times(0)).notificar(any(), any());
    }
}
