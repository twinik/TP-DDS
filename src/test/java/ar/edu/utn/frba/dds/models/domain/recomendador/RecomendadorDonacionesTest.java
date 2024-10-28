package ar.edu.utn.frba.dds.models.domain.recomendador;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.externapi.IRecomendacionDonacionesAdapter;
import ar.edu.utn.frba.dds.externapi.RecomendacionDonaciones;
import ar.edu.utn.frba.dds.externapi.recomendaciones.Localidad;
import ar.edu.utn.frba.dds.externapi.recomendaciones.Provincia;
import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecomendadorDonacionesTest {

  String id;
  Recomendacion recomendacionHardcodeada;

  @BeforeEach
  void initTest() {
    id = "3a7502ae-7185-11ef-98ce-3c7c3f266695";
    recomendacionHardcodeada = new Recomendacion();
    recomendacionHardcodeada.setId(id);
    recomendacionHardcodeada.setNombre("punto2");
    recomendacionHardcodeada.setCalle("Av. Cordoba");
    recomendacionHardcodeada.setAltura(4857);
    recomendacionHardcodeada.setLatitud("5468968245");
    recomendacionHardcodeada.setLongitud("5643212543");
    recomendacionHardcodeada.setProvincia(new Provincia(1, "Ciudad Autonoma de Buenos Aires", "caba"));
    recomendacionHardcodeada.setLocalidad(new Localidad(1, "Almagro", "almagro"));
  }

  @Test
  @DisplayName("pruebo que lo que me devuelve la mock api sea correcto")
  void test() {
    RecomendacionDonaciones recomendadorDonaciones = new RecomendacionDonaciones();
    IRecomendacionDonacionesAdapter adapter = mock(IRecomendacionDonacionesAdapter.class);
    when(adapter.obtenerRecomendacion(any())).thenReturn(recomendacionHardcodeada);
    recomendadorDonaciones.setAdapter(adapter);
    Recomendacion recomendacion = recomendadorDonaciones.obtenerRecomendacion(id);
    Assertions.assertEquals(id, recomendacion.getId());
    Assertions.assertEquals("punto2", recomendacion.getNombre());
    Assertions.assertEquals("Av. Cordoba", recomendacion.getCalle());
    Assertions.assertEquals(4857, recomendacion.getAltura());
    Assertions.assertEquals("5468968245", recomendacion.getLatitud());
    Assertions.assertEquals("5643212543", recomendacion.getLongitud());
    Assertions.assertEquals(1, recomendacion.getProvincia().getId());
    Assertions.assertEquals("Ciudad Autonoma de Buenos Aires", recomendacion.getProvincia().getNombre());
    Assertions.assertEquals("caba", recomendacion.getProvincia().getEtiqueta());
    Assertions.assertEquals(1, recomendacion.getLocalidad().getId());
    Assertions.assertEquals("Almagro", recomendacion.getLocalidad().getNombre());
    Assertions.assertEquals("almagro", recomendacion.getLocalidad().getEtiqueta());
  }
}
