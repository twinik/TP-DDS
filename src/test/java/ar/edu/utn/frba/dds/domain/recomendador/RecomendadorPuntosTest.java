package ar.edu.utn.frba.dds.domain.recomendador;

import ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion.ListadoUbicaciones;
import ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion.RecomendadorDePuntosDeColocacion;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class RecomendadorPuntosTest {

Ubicacion ubicacion;
@BeforeEach
void initTest(){
  ubicacion = new Ubicacion(1.0f,1.0f);
}

  @Test
  @DisplayName("pruebo que lo que me devuelve la mock api sea correcto")
  void obtenerPuntos() throws IOException {

    ListadoUbicaciones listado = RecomendadorDePuntosDeColocacion.getInstance().recomendarUbicacion(ubicacion,1f);
    Assertions.assertEquals(3,listado.getListadoUbicaciones().size());
    Assertions.assertEquals(12,listado.getListadoUbicaciones().get(0).getLongitud());
    Assertions.assertEquals(10,listado.getListadoUbicaciones().get(0).getLatitud());
    Assertions.assertEquals(17,listado.getListadoUbicaciones().get(1).getLongitud());
    Assertions.assertEquals(5,listado.getListadoUbicaciones().get(1).getLatitud());
    Assertions.assertEquals(2,listado.getListadoUbicaciones().get(2).getLongitud());
    Assertions.assertEquals(8,listado.getListadoUbicaciones().get(2).getLatitud());
  }
}
