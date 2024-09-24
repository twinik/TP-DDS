package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.SensorMovimiento;
import ar.edu.utn.frba.dds.repositories.ISensorMovimientoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class SensoresMovimientoRepository implements ISensorMovimientoRepository, WithSimplePersistenceUnit {


    @Override
    public Optional<SensorMovimiento> buscar(String id) {
        return Optional.ofNullable(entityManager().find(SensorMovimiento.class, id));
    }

    @Override
    public List<SensorMovimiento> buscarTodos() {
        return entityManager().createQuery("from SensorMovimiento where activo=:activo", SensorMovimiento.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(SensorMovimiento sensorMovimiento) {
        withTransaction(() -> entityManager().persist(sensorMovimiento));
    }

    public void guardar(SensorMovimiento... sensorMovimiento) {

        withTransaction(() -> {
            for (SensorMovimiento sensor : sensorMovimiento) {
                entityManager().persist(sensor);
            }
        });
    }

    @Override
    public void actualizar(SensorMovimiento sensorMovimiento) {
        withTransaction(() -> entityManager().merge(sensorMovimiento));
    }

    @Override
    public void eliminar(SensorMovimiento sensorMovimiento) {
        sensorMovimiento.borrarLogico();
        withTransaction(() -> entityManager().merge(sensorMovimiento));
    }

  /*public static void main(String[] args) {
        SensorMovimiento m = new SensorMovimiento("otro");
        SensorMovimiento m1 = new SensorMovimiento("uno");
        SensorMovimiento m2 = new SensorMovimiento("hola");
        ISensorMovimientoRepository repositorio = (ISensorMovimientoRepository) ServiceLocator.get("sensorMovimientoRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<SensorMovimiento> sensorMovimiento1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<SensorMovimiento> sensorMovimiento2 = repositorio.buscar(2L);

        List<SensorMovimiento> lista = repositorio.buscarTodos();

    }*/

}
