package ar.edu.utn.frba.dds.cronjobs;


import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;


public class HeladerasCronJob {

    public static void main(String[] args) {
        IColaboradoresRepository colaboradoresRepository = ServiceLocator.get("colaboradoresRepository", IColaboradoresRepository.class);
        colaboradoresRepository.buscarTodos().
                forEach(colaborador -> colaborador.sumarPuntos(colaborador.getHeladerasColocadas().size() * ColocacionHeladeras.PUNTOS_POR_MES));
    }

}


/*
public class HeladerasCronJob {

  public static void main(String[] args) {
    IColaboradoresRepository colaboradoresRepository = (IColaboradoresRepository) ServiceLocator.get("ColaboradoresRepository");
    colaboradoresRepository.buscarTodos().
        forEach(colaborador -> colaborador.
            sumarPuntos(colaborador.getHeladerasColocadas().stream().
                filter(c-> c.getFecha().getDayOfMonth() == LocalDate.now().getDayOfMonth()).count() * 5.0f));
  }

}
*/
