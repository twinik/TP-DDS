package ar.edu.utn.frba.dds.domain.serviceLocator;

import java.util.HashMap;
import java.util.Map;

/**
 * ServiceLocator utilizado para obtener repositorios
 */
public class ServiceLocator {

  private static final Map<String, Object> services = new HashMap<>();

  public static void add(String nombre,Object service){
    services.put(nombre,service);
  }

  public static Object get(String nombre) {
    return services.get(nombre);
  }

}
