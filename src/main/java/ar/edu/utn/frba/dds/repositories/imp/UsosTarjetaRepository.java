package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tarjetas.UsoTarjeta;
import ar.edu.utn.frba.dds.repositories.IUsosTarjetaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class UsosTarjetaRepository implements IUsosTarjetaRepository, WithSimplePersistenceUnit {


    @Override
    public Optional<UsoTarjeta> buscar(String id) {
        return Optional.ofNullable(entityManager().find(UsoTarjeta.class, id));
    }

    @Override
    public List<UsoTarjeta> buscarTodos() {
        return entityManager().createQuery("from UsoTarjeta where activo=:activo", UsoTarjeta.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(UsoTarjeta usoTarjeta) {
        withTransaction(() -> entityManager().persist(usoTarjeta));
    }

    public void guardar(UsoTarjeta... usoTarjeta) {

        withTransaction(() -> {
            for (UsoTarjeta uso : usoTarjeta) {
                entityManager().persist(uso);
            }
        });
    }

    @Override
    public void actualizar(UsoTarjeta usoTarjeta) {
        withTransaction(() -> entityManager().merge(usoTarjeta));
    }

    @Override
    public void eliminar(UsoTarjeta usoTarjeta) {
        usoTarjeta.borrarLogico();
        withTransaction(() -> entityManager().merge(usoTarjeta));
    }

  /*public static void main(String[] args) {
        UsoTarjeta m = new UsoTarjeta("otro");
        UsoTarjeta m1 = new UsoTarjeta("uno");
        UsoTarjeta m2 = new UsoTarjeta("hola");
        IUsosTarjetaRepository repositorio = (IUsosTarjetaRepository) ServiceLocator.get("usosTarjetaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<UsoTarjeta> usoTarjeta1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<UsoTarjeta> usoTarjeta2 = repositorio.buscar(2L);

        List<UsoTarjeta> lista = repositorio.buscarTodos();

    }*/

}
