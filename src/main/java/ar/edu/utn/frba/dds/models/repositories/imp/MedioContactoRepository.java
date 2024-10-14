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

}
