package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.repositories.IFallasTecnicasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor

public class FallasTecnicasRepository implements IFallasTecnicasRepository, WithSimplePersistenceUnit {


    public static void main(String[] args) {
        Colaborador c = new Colaborador();
        c.setUsuario(new Usuario("123", "123"));
        FallaTecnica f1 = new FallaTecnica(new Heladera(), LocalDateTime.now(), new TecnicosHelper(new TecnicosRepository()),
                new NotificationStrategyFactory(), c, "hola", "rerer");

        FallasTecnicasRepository repo = new FallasTecnicasRepository();
        repo.guardar(f1);

        Optional<FallaTecnica> f3 = repo.buscar(f1.getId());

    }

    @Override
    public Optional<FallaTecnica> buscar(String id) {
        return Optional.ofNullable(entityManager().find(FallaTecnica.class, id));
    }

    @Override
    public List<FallaTecnica> buscarTodos() {
        return entityManager().createQuery("from FallaTecnica where activo=:activo", FallaTecnica.class)
                .setParameter("activo", true)
                .getResultList();
    }

    @Override
    public List<FallaTecnica> buscarPorHeladera(Long heladera_id) {
        return entityManager().createQuery("from FallaTecnica where activo=:activo and heladera.id=:heladera_id and solucionado=:solucionado", FallaTecnica.class)
                .setParameter("activo", true)
                .setParameter("heladera_id", heladera_id)
                .setParameter("solucionado", false)
                .getResultList();
    }

    @Override
    public Map<String, Long> buscarFallasAgrupadasPorHeladera(LocalDate fecha) {
        LocalDateTime principioDeSemana = DateHelper.principioDeSemana(fecha.atStartOfDay());
        LocalDateTime finDeSemana = DateHelper.finDeSemana(fecha).atStartOfDay();
        List<Object[]> results = entityManager().createQuery(
                        "select f.heladera.nombre, count(f) from FallaTecnica f where f.activo = :activo" + " and f.timestamp between :principioSemana and :finSemana group by f.heladera.nombre order by count(f)", Object[].class)
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
    public void guardar(FallaTecnica fallaTecnica) {
        withTransaction(() -> entityManager().persist(fallaTecnica));
    }

    @Override
    public void actualizar(FallaTecnica fallaTecnica) {
        withTransaction(() -> entityManager().merge(fallaTecnica));
    }

    @Override
    public void eliminar(FallaTecnica fallaTecnica) {
        fallaTecnica.borrarLogico();
        withTransaction(() -> entityManager().merge(fallaTecnica));
    }
}
