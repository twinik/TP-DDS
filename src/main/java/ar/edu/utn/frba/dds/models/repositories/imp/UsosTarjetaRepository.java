package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.tarjetas.UsoTarjeta;
import ar.edu.utn.frba.dds.models.repositories.IUsosTarjetaRepository;
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

    @Override
    public void actualizar(UsoTarjeta usoTarjeta) {
        withTransaction(() -> entityManager().merge(usoTarjeta));
    }

    @Override
    public void eliminar(UsoTarjeta usoTarjeta) {

        withTransaction(() -> {
            usoTarjeta.borrarLogico();
            entityManager().merge(usoTarjeta);
        });
    }

}
