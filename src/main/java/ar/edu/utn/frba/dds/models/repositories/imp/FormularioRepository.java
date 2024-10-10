package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import ar.edu.utn.frba.dds.models.repositories.IFormularioRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class FormularioRepository implements IFormularioRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Formulario> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Formulario.class, id));
    }

    @Override
    public List<Formulario> buscarTodos() {
        return entityManager().createQuery("from Formulario where activo=:activo order by created_at", Formulario.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Formulario formulario) {
        withTransaction(() -> entityManager().persist(formulario));
    }

    public void guardar(Formulario... formulario) {

        withTransaction(() -> {
            for (Formulario form : formulario) {
                entityManager().persist(form);
            }
        });
    }


    @Override
    public void actualizar(Formulario formulario) {
        withTransaction(() -> entityManager().merge(formulario));
    }

    @Override
    public void eliminar(Formulario formulario) {

        withTransaction(() -> {
            formulario.borrarLogico();
            entityManager().merge(formulario);
        });
    }

    /*public static void main(String[] args) {
        Formulario m = new Formulario("otro");
        Formulario m1 = new Formulario("uno");
        Formulario m2 = new Formulario("hola");
        IFormularioRepository repositorio = (IFormularioRepository) ServiceLocator.get("formularioRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Formulario> formulario1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Formulario> formulario2 = repositorio.buscar(2L);

        List<Formulario> lista = repositorio.buscarTodos();

    }*/

}
