package ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.IOException;

/**
 * RecomendadorDePuntosDeColocacion class permite recomendar puntos de colocacion.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecomendadorDePuntosDeColocacion {

    private RecomendadorDePuntosAdapter recomendadorDePuntosAdapter;

    /**
     * Recomienda una ubicacion.
     */
    public ListadoUbicaciones recomendarUbicacion(Ubicacion punto, Float radio) {
        return this.recomendadorDePuntosAdapter.recomendarUbicacion(punto, radio);
    }

    public static void main(String[] args) throws IOException {
        RecomendadorDePuntosDeColocacion recomendadorDePuntosDeColocacion  = new RecomendadorDePuntosDeColocacion();
        recomendadorDePuntosDeColocacion.setRecomendadorDePuntosAdapter(RecomendadorRetrofitAdapter.getInstance());
        ListadoUbicaciones listadoUbicaciones = recomendadorDePuntosDeColocacion.recomendarUbicacion(new Ubicacion(30f,50f),2f);
        System.out.println(listadoUbicaciones);
    }
}