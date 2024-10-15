package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.incidentes.IncidenteDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.IIncidentesRepository;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class IncidentesService {
  private IIncidentesRepository incidentesRepository;
  private HeladerasService heladerasService;

  public List<IncidenteDto> obtenerIncidentes() {
    return incidentesRepository.buscarTodos().stream().map(IncidenteDto::fromIncidente).toList();
  }

  public Incidente obtenerIncidente(String id) {
    Optional<Incidente> incidente = this.incidentesRepository.buscar(id);
    if (incidente.isEmpty())
      throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Incidente", id));
    return incidente.get();
  }

  public void actualizar(Incidente incidente) {
    this.incidentesRepository.actualizar(incidente);
  }


  public void solucionar(Incidente incidente) {
    incidente.marcarSolucionado();
    this.incidentesRepository.actualizar(incidente);
    Heladera h = incidente.getHeladera();
    if (this.incidentesRepository.cantidadNoSolucionadosPorHeladera(h) == 0L) {
      h.habilitar();
    }

    this.heladerasService.actualizarHeladera(h);
  }
}
