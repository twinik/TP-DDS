package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.MotivoRedistribucionDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.IMotivoRedistribucionRepository;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MotivoRedistribucionService {
  private IMotivoRedistribucionRepository motivoRedistribucionRepository;

  public List<MotivoRedistribucionDto> obtenerMotivos() {
    return this.motivoRedistribucionRepository.buscarTodos().stream().map(MotivoRedistribucionDto::fromMotivo).toList();
  }

  public MotivoRedistribucionVianda obtenerMotivo(String id) {
    Optional<MotivoRedistribucionVianda> motivo = this.motivoRedistribucionRepository.buscar(id);
    if (motivo.isEmpty())
      throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Motivo de redistribucion", id));
    return motivo.get();
  }

}
