package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.models.repositories.IModeloHeladeraRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class ModeloHeladeraRepository implements IModeloHeladeraRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<ModeloHeladera> buscar(String id) {
        return Optional.ofNullable(entityManager().find(ModeloHeladera.class, id));
    }

    @Override
    public List<ModeloHeladera> buscarTodos() {
        return entityManager().createQuery("from ModeloHeladera where activo=:activo", ModeloHeladera.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(ModeloHeladera modeloHeladera) {
        withTransaction(() -> entityManager().persist(modeloHeladera));
    }

    public void guardar(ModeloHeladera... modeloHeladera) {

        withTransaction(() -> {
            for (ModeloHeladera modelo : modeloHeladera) {
                entityManager().persist(modelo);
            }
        });
    }

    @Override
    public void actualizar(ModeloHeladera modeloHeladera) {
        withTransaction(() -> entityManager().merge(modeloHeladera));
    }

    @Override
    public void eliminar(ModeloHeladera modeloHeladera) {
        modeloHeladera.borrarLogico();
        withTransaction(() -> entityManager().merge(modeloHeladera));
    }

  /*public static void main(String[] args) {
        ModeloHeladera m = new ModeloHeladera("otro");
        ModeloHeladera m1 = new ModeloHeladera("uno");
        ModeloHeladera m2 = new ModeloHeladera("hola");
        IModeloHeladeraRepository repositorio = (IModeloHeladeraRepository) ServiceLocator.get("modeloHeladeraRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<ModeloHeladera> modeloHeladera1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<ModeloHeladera> modeloHeladera2 = repositorio.buscar(2L);

        List<ModeloHeladera> lista = repositorio.buscarTodos();

    }*/
}
