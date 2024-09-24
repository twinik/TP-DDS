package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.models.repositories.ICampoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class CampoRepository implements ICampoRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Campo> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Campo.class, id));
    }

    @Override
    public List<Campo> buscarTodos() {
        return entityManager().createQuery("from Campo where activo=:activo", Campo.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Campo campo) {
        withTransaction(() -> entityManager().persist(campo));
    }

    public void guardar(Campo... campo) {

        withTransaction(() -> {
            for (Campo camp : campo) {
                entityManager().persist(camp);
            }
        });
    }

    @Override
    public void actualizar(Campo campo) {
        withTransaction(() -> entityManager().merge(campo));
    }

    @Override
    public void eliminar(Campo campo) {
        campo.borrarLogico();
        withTransaction(() -> entityManager().merge(campo));
    }

    /* public static void main(String[] args) {
        Campo m = new campo("otro");
        Campo m1 = new campo("uno");
        Campo m2 = new campo("hola");
        ICampoRepository repositorio = (ICampoRepository) ServiceLocator.get("campoRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Campo> campo1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Campo> campo2 = repositorio.buscar(2L);

        List<Campo> lista = repositorio.buscarTodos();

    }*/
}
