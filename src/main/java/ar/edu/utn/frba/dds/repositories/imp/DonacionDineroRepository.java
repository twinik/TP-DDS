package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.repositories.IDonacionDineroRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class DonacionDineroRepository implements IDonacionDineroRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<DonacionDinero> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(DonacionDinero.class,id));
    }

    @Override
    public List<DonacionDinero> buscarTodos() {
        return entityManager().createQuery("from DonacionDinero where activo=:activo",DonacionDinero.class).
                setParameter("activo",true)
                .getResultList();
    }

    @Override
    public void guardar(DonacionDinero donacionDinero) {
        withTransaction(() -> entityManager().persist(donacionDinero));
    }

    public void guardar(DonacionDinero ...donacionDinero) {

        withTransaction(() -> {
            for (DonacionDinero donacion : donacionDinero){
                entityManager().persist(donacion);
            }
        });
    }

    @Override
    public void actualizar(DonacionDinero donacionDinero) {
        withTransaction(() -> entityManager().merge(donacionDinero));
    }

    @Override
    public void eliminar(DonacionDinero donacionDinero) {
        donacionDinero.borrarLogico();
        withTransaction(() -> entityManager().merge(donacionDinero));
    }

    /*
    * public static void main(String[] args) {
        DonacionDinero m = new DonacionDinero("otro");
        DonacionDinero m1 = new DonacionDinero("uno");
        DonacionDinero m2 = new DonacionDinero("hola");
        IDonacionDineroRepository repositorio = (IDonacionDineroRepository) ServiceLocator.get("donacionDineroRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<DonacionDinero> donacion1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<DonacionDinero> donacion2 = repositorio.buscar(2L);

        List<DonacionDinero> lista = repositorio.buscarTodos();

    }*/
}
