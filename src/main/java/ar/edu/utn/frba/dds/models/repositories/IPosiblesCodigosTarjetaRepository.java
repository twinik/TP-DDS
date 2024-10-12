package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.tarjetas.PosibleCodigoTarjeta;
import java.util.List;
import java.util.Optional;

public interface IPosiblesCodigosTarjetaRepository {

    Optional<PosibleCodigoTarjeta> buscarPrimeroLibre();

    Optional<PosibleCodigoTarjeta> buscar(String id);

    Optional<PosibleCodigoTarjeta> buscarPorCodigo(String codigo);

    List<PosibleCodigoTarjeta> buscarTodos();

    void guardar(PosibleCodigoTarjeta codigo);

    void actualizar(PosibleCodigoTarjeta codigo);

    void eliminar(PosibleCodigoTarjeta codigo);
}
