package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;


@AllArgsConstructor
public class ColaboradoresService {
  private IColaboradoresRepository colaboradoresRepository;

  public Colaborador colaboradorFromUsuario(String idUsuario) {
    Optional<Colaborador> colaborador = this.colaboradoresRepository.buscarPorUsuario(idUsuario);
    // TODO: si quieren agregar una excepcion con nombre propio pero es medio raro este caso
    if (colaborador.isEmpty()) throw new RuntimeException("no hay colaborador asociado a este usuario");
    return colaborador.get();
  }

}
