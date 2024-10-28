package ar.edu.utn.frba.dds.cronjobs;

import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.util.List;

public class TarjetasCronJob {
    public static void main(String[] args) {
        ITarjetasRepository repository = ServiceLocator.get(ITarjetasRepository.class);
        if (repository != null) {
            List<Tarjeta> tarjetasActivas = repository.buscarTodos().stream().filter(Tarjeta::isTarjetaActiva).toList();
            tarjetasActivas.forEach(Tarjeta::resetearUsosDiarios);
            repository.actualizar(tarjetasActivas);
        }
    }
}
