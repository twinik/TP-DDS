package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.models.repositories.IDonacionesViandaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonacionesViandaRepository implements IDonacionesViandaRepository, WithSimplePersistenceUnit {

    @Override
    public Optional<DonacionVianda> buscar(String id) {
        return Optional.ofNullable(entityManager().find(DonacionVianda.class, id));
    }

    @Override
    public List<DonacionVianda> buscarTodos() {
        return entityManager().createQuery("from DonacionVianda where activo=:activo", DonacionVianda.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public List<DonacionVianda> buscarPorColaborador(String colaborador_id) {
        return entityManager().createQuery("from DonacionVianda where activo=:activo and colaborador.id=:colaborador_id", DonacionVianda.class)
                .setParameter("activo", true)
                .setParameter("colaborador_id", colaborador_id)
                .getResultList();
    }

    @Override
    public Map<String, Long> buscarDonacionesAgrupadasPorHeladera(LocalDate fecha) {
        LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
        LocalDate finDeSemana = DateHelper.finDeSemana(fecha);
        List<Object[]> results = entityManager().createQuery(
                        "select d.heladera.nombre, count(d) from DonacionVianda d where d.activo = :activo" + " and d.fecha between :principioSemana and :finSemana" +
                                " and d.heladera.activo=:activo group by d.heladera.nombre order by count(d)", Object[].class)
                .setParameter("activo", true)
                .setParameter("principioSemana", principioDeSemana)
                .setParameter("finSemana", finDeSemana)
                .getResultList();

        return results.stream().collect(Collectors.toMap(
                result -> (String) result[0],
                result -> (Long) result[1]
        ));
    }

    @Override
    public void guardar(DonacionVianda donacionVianda) {
        withTransaction(() -> entityManager().persist(donacionVianda));
    }

    public void guardar(DonacionVianda... donacionVianda) {

        withTransaction(() -> {
            for (DonacionVianda donacion : donacionVianda) {
                entityManager().persist(donacion);
            }
        });
    }

    @Override
    public void actualizar(DonacionVianda donacionVianda) {
        withTransaction(() -> entityManager().merge(donacionVianda));
    }

    @Override
    public void eliminar(DonacionVianda donacionVianda) {

        withTransaction(() -> {
            donacionVianda.borrarLogico();
            entityManager().merge(donacionVianda);
        });
    }

  /*public static void main(String[] args) {
        DonacionVianda m = new DonacionVianda("otro");
        DonacionVianda m1 = new DonacionVianda("uno");
        DonacionVianda m2 = new DonacionVianda("hola");
        IDonacionesViandaRepository repositorio = (IDonacionesViandaRepository) ServiceLocator.get("donacionesViandaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<DonacionVianda> donacion1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<DonacionVianda> donacion2 = repositorio.buscar(2L);

        List<DonacionVianda> lista = repositorio.buscarTodos();

    }*/
}
