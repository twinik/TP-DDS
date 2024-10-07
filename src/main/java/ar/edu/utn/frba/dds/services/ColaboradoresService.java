package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;


@AllArgsConstructor
public class ColaboradoresService {
  private IColaboradoresRepository colaboradoresRepository;

  public Optional<Colaborador> colaboradorFromUsuario(String idUsuario) {
    return this.colaboradoresRepository.buscarPorUsuario(idUsuario);
  }

  public Colaborador obtenerColaborador(String id) {
    if (id == null) throw new RecursoInexistenteException("no existe colaborador asociado a este id");
    Optional<Colaborador> colab = this.colaboradoresRepository.buscar(id);
    if (colab.isEmpty()) throw new RecursoInexistenteException("no existe colaborador asociado a este id");
    return colab.get();
  }

  public void validarExistenciaColaborador(String id) {
    if (this.colaboradoresRepository.buscar(id).isEmpty())
      throw new RecursoInexistenteException("El colaborador no existe");
  }

}
