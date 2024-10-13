package ar.edu.utn.frba.dds.cronjobs;


import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.util.List;


public class HeladerasCronJob {

  public static void main(String[] args) {
    IColaboradoresRepository colaboradoresRepository = ServiceLocator.get(IColaboradoresRepository.class);
    List<Colaborador> todos = colaboradoresRepository.buscarTodos().stream().filter(c ->!c.getHeladerasColocadas().isEmpty()).toList();
    todos.forEach(colaborador -> colaborador.sumarPuntos(colaborador.getHeladerasColocadas().size() * ColocacionHeladeras.PUNTOS_POR_MES));
    colaboradoresRepository.actualizar(todos);
  }

}

