package ar.edu.utn.frba.dds.domain.serviceLocator;

import ar.edu.utn.frba.dds.repositories.imp.AlertasRepository;
import ar.edu.utn.frba.dds.repositories.imp.AperturasHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.imp.ColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.imp.DonacionesVIandaRepository;
import ar.edu.utn.frba.dds.repositories.imp.FallasTecnicasRepository;
import ar.edu.utn.frba.dds.repositories.imp.HeladeraRepository;
import ar.edu.utn.frba.dds.repositories.imp.RedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.imp.SolcitudesAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.imp.TarjetaRepository;
import ar.edu.utn.frba.dds.repositories.imp.TarjetasColaboradorRepository;
import ar.edu.utn.frba.dds.repositories.imp.ViandasRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * ServiceLocator utilizado para obtener repositorios
 */
public class ServiceLocator {

  private static final Map<String, Object> services = new HashMap<>();

  public static void add(String nombre, Object service) {
    services.put(nombre, service);
  }

  public static Object get(String nombre) {
    if (!existeService(nombre)) {
      switch (nombre) {
        case "colaboradoresRepository" -> add("colaboradoresRepository", new ColaboradoresRepository());
        case "heladerasRepository" -> add("heladerasRepository", new HeladeraRepository());
        case "donacionesViandaRepository" -> add("donacionesViandaRepository", new DonacionesVIandaRepository());
        case "redistribucionesViandaRepository" ->
            add("redistribucionesViandaRepository", new RedistribucionesViandaRepository());
        case "alertasRepository" -> add("alertasRepository", new AlertasRepository());
        case "fallasTecnicasRepository" -> add("fallasTecnicasRepository", new FallasTecnicasRepository());
        case "tarjetasRepository" -> add("tarjetasRepository", new TarjetaRepository());
        case "viandasRepository" -> add("viandasRepository", new ViandasRepository());
        case "solicitudesAperturaHeladeraRepository" -> add("solicitudesAperturaHeladeraRepository", new SolcitudesAperturaHeladeraRepository());
        case "tarjetasColaboradorRepository" -> add("tarjetasColaboradorRepository", new TarjetasColaboradorRepository());
        case "aperturasHeladeraRepository" -> add("aperturasHeladeraRepository", new AperturasHeladeraRepository());
      }
    }

    return services.get(nombre);
  }

  private static boolean existeService(String nombre) {
    return services.containsKey(nombre);
  }

}
