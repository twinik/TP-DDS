package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.SensorTemperatura;
import ar.edu.utn.frba.dds.repositories.ISensorTemperaturaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SensoresTemperaturaRepository implements ISensorTemperaturaRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<SensorTemperatura> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(SensorTemperatura.class,id));
  }

  @Override
  public List<SensorTemperatura> buscarTodos() {
    return entityManager().createQuery("from SensorTemperatura where activo=:activo",SensorTemperatura.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(SensorTemperatura sensorTemperatura) {
    withTransaction(() -> entityManager().persist(sensorTemperatura));
  }
  public void guardar(SensorTemperatura ...sensorTemperatura) {

    withTransaction(() -> {
      for (SensorTemperatura sensor : sensorTemperatura){
        entityManager().persist(sensor);
      }
    });
  }

  @Override
  public void actualizar(SensorTemperatura sensorTemperatura) {
    withTransaction(() -> entityManager().merge(sensorTemperatura));
  }

  @Override
  public void eliminar(SensorTemperatura sensorTemperatura) {
    sensorTemperatura.setActivo(false);
    withTransaction(() -> entityManager().merge(sensorTemperatura));
  }

  /*public static void main(String[] args) {
        SensorTemperatura m = new SensorTemperatura("otro");
        SensorTemperatura m1 = new SensorTemperatura("uno");
        SensorTemperatura m2 = new SensorTemperatura("hola");
        ISensorTemperaturaRepository repositorio = (ISensorTemperaturaRepository) ServiceLocator.get("sensorTemperaturaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<SensorTemperatura> sensorTemperatura1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<SensorTemperatura> sensorTemperatura2 = repositorio.buscar(2L);

        List<SensorTemperatura> lista = repositorio.buscarTodos();

    }*/

}
