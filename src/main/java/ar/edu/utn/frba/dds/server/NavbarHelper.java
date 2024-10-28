package ar.edu.utn.frba.dds.server;

import io.javalin.http.Context;
import java.util.Map;

public class NavbarHelper {
    public static void agregarAtributosSesionAlModelo(Context context, Map<String, ?> model) {
        String[] atributos = {"username", "email", "permisoTecnico", "permisoFormulario", "permisoModeloHeladera", "permisoTarjeta", "permisoCsv", "admin"};
        for (String atributo : atributos) {
            model.put(atributo, context.sessionAttribute(atributo));
        }
    }
}
