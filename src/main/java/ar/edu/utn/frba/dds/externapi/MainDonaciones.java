package ar.edu.utn.frba.dds.externapi;

import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import java.io.IOException;
import java.util.List;

public class MainDonaciones {
    public static void main(String[] args) throws IOException {

        RecomendacionDonaciones recomendadorDonaciones = new RecomendacionDonaciones();

        IRecomendacionDonacionesAdapter adapter = RecomendadorDonacionesRetrofitAdapter.getInstance();

        recomendadorDonaciones.setAdapter(adapter);

        String id = "32d292bf-8677-11ef-87fb-9c6b0047e007";
        Recomendacion recomendacion = recomendadorDonaciones.obtenerRecomendacion(id);

        mostrarRecomendacion(recomendacion);

        String etiquetaLocalidad = "bajoFlores";
        String etiquetaProvincia = "caba";

        List<Recomendacion> recomendaciones = recomendadorDonaciones.listarRecomendaciones(etiquetaProvincia, etiquetaLocalidad);

        for (Recomendacion r : recomendaciones) {
            mostrarRecomendacion(r);
        }
    }

    private static void mostrarRecomendacion(Recomendacion recomendacion) {
        System.out.println("ID: " + recomendacion.getId());
        System.out.println("Nombre: " + recomendacion.getNombre());
        System.out.println("Calle: " + recomendacion.getCalle());
        System.out.println("Altura: " + recomendacion.getAltura());
        System.out.println("Latitud: " + recomendacion.getLatitud());
        System.out.println("Longitud: " + recomendacion.getLongitud());
        System.out.println("Provincia: " + recomendacion.getProvincia().getNombre());
        System.out.println("Localidad: " + recomendacion.getLocalidad().getNombre());
        System.out.println();
    }
}