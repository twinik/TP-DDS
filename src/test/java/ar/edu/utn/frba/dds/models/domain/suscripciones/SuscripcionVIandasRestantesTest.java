package ar.edu.utn.frba.dds.models.domain.suscripciones;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuscripcionVIandasRestantesTest {


    @Test
    @DisplayName("pruebo que notifique cuando corresponde")
    void mandarNotificacion() {
        Suscripcion sucripcion;
        NotificationStrategy strategy = Mockito.mock(NotificationStrategy.class);
        doNothing().when(strategy).notificar(any(), any());
        sucripcion = new Suscripcion(new Colaborador(),
                strategy, new SuscripcionViandasRestantes(), 2);

        List<Vianda> viandas = new ArrayList<>();

        Vianda vianda1 = new Vianda();
        viandas.add(vianda1);
        viandas.add(new Vianda());
        viandas.add(new Vianda());

        Heladera heladera = new Heladera(LocalDate.now());
        heladera.setViandas(viandas);
        heladera.agregarSuscripcion(sucripcion);
        heladera.quitarVianda(vianda1);

        verify(strategy, times(1)).notificar(any(), any());
    }

    @Test
    @DisplayName("pruebo que no notifique cuando corresopnda")
    void noMandaNada() {
        Suscripcion sucripcion;
        NotificationStrategy strategy = Mockito.mock(NotificationStrategy.class);
        doNothing().when(strategy).notificar(any(), any());
        sucripcion = new Suscripcion(new Colaborador(),
                strategy, new SuscripcionViandasRestantes(), 1);

        List<Vianda> viandas = new ArrayList<>();

        Vianda vianda1 = new Vianda();
        viandas.add(vianda1);
        viandas.add(new Vianda());
        viandas.add(new Vianda());

        Heladera heladera = new Heladera(LocalDate.now());
        heladera.setViandas(viandas);
        heladera.agregarSuscripcion(sucripcion);
        heladera.quitarVianda(vianda1);

        verify(strategy, times(0)).notificar(any(), any());
    }
}
