package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.personas.FormaColaboracionDto;
import ar.edu.utn.frba.dds.dtos.personas.FormaColaboracionOutputDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.repositories.IFormasColaboracionRespository;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class FormaColaboracionService {
  private IFormasColaboracionRespository formasColaboracionRespository;

  public List<FormaColaboracionOutputDto> obtenerFormas(String... nombres) {
    List<FormaColaboracionOutputDto> result = new ArrayList<>();
    for (String nombre : nombres) {
      Optional<FormaColaboracion> forma = this.formasColaboracionRespository.buscarPorNombre(nombre);
      if (forma.isEmpty()) throw new RuntimeException("nombre invalido de forma de colaboracion");
      result.add(FormaColaboracionOutputDto.fromForma(forma.get()));
    }
    return result;
  }

  public FormaColaboracion obtenerForma(FormaColaboracionDto formaColaboracionDto) {
    Optional<FormaColaboracion> formaColaboracion = this.formasColaboracionRespository.buscar(formaColaboracionDto.getId());
    if (formaColaboracion.isEmpty())
      throw new RecursoInexistenteException("No existe forma de colaboracion asociada a este id");
    return formaColaboracion.get();
  }

  public List<FormaColaboracion> fromDtos(List<FormaColaboracionDto> formaColaboracionDtos) {
    return formaColaboracionDtos.stream().map(this::obtenerForma).toList();
  }

}
