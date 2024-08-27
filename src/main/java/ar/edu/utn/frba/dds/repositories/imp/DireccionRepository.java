package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.repositories.IDireccionRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class DireccionRepository implements IDireccionRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Direccion> buscar(long id) {
        return Optional.ofNullable(entityManager().find(Direccion.class,id));
    }

    @Override
    public List<Direccion> buscarTodos() {
        return entityManager().createQuery("from Direccion where activo=:activo",Direccion.class).
                setParameter("activo",true)
                .getResultList();
    }

    @Override
    public void guardar(Direccion direccion) {
        withTransaction(() -> entityManager().persist(direccion));
    }

    public void guardar(Direccion ...direccion) {

        withTransaction(() -> {
            for (Direccion direc : direccion){
                entityManager().persist(direc);
            }
        });
    }

    @Override
    public void actualizar(Direccion direccion) {
        withTransaction(() -> entityManager().merge(direccion));
    }

    @Override
    public void eliminar(Direccion direccion) {
        direccion.setActivo(false);
        withTransaction(() -> entityManager().merge(direccion));
    }

    /* public static void main(String[] args) {
        Direccion m = new Direccion("otro");
        Direccion m1 = new Direccion("uno");
        Direccion m2 = new Direccion("hola");
        IDireccionRepository repositorio = (IDireccionRepository) ServiceLocator.get("direccionRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Direccion> direccion1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Direccion> direccion2 = repositorio.buscar(2L);

        List<Direccion> lista = repositorio.buscarTodos();

    }*/
}
