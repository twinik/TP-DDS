package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.models.repositories.IMedioContactoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class MedioContactoRepository implements IMedioContactoRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<MedioDeContacto> buscar(String id) {
        return Optional.ofNullable(entityManager().find(MedioDeContacto.class, id));
    }

    @Override
    public List<MedioDeContacto> buscarTodos() {
        return entityManager().createQuery("from MedioDeContacto where activo=:activo", MedioDeContacto.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(MedioDeContacto visitaTecnico) {
        withTransaction(() -> entityManager().persist(visitaTecnico));
    }

    public void guardar(MedioDeContacto... visitaTecnico) {

        withTransaction(() -> {
            for (MedioDeContacto visita : visitaTecnico) {
                entityManager().persist(visita);
            }
        });
    }

    @Override
    public void actualizar(MedioDeContacto visitaTecnico) {
        withTransaction(() -> entityManager().merge(visitaTecnico));
    }

    @Override
    public void eliminar(MedioDeContacto visitaTecnico) {

        withTransaction(() -> {
            visitaTecnico.borrarLogico();
            entityManager().merge(visitaTecnico);
        });
    }

  /* public static void main(String[] args) {
        MedioDeContacto m = new MedioDeContacto("otro");
        MedioDeContacto m1 = new MedioDeContacto("uno");
        MedioDeContacto m2 = new MedioDeContacto("hola");
        IMedioContactoRepository repositorio = (IMedioContactoRepository) ServiceLocator.get("medioContactoRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<MedioDeContacto> visitaTecnico1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<MedioDeContacto> visitaTecnico2 = repositorio.buscar(2L);

        List<MedioDeContacto> lista = repositorio.buscarTodos();

    }*/

}
