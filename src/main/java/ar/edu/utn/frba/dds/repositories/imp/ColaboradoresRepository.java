package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ColaboradorRepository class permite interactuar con los colaboradores.
 */
public class ColaboradoresRepository implements IColaboradoresRepository, WithSimplePersistenceUnit {

    @Override
    public Optional<Colaborador> buscar(TipoDocumento tipoDocumento, String documento) {
        try {
            Colaborador c = (Colaborador) entityManager().createQuery("from Colaborador where tipoDocumento=:tipoDocumento and documento=:documento")
                    .setParameter("tipoDocumento", tipoDocumento)
                    .setParameter("documento", documento).getSingleResult();
            return Optional.ofNullable(c);
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Colaborador> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Colaborador.class, id));
    }

    @Override
    public List<Colaborador> buscarTodos() {
        return entityManager().createQuery("from Colaborador where activo=:activo", Colaborador.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Colaborador colaborador) {
        withTransaction(() -> entityManager().persist(colaborador));
    }

    public void guardar(Colaborador... colaborador) {

        withTransaction(() -> {
            for (Colaborador colab : colaborador) {
                entityManager().persist(colab);
            }
        });
    }

    @Override
    public void actualizar(Colaborador colaborador) {
        withTransaction(() -> entityManager().merge(colaborador));
    }

    @Override
    public void eliminar(Colaborador colaborador) {
        colaborador.borrarLogico();
        withTransaction(() -> entityManager().merge(colaborador));
    }

//  public static void main(String[] args) {
//        Colaborador m = new Colaborador();
//
//        ColaboradoresRepository repo = new ColaboradoresRepository();
//        m.setUsuario(new Usuario("hola","chau"));
//
//
//        DonacionDinero d = new DonacionDinero(m, LocalDate.now(),100f, FrecuenciaDonacion.ANUAL);
//
//        repo.guardar(m);
//
//        Optional<Colaborador> colaborador1 = repo.buscar(1L);
//        //System.out.println(hidratado.get().getMotivo());
//        Optional<Colaborador> colaborador2 = repo.buscar(2L);
//
//        List<Colaborador> lista = repo.buscarTodos();
//
//    }
}
