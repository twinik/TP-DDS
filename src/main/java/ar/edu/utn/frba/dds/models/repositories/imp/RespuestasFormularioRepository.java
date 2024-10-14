package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.RespuestaFormulario;
import ar.edu.utn.frba.dds.models.repositories.IRespuestasFormularioRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class RespuestasFormularioRepository implements IRespuestasFormularioRepository, WithSimplePersistenceUnit {

    @Override
    public Optional<RespuestaFormulario> buscar(String id) {
        return Optional.ofNullable(entityManager().find(RespuestaFormulario.class, id));
    }

    @Override
    public List<RespuestaFormulario> buscarTodos() {
        return entityManager().createQuery("from RespuestaFormulario where activo=:activo", RespuestaFormulario.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(RespuestaFormulario respuestaFormulario) {
        withTransaction(() -> entityManager().persist(respuestaFormulario));
    }
    @Override
    public void actualizar(RespuestaFormulario respuestaFormulario) {
        withTransaction(() -> entityManager().merge(respuestaFormulario));
    }

    @Override
    public void eliminar(RespuestaFormulario respuestaFormulario) {

        withTransaction(() -> {
            respuestaFormulario.borrarLogico();
            entityManager().merge(respuestaFormulario);
        });
    }
}
