package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IPermisosRepository;
import ar.edu.utn.frba.dds.models.repositories.IRolesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.utils.PermisosHelper;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * ColaboradorRepository class permite interactuar con los colaboradores.
 */
public class ColaboradoresRepository implements IColaboradoresRepository, WithSimplePersistenceUnit {

    public static void main(String[] args) {
        Colaborador m = new Colaborador();

        ColaboradoresRepository repo = new ColaboradoresRepository();
        Usuario u = new Usuario("dkfnkdafs", "dlmfads");
        Rol r = new Rol();
        r.setNombre("fasf");
        Permiso p1 = new Permiso("fodmasofd", "dkasfds");
        Permiso p2 = new Permiso("lkfmasdlf", "flamflds");
        ServiceLocator.get(IPermisosRepository.class).guardar(p1, p2);

        r.agregarPermisos(PermisosHelper.getInstance().buscarPorNombres("dkasfds").toArray(new Permiso[0]));
        ServiceLocator.get(IRolesRepository.class).guardar(r);
        u.agregarRoles(r);
        m.setUsuario(u);


        DonacionDinero d = new DonacionDinero(m, LocalDate.now(), 100f, FrecuenciaDonacion.ANUAL);

        repo.guardar(m);

        repo.eliminar(m);
        //System.out.println(hidratado.get().getMotivo());
        // Optional<Colaborador> colaborador2 = repo.buscar(2L);

        List<Colaborador> lista = repo.buscarTodos();

    }

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
    public Optional<Colaborador> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Colaborador.class, id));
    }

    @Override
    public Optional<Colaborador> buscarPorUsuario(String idUsuario) {
        try {
            return Optional.of(entityManager().createQuery("from Colaborador where usuario.id = :idUsuario and activo=:activo", Colaborador.class)
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("activo", true)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Colaborador> buscarPorDni(TipoDocumento tipoDocumento, String documento) {
        try {
            return Optional.of(entityManager().createQuery("from Colaborador where documento =:documento and tipoDocumento=:tipo and activo=:activo", Colaborador.class)
                    .setParameter("documento", documento)
                    .setParameter("activo", true)
                    .setParameter("tipo", tipoDocumento)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Colaborador> buscarTodos() {
        return entityManager().createQuery("from Colaborador where activo=:activo and formCompletado=:formCompletado", Colaborador.class).
                setParameter("activo", true)
                .setParameter("formCompletado", true)
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

//    @Override
//    public void marcarFormCompletado(String id) {
//        withTransaction(() -> {
//            entityManager().createQuery("UPDATE Colaborador c SET c.formCompletado = :completado WHERE c.id = :idColaborador")
//                .setParameter("completado", true)
//                .setParameter("idColaborador", id)
//                .executeUpdate();
//        });
//    }

    @Override
    public void eliminar(Colaborador colaborador) {

        withTransaction(() -> {
            colaborador.borrarLogico();
            entityManager().merge(colaborador);
        });
    }
}
