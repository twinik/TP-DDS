package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import ar.edu.utn.frba.dds.repositories.IRespuestasFormularioRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RespuestasFormularioRepository implements IRespuestasFormularioRepository, WithSimplePersistenceUnit {

    @Override
    public Optional<RespuestaFormulario> buscar(Long id) {
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

    public void guardar(RespuestaFormulario... respuestaFormulario) {

        withTransaction(() -> {
            for (RespuestaFormulario respuesta : respuestaFormulario) {
                entityManager().persist(respuesta);
            }
        });
    }

    @Override
    public void actualizar(RespuestaFormulario respuestaFormulario) {
        withTransaction(() -> entityManager().merge(respuestaFormulario));
    }

    @Override
    public void eliminar(RespuestaFormulario respuestaFormulario) {
        respuestaFormulario.borrarLogico();
        withTransaction(() -> entityManager().merge(respuestaFormulario));
    }

  /* public static void main(String[] args) {
        RespuestaFormulario m = new RespuestaFormulario("otro");
        RespuestaFormulario m1 = new RespuestaFormulario("uno");
        RespuestaFormulario m2 = new RespuestaFormulario("hola");
        IRespuestasFormularioRepository repositorio = (IRespuestasFormularioRepository) ServiceLocator.get("respuestasFormularioRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<RespuestaFormulario> respuestaFormulario1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<RespuestaFormulario> respuestaFormulario2 = repositorio.buscar(2L);

        List<RespuestaFormulario> lista = repositorio.buscarTodos();

    }*/

}
