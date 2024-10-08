package ar.edu.utn.frba.dds.externapi;

import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import java.io.IOException;
import java.util.List;

public class MainDonaciones {
    public static void main(String[] args) throws IOException {

        RecomendacionDonaciones recomendadorDonaciones = new RecomendacionDonaciones();

        IRecomendacionDonacionesAdapter adapter = RecomendadorDonacionesRetrofitAdapter.getInstance();

        recomendadorDonaciones.setAdapter(adapter);

        String id = "3aa93e52-85b1-11ef-bb7f-9c6b0047e007";
        Recomendacion recomendacion = recomendadorDonaciones.obtenerRecomendacion(id);


        System.out.println("ID: " + recomendacion.getId());
        System.out.println("Nombre: " + recomendacion.getNombre());
        System.out.println("Calle: " + recomendacion.getCalle());
        System.out.println("Altura: " + recomendacion.getAltura());
        System.out.println("Latitud: " + recomendacion.getLatitud());
        System.out.println("Longitud: " + recomendacion.getLongitud());
        System.out.println("Provincia: " + recomendacion.getProvincia().getNombre());
        System.out.println("Localidad: " + recomendacion.getLocalidad().getNombre());

        String etiquetaLocalidad = "bajoFlores";
        String etiquetaProvincia = "caba";

        List<Recomendacion> recomendaciones = recomendadorDonaciones.listarRecomendaciones(etiquetaProvincia, etiquetaLocalidad);

        for (Recomendacion r : recomendaciones) {
            System.out.println("ID: " + r.getId());
            System.out.println("Nombre: " + r.getNombre());
            System.out.println("Calle: " + r.getCalle());
            System.out.println("Altura: " + r.getAltura());
            System.out.println("Latitud: " + r.getLatitud());
            System.out.println("Longitud: " + r.getLongitud());
            System.out.println("Provincia: " + r.getProvincia().getNombre());
            System.out.println("Localidad: " + r.getLocalidad().getNombre());
        }
    }
}