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
    this.repoHeladeras.refresh(heladeras);
    List<HeladeraMapaDto> resultado = heladeras.stream().map(HeladeraMapaDto::fromHeladera).toList();
    return resultado;
  }

  public List<HeladeraMapaDto> getHeladerasParaDonar() {
    List<Heladera> heladeras = repoHeladeras.buscarTodos().stream().filter(Heladera::isHeladeraActiva).toList();
    this.repoHeladeras.refresh(heladeras);
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
      this.repoHeladeras.refresh(h.get());
      return h.get();
    }
  }

  public List<HeladeraDto> obtenerHeladeras() {
    return this.repoHeladeras.buscarTodos().stream().map(HeladeraDto::fromHeladera).toList();
  }

  public void agregarSuscripcionAHeladera(Heladera h, Suscripcion s) {
    h.agregarSuscripcion(s);
    this.repoHeladeras.actualizar(h);
  }

  public void actualizarHeladera(Heladera heladera) {
    this.repoHeladeras.actualizar(heladera);
  }

  public void refresh(Heladera heladera){
    this.repoHeladeras.refresh(heladera);
  }

  public void actualizarHeladera(HeladeraDto dto, String idColaborador) {
    Heladera heladera = this.obtenerHeladera(dto.getId());
    if (idColaborador == null) throw new NoAutorizadoException(MensajeNoAutorizadoFactory.generarMensaje());
    this.validarPermisosHeladera(dto.getId(), idColaborador);
    if (dto.getNombre() != null) heladera.setNombre(dto.getNombre());
    this.repoHeladeras.actualizar(heladera);
    this.repoHeladeras.refresh(heladera);
  }

  public void eliminarHeladera(String idHeladera, String idColaborador) {
    Heladera h = this.obtenerHeladera(idHeladera);
    this.validarPermisosHeladera(idHeladera, idColaborador);
    this.repoHeladeras.eliminar(h);
    this.repoHeladeras.refresh(h);
  }

  private void validarPermisosHeladera(String idHeladera, String idColaborador) {
    if (this.repoHeladeras.buscarPorColaborador(idColaborador).stream().noneMatch(hel -> hel.getId().equals(idHeladera)))
      throw new NoAutorizadoException(MensajeNoAutorizadoFactory.generarMensaje());
  }

  public List<HeladeraDto> obtenerHeladerasConAlerta() {
    List<Heladera> heladeras = this.repoHeladeras.buscarConAlertas();
    return heladeras.stream().map(HeladeraDto::fromHeladera).collect(Collectors.toList());
  }

  public List<HeladeraDto> obtenerHeladerasColaborador(String id) {
    List<Heladera> heladeras = this.repoHeladeras.buscarPorColaborador(id);
    this.repoHeladeras.refresh(heladeras);
    return heladeras.stream().map(HeladeraDto::fromHeladera).toList();
  }

  public void detach(Heladera h){
    this.repoHeladeras.detach(h);
  }

}
