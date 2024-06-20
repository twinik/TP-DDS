package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.SolicitudAperturaHeladera;
import java.util.List;
import java.util.Optional;

public interface ISolicitudesAperturaHeladeraRepository {
    Optional<SolicitudAperturaHeladera> buscar(int id);

    List<SolicitudAperturaHeladera> buscarTodos();

    void guardar(SolicitudAperturaHeladera solicitudAperturaHeladera);

    void actualizar(SolicitudAperturaHeladera solicitudAperturaHeladera);

    void eliminar(SolicitudAperturaHeladera solicitudAperturaHeladera);

}
