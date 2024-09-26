package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.heladeras.SensorMovimiento;
import ar.edu.utn.frba.dds.models.repositories.IRolesRepository;
import ar.edu.utn.frba.dds.models.repositories.ISensorMovimientoRepository;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class SensoresMovimientoRepository implements ISensorMovimientoRepository, WithSimplePersistenceUnit {


    @Override
    public Optional<SensorMovimiento> buscar(String id) {
        return Optional.ofNullable(entityManager().find(SensorMovimiento.class, id));
    }

    @Override
    public List<SensorMovimiento> buscarTodos() {
        return entityManager().createQuery("from SensorMovimiento where activo=:activo", SensorMovimiento.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(SensorMovimiento sensorMovimiento) {
        withTransaction(() -> entityManager().persist(sensorMovimiento));
    }

    public void guardar(SensorMovimiento... sensorMovimiento) {

        withTransaction(() -> {
            for (SensorMovimiento sensor : sensorMovimiento) {
                entityManager().persist(sensor);
            }
        });
    }

    @Override
    public void actualizar(SensorMovimiento sensorMovimiento) {
        withTransaction(() -> entityManager().merge(sensorMovimiento));
    }

    @Override
    public void eliminar(SensorMovimiento sensorMovimiento) {

        withTransaction(() -> {
            sensorMovimiento.borrarLogico();
            entityManager().merge(sensorMovimiento);
        });
    }

    public static void main(String[] args) {
        IRolesRepository repo = ServiceLocator.get(IRolesRepository.class);
        Rol r1= new Rol();
        r1.agregarPermisos(new Permiso("hola"),new Permiso("permite_hacer"));
        Rol r2 = new Rol();
        r2.setNombre("rolcito");
        repo.guardar(r1);
        repo.guardar(r2);
        repo.eliminar(r2);

        Usuario u1 = new Usuario();
        u1.agregarRoles(r1);
        ServiceLocator.get(IUsuariosRepository.class).guardar(u1);
    }

}
