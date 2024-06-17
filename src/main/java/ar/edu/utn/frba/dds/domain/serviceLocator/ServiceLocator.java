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

/**
 * ServiceLocator utilizado para obtener repositorios
 */
public class ServiceLocator {

  private static final IColaboradoresRepository colaboradoresRepository = new ColaboradoresRepository();
  private static final ITarjetasRepository tarjetasRepostory = new TarjetaRepository();

  private static final IHeladerasRepository heladerasRepository = new HeladeraRepository();

  private static final IDonacionesVIandaRepository donacionesViandaRepository = new DonacionesVIandaRepository();

  private static final IRedistribucionesVIandaRepository redistribucionesViandaRepository = new RedistribucionesViandaRepository();


  public static Object get(String nombre) {
    switch (nombre) {
      case "colaboradoresRepository" -> {
        return colaboradoresRepository;
      }
      case "tarjetasRepository" -> {
        return tarjetasRepostory;
      }
      case "heladerasRepository" -> {
        return heladerasRepository;
      }
      case "donacionesViandaRepository" -> {
        return donacionesViandaRepository;
      }
      case "redistribucionesViandaRepository" -> {
        return redistribucionesViandaRepository;
      }
      default -> {
        return null;
      }
    }
  }

}
