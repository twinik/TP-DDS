package ar.edu.utn.frba.dds.domain.serviceLocator;

import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.IDonacionesVIandaRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesVIandaRepository;
import ar.edu.utn.frba.dds.repositories.ITarjetasRepository;
import ar.edu.utn.frba.dds.repositories.imp.ColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.imp.DonacionesVIandaRepository;
import ar.edu.utn.frba.dds.repositories.imp.HeladeraRepository;
import ar.edu.utn.frba.dds.repositories.imp.RedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.imp.TarjetaRepository;
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
