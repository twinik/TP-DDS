package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.repositories.IRespuestasCampoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RespuestasCampoRepository implements IRespuestasCampoRepository, WithSimplePersistenceUnit {


    @Override
    public Optional<RespuestaACampo> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(RespuestaACampo.class, id));
    }

    @Override
    public List<RespuestaACampo> buscarTodos() {
        return entityManager().createQuery("from RespuestaACampo where activo=:activo", RespuestaACampo.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(RespuestaACampo respuestaACampo) {
        withTransaction(() -> entityManager().persist(respuestaACampo));
    }

    public void guardar(RespuestaACampo... respuestaACampo) {

        withTransaction(() -> {
            for (RespuestaACampo respuesta : respuestaACampo) {
                entityManager().persist(respuesta);
            }
        });
    }

    @Override
    public void actualizar(RespuestaACampo respuestaACampo) {
        withTransaction(() -> entityManager().merge(respuestaACampo));
    }

    @Override
    public void eliminar(RespuestaACampo respuestaACampo) {
        respuestaACampo.borrarLogico();
        withTransaction(() -> entityManager().merge(respuestaACampo));
    }

  /*public static void main(String[] args) {
        RespuestaACampo m = new RespuestaACampo("otro");
        RespuestaACampo m1 = new RespuestaACampo("uno");
        RespuestaACampo m2 = new RespuestaACampo("hola");
        IRespuestasCampoRepository repositorio = (IRespuestasCampoRepository) ServiceLocator.get("respuestasCampoRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<RespuestaACampo> respuestaACampo1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<RespuestaACampo> respuestaACampo2 = repositorio.buscar(2L);

        List<RespuestaACampo> lista = repositorio.buscarTodos();

    }*/

}