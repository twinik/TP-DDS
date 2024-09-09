package ar.edu.utn.frba.dds.domain.recomendador;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion.ListadoUbicaciones;
import ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion.RecomendadorDePuntosAdapter;
import ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion.RecomendadorDePuntosDeColocacion;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class RecomendadorPuntosTest {

    Ubicacion ubicacion;
    ListadoUbicaciones listaHarcodeada;

    @BeforeEach
    void initTest() {

        ubicacion = new Ubicacion(1.0f, 1.0f);
        listaHarcodeada = new ListadoUbicaciones();
        ArrayList<Ubicacion> ubicaciones = new ArrayList<>();
        ubicaciones.add(new Ubicacion(10f, 12f));
        ubicaciones.add(new Ubicacion(5f, 17f));
        ubicaciones.add(new Ubicacion(8f, 2f));
        listaHarcodeada.setListadoUbicaciones(ubicaciones);
    }

    @Test
    @DisplayName("pruebo que lo que me devuelve la mock api sea correcto")
    void obtenerPuntos() {
        RecomendadorDePuntosDeColocacion recomendadorDePuntosDeColocacion = new RecomendadorDePuntosDeColocacion();
        RecomendadorDePuntosAdapter adapter = mock(RecomendadorDePuntosAdapter.class);
        when(adapter.recomendarUbicacion(any(), any())).thenReturn(listaHarcodeada);
        recomendadorDePuntosDeColocacion.setRecomendadorDePuntosAdapter(adapter);
        ListadoUbicaciones listado = recomendadorDePuntosDeColocacion.recomendarUbicacion(ubicacion, 1f);
        Assertions.assertEquals(3, listado.getListadoUbicaciones().size());
        Assertions.assertEquals(12, listado.getListadoUbicaciones().get(0).getLongitud());
        Assertions.assertEquals(10, listado.getListadoUbicaciones().get(0).getLatitud());
        Assertions.assertEquals(17, listado.getListadoUbicaciones().get(1).getLongitud());
        Assertions.assertEquals(5, listado.getListadoUbicaciones().get(1).getLatitud());
        Assertions.assertEquals(2, listado.getListadoUbicaciones().get(2).getLongitud());
        Assertions.assertEquals(8, listado.getListadoUbicaciones().get(2).getLatitud());
    }
}
