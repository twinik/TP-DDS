package ar.edu.utn.frba.dds.models.repositories.mainTests;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionDesperfectoHeladera;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.models.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.util.Optional;

public class MainPruebaSuscripcion {

    public static void main(String[] args) {

        IColaboradoresRepository colaboradoresRepository = ServiceLocator.get(IColaboradoresRepository.class);
        ISuscripcionesRepository suscripcionesRepository = ServiceLocator.get(ISuscripcionesRepository.class);
        IHeladerasRepository helaRepo = ServiceLocator.get(IHeladerasRepository.class);
        Colaborador c = new Colaborador();
        c.setUsuario(new Usuario("fdf", "dfdf"));

        colaboradoresRepository.guardar(c);

        colaboradoresRepository.buscar(c.getId());

        NotificationStrategyFactory factory = new NotificationStrategyFactory();
        Suscripcion nueva = new Suscripcion(c, null, new SuscripcionDesperfectoHeladera(new RecomendadorHeladeras()));

        Heladera h = new Heladera();
        h.setNombre("un_nombre");

        helaRepo.guardar(h);

        h.agregarSuscripcion(nueva);

        suscripcionesRepository.guardar(nueva);

        Optional<Suscripcion> hidratada = suscripcionesRepository.buscar(nueva.getId());

        //suscripcionesRepository.eliminar(hidratada.get());

    }


}
