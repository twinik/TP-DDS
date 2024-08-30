package ar.edu.utn.frba.dds.cronjobs;

import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.repositories.ITarjetasRepository;

public class TarjetasCronJob {
  public static void main(String[] args) {
    ITarjetasRepository repository = ServiceLocator.get("tarjetasRepository", ITarjetasRepository.class);
    if (repository != null) {
      repository.buscarTodos().stream().filter(Tarjeta::isActiva).forEach(Tarjeta::resetearUsosDiarios);
    }
  }
}
