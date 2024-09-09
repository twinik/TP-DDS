package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.RegistroTemperatura;
import ar.edu.utn.frba.dds.repositories.IRegistrosTemperaturaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class RegistrosTemperaturaRepository implements IRegistrosTemperaturaRepository, WithSimplePersistenceUnit {


    @Override
    public Optional<RegistroTemperatura> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(RegistroTemperatura.class, id));
    }

    @Override
    public List<RegistroTemperatura> buscarTodos() {
        return entityManager().createQuery("from RegistroTemperatura where activo=:activo", RegistroTemperatura.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(RegistroTemperatura registroTemperatura) {
        withTransaction(() -> entityManager().persist(registroTemperatura));
    }

    public void guardar(RegistroTemperatura... registroTemperatura) {

        withTransaction(() -> {
            for (RegistroTemperatura registro : registroTemperatura) {
                entityManager().persist(registro);
            }
        });
    }

    @Override
    public void actualizar(RegistroTemperatura registroTemperatura) {
        withTransaction(() -> entityManager().merge(registroTemperatura));
    }

    @Override
    public void eliminar(RegistroTemperatura registroTemperatura) {
        registroTemperatura.borrarLogico();
        withTransaction(() -> entityManager().merge(registroTemperatura));
    }

  /*public static void main(String[] args) {
        RegistroTemperatura m = new RegistroTemperatura("otro");
        RegistroTemperatura m1 = new RegistroTemperatura("uno");
        RegistroTemperatura m2 = new RegistroTemperatura("hola");
        IRegistrosTemperaturaRepository repositorio = (IRegistrosTemperaturaRepository) ServiceLocator.get("registrosTemperaturaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<RegistroTemperatura> registroTemperatura1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<RegistroTemperatura> registroTemperatura2 = repositorio.buscar(2L);

        List<RegistroTemperatura> lista = repositorio.buscarTodos();

    }*/
}
