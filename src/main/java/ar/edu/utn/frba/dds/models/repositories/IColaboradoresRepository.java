package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import java.util.List;
import java.util.Optional;

/**
 * IColaboradorRepository interface permite interactuar con los colaboradores.
 */
public interface IColaboradoresRepository {
    Optional<Colaborador> buscar(TipoDocumento tipoDocumento, String documento);

    Optional<Colaborador> buscar(String id);

    Optional<Colaborador> buscarPorUsuario(String idUsuario);

    Optional<Colaborador> buscarPorDni(String dni);

    List<Colaborador> buscarTodos();

    void guardar(Colaborador colaborador);

    void actualizar(Colaborador colaborador);

    void eliminar(Colaborador colaborador);

    void marcarFormCompletado(String id);
}
