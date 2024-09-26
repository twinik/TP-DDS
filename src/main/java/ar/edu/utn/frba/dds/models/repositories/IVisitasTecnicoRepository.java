package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.tecnicos.VisitaTecnico;
import java.util.List;
import java.util.Optional;

/**
 * IVisitasTecnicoRepository interface permite interactuar con las visitas de un tecnico.
 */
public interface IVisitasTecnicoRepository {
    Optional<VisitaTecnico> buscar(String id);

    List<VisitaTecnico> buscarTodos();

    void guardar(VisitaTecnico visitaTecnico);

    void actualizar(VisitaTecnico visitaTecnico);

    void eliminar(VisitaTecnico visitaTecnico);
}
