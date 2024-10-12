package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.incidentes.IncidenteDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.IIncidentesRepository;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class IncidentesService {
  private IIncidentesRepository incidentesRepository;

  public List<IncidenteDto> obtenerIncidentes() {
    return incidentesRepository.buscarTodos().stream().map(IncidenteDto::fromIncidente).toList();
  }

  public Incidente obtenerIncidente(String id){
    Optional<Incidente> incidente = this.incidentesRepository.buscar(id);
    if(incidente.isEmpty()) throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Incidente", id));
    return incidente.get();
  }


}
