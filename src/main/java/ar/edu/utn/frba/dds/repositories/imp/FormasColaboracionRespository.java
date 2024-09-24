package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.repositories.IFormasColaboracionRespository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FormasColaboracionRespository implements IFormasColaboracionRespository, WithSimplePersistenceUnit {

    private List<FormaColaboracion> formas;

    public FormasColaboracionRespository() {
        this.formas = new ArrayList<>();
    }

    @Override
    public Optional<FormaColaboracion> buscarPorNombre(String id) {
        return Optional.ofNullable(entityManager().find(FormaColaboracion.class, id));
    }

    @Override
    public Optional<FormaColaboracion> buscar(String nombre) {
        return this.formas.stream().filter(f -> f.getNombre().equals(nombre)).findFirst(); // Este va o no???
    }


    @Override
    public List<FormaColaboracion> buscarTodos() {
        return entityManager().createQuery("from FormaColaboracion where activo=:activo", FormaColaboracion.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(FormaColaboracion forma) {
        withTransaction(() -> entityManager().persist(forma));
    }

    public void guardar(FormaColaboracion... forma) {

        withTransaction(() -> {
            for (FormaColaboracion f : forma) {
                entityManager().persist(f);
            }
        });
    }

    @Override
    public void actualizar(FormaColaboracion forma) {
        withTransaction(() -> entityManager().merge(forma));
    }

    @Override
    public void eliminar(FormaColaboracion forma) {
        forma.borrarLogico();
        withTransaction(() -> entityManager().merge(forma));
    }

  /*public static void main(String[] args) {
        FormaColaboracion m = new FormaColaboracion("otro");
        FormaColaboracion m1 = new FormaColaboracion("uno");
        FormaColaboracion m2 = new FormaColaboracion("hola");
        IFormasColaboracionRespository repositorio = (IFormasColaboracionRespository) ServiceLocator.get("formasColaboracionRespository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<FormaColaboracion> forma1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<FormaColaboracion> forma2 = repositorio.buscar(2L);

        List<FormaColaboracion> lista = repositorio.buscarTodos();

    }*/

}
