package ar.edu.utn.frba.dds.cronjobs;


import ar.edu.utn.frba.dds.domain.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import java.time.LocalDate;


public class HeladerasCronJob {

  public static void main(String[] args) {
    IColaboradoresRepository colaboradoresRepository = (IColaboradoresRepository) ServiceLocator.get("colaboradoresRepository");
    colaboradoresRepository.buscarTodos().
        forEach(colaborador -> colaborador.sumarPuntos(colaborador.getHeladerasColocadas().size() * 5.0f));
  }

}


//public class HeladerasCronJob {
//
//  public static void main(String[] args) {
//    IColaboradoresRepository colaboradoresRepository = (IColaboradoresRepository) ServiceLocator.get("ColaboradoresRepository");
//    colaboradoresRepository.buscarTodos().
//        forEach(colaborador -> colaborador.
//            sumarPuntos(colaborador.getHeladerasColocadas().stream().
//                filter(c-> c.getFecha().getDayOfMonth() == LocalDate.now().getDayOfMonth()).count() * 5.0f));
//  }
//
//}
