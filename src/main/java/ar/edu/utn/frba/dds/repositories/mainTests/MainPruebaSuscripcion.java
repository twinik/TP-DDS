package ar.edu.utn.frba.dds.repositories.mainTests;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.domain.suscripciones.SuscripcionDesperfectoHeladera;
import ar.edu.utn.frba.dds.domain.suscripciones.SuscripcionViandasFaltantes;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;

public class MainPruebaSuscripcion {

  public static void main(String[] args) {

    IColaboradoresRepository colaboradoresRepository = (IColaboradoresRepository) ServiceLocator.get("colaboradoresRepository");
    ISuscripcionesRepository suscripcionesRepository = (ISuscripcionesRepository) ServiceLocator.get("suscripcionesRepository");

    Colaborador c = new Colaborador();
    c.setUsuario(new Usuario("fdf","dfdf"));

    colaboradoresRepository.guardar(c);

    colaboradoresRepository.buscar(1L);

    NotificationStrategyFactory factory = new NotificationStrategyFactory();
    Suscripcion nueva = new Suscripcion(c,factory.create(CanalContacto.TELEGRAM), new SuscripcionDesperfectoHeladera(new RecomendadorHeladeras()));

    suscripcionesRepository.guardar(nueva);

  }


}
