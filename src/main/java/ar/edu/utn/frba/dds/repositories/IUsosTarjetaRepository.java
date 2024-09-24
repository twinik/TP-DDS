package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.tarjetas.UsoTarjeta;
import java.util.List;
import java.util.Optional;

/**
 * IUsosTarjetaRepository interface permite interactuar con los Usos de las Tarjetas.
 */
public interface IUsosTarjetaRepository {
    Optional<UsoTarjeta> buscar(String id);

    List<UsoTarjeta> buscarTodos();

    void guardar(UsoTarjeta usoTarjeta);

    void actualizar(UsoTarjeta usoTarjeta);

    void eliminar(UsoTarjeta usoTarjeta);
}
