package ar.edu.utn.frba.dds.domain.cronjobs;

import ar.edu.utn.frba.dds.domain.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.repositories.ITarjetasRepository;

public class TarjetasCronJob {
  public static void main(String[] args) {
    ITarjetasRepository repository = (ITarjetasRepository) ServiceLocator.get("tarjetasRepository");
    if (repository != null) {
      repository.resetearUsosDiarios();
    }
  }
}
