package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.SolicitudAperturaHeladera;
import java.util.List;
import java.util.Optional;

public interface ISolicitudesAperturaHeladeraRepository {
    Optional<SolicitudAperturaHeladera> buscar(String id);

    List<SolicitudAperturaHeladera> buscarTodos();

    void guardar(SolicitudAperturaHeladera solicitudAperturaHeladera);

    void actualizar(SolicitudAperturaHeladera solicitudAperturaHeladera);

    void eliminar(SolicitudAperturaHeladera solicitudAperturaHeladera);

}
