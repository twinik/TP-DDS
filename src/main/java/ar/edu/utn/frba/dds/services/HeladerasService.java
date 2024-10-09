package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraMapaDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HeladerasService {

  IHeladerasRepository repoHeladeras;

  public List<HeladeraMapaDto> getHeladerasParaMapa() {
    List<Heladera> heladeras = repoHeladeras.buscarTodos();
    List<HeladeraMapaDto> resultado = heladeras.stream().map(HeladeraMapaDto::fromHeladera).collect(Collectors.toList());
    return resultado;
  }

  public HeladeraDto getHeladeraDto(String id) {
    Optional<Heladera> h = repoHeladeras.buscar(id);
    if (h.isEmpty()) {
      throw new RecursoInexistenteException("La heladera no existe");
    } else {
      return HeladeraDto.fromHeladera(h.get());
    }
  }

  public Heladera obtenerHeladera(String id) {
    Optional<Heladera> h = repoHeladeras.buscar(id);
    if (h.isEmpty()) {
      throw new RecursoInexistenteException("La heladera no existe");
    } else {
      return h.get();
    }
  }
}
