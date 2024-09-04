package ar.edu.utn.frba.dds.repositories.mainTests;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.domain.suscripciones.SuscripcionDesperfectoHeladera;
import ar.edu.utn.frba.dds.domain.suscripciones.SuscripcionViandasFaltantes;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.util.Optional;

public class MainPruebaSuscripcion {

    public static void main(String[] args) {

        IColaboradoresRepository colaboradoresRepository = ServiceLocator.get("colaboradoresRepository", IColaboradoresRepository.class);
        ISuscripcionesRepository suscripcionesRepository = ServiceLocator.get("suscripcionesRepository", ISuscripcionesRepository.class);
        IHeladerasRepository helaRepo = ServiceLocator.get("heladerasRepository", IHeladerasRepository.class);
        Colaborador c = new Colaborador();
        c.setUsuario(new Usuario("fdf", "dfdf"));

        colaboradoresRepository.guardar(c);

        colaboradoresRepository.buscar(1L);

        NotificationStrategyFactory factory = new NotificationStrategyFactory();
        Suscripcion nueva = new Suscripcion(c, factory.create(CanalContacto.TELEGRAM), new SuscripcionDesperfectoHeladera(new RecomendadorHeladeras()));

        Heladera h = new Heladera();
        h.setNombre("un_nombre");

        helaRepo.guardar(h);

        h.agregarSuscripcion(nueva);

        suscripcionesRepository.guardar(nueva);

        Optional<Suscripcion> hidratada = suscripcionesRepository.buscar(1L);

        //suscripcionesRepository.eliminar(hidratada.get());

    }


}
