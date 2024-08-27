package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.repositories.IAreaDeCoberturaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class AreaDeCoberturaRepository implements IAreaDeCoberturaRepository, WithSimplePersistenceUnit {

    @Override
    public Optional<AreaDeCobertura> buscar(long id) {
        return Optional.ofNullable(entityManager().find(AreaDeCobertura.class,id));
    }

    @Override
    public List<AreaDeCobertura> buscarTodos() {
        return entityManager().createQuery("from AreaDeCobertura where activo=:activo",AreaDeCobertura.class).
                setParameter("activo",true)
                .getResultList();
    }

    @Override
    public void guardar(AreaDeCobertura areaDeCobertura) {
        withTransaction(() -> entityManager().persist(areaDeCobertura));
    }

    public void guardar(AreaDeCobertura ...areaDeCobertura) {

        withTransaction(() -> {
            for (AreaDeCobertura area : areaDeCobertura) {
                entityManager().persist(area);
            }
        });
    }

    @Override
    public void actualizar(AreaDeCobertura areaDeCobertura) {
        withTransaction(() -> entityManager().merge(areaDeCobertura));
    }

    @Override
    public void eliminar(AreaDeCobertura areaDeCobertura) {
        areaDeCobertura.setActivo(false);
        withTransaction(() -> entityManager().merge(areaDeCobertura));
    }

    /*public static void main(String[] args) {
        AreaDeCobertura m = new AreaDeCobertura("otro");
        AreaDeCobertura m1 = new AreaDeCobertura("uno");
        AreaDeCobertura m2 = new AreaDeCobertura("hola");
        IAreaDeCoberturaRepository repositorio = (IAreaDeCoberturaRepository) ServiceLocator.get("areaDeCoberturaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<MotivoRedistribucionVianda> area1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<MotivoRedistribucionVianda> area2 = repositorio.buscar(2L);

        List<MotivoRedistribucionVianda> lista = repositorio.buscarTodos();

    }*/
}
