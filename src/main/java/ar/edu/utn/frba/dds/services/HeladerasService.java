package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraMapaDto;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeNoAutorizadoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
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

  public List<HeladeraMapaDto> getHeladerasParaDonar() {
    List<Heladera> heladeras = repoHeladeras.buscarTodos().stream().filter(Heladera::isHeladeraActiva).toList();
    return heladeras.stream().map(HeladeraMapaDto::fromHeladera).toList();
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
      throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Heladera", id));
    } else {
      return h.get();
    }
  }

  public void agregarSuscripcionAHeladera(Heladera h, Suscripcion s) {
    h.agregarSuscripcion(s);
    this.repoHeladeras.actualizar(h);
  }

  public void actualizarHeladera(Heladera heladera) {
    this.repoHeladeras.actualizar(heladera);
  }

  public void actualizarHeladera(HeladeraDto dto, String idColaborador) {
    Heladera heladera = this.obtenerHeladera(dto.getId());
    if (idColaborador == null) throw new NoAutorizadoException(MensajeNoAutorizadoFactory.generarMensaje());
    this.validarPermisosHeladera(dto.getId(), idColaborador);
    if (dto.getNombre() != null) heladera.setNombre(dto.getNombre());
    if (dto.getActiva() != null) heladera.setHeladeraActiva(dto.getActiva());
    this.repoHeladeras.actualizar(heladera);
  }

  public void eliminarHeladera(String idHeladera, String idColaborador) {
    Heladera h = this.obtenerHeladera(idHeladera);
    this.validarPermisosHeladera(idHeladera, idColaborador);
    this.repoHeladeras.eliminar(h);
  }

  private void validarPermisosHeladera(String idHeladera, String idColaborador) {
    if (this.repoHeladeras.buscarPorColaborador(idColaborador).stream().noneMatch(hel -> hel.getId().equals(idHeladera)))
      throw new NoAutorizadoException(MensajeNoAutorizadoFactory.generarMensaje());
  }

}
