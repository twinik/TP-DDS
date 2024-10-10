package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.tarjetas.PosibleCodigoTarjeta;
import ar.edu.utn.frba.dds.models.repositories.IPosiblesCodigosTarjetaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class PosiblesCodigosTarjetaRepository implements WithSimplePersistenceUnit, IPosiblesCodigosTarjetaRepository {

    @Override
    public Optional<PosibleCodigoTarjeta> buscar(String id) {
        return Optional.ofNullable(entityManager().find(PosibleCodigoTarjeta.class,id));
    }

    @Override
    public Optional<PosibleCodigoTarjeta> buscarPorCodigo(String codigo) {
        try{
            return Optional.of(entityManager().createQuery("from PosibleCodigoTarjeta where codigo=:codigo and activo=:activo", PosibleCodigoTarjeta.class)
                    .setParameter("codigo",codigo)
                    .setParameter("activo",true)
                    .getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public List<PosibleCodigoTarjeta> buscarTodos() {
        return entityManager().createQuery("from PosibleCodigoTarjeta where activo=:activo", PosibleCodigoTarjeta.class)
                .setParameter("activo",true)
                .getResultList();
    }

    @Override
    public void guardar(PosibleCodigoTarjeta codigo) {
        withTransaction(() -> entityManager().persist(codigo));
    }

    @Override
    public void actualizar(PosibleCodigoTarjeta codigo) {
        withTransaction(()->entityManager().merge(codigo));
    }

    @Override
    public void eliminar(PosibleCodigoTarjeta codigo) {
        withTransaction(()->{
            codigo.borrarLogico();
            entityManager().merge(codigo);
        });
    }

}
