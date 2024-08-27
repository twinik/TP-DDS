package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ColaboradorRepository class permite interactuar con los colaboradores.
 */
public class ColaboradoresRepository implements IColaboradoresRepository, WithSimplePersistenceUnit {
  private List<Colaborador> colaboradores;

  public ColaboradoresRepository() {
    colaboradores = new ArrayList<>();
  }

  @Override
  public Optional<Colaborador> buscar(TipoDocumento tipoDocumento, String documento) {
    return this.colaboradores.stream().filter(c -> c.getDocumento().equals(documento) && c.getTipoDocumento().equals(tipoDocumento)).findFirst(); //Queda o se va???
  }
  @Override
  public Optional<Colaborador> buscar(long id) {
    return Optional.ofNullable(entityManager().find(Colaborador.class,id));
  }

  @Override
  public List<Colaborador> buscarTodos() {
    return entityManager().createQuery("from Colaborador where activo=:activo",Colaborador.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(Colaborador colaborador) {
    withTransaction(() -> entityManager().persist(colaborador));
  }

  public void guardar(Colaborador ...colaborador) {

    withTransaction(() -> {
      for (Colaborador colab : colaborador){
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
    colaborador.setActivo(false);
    withTransaction(() -> entityManager().merge(colaborador));
  }

  /*public static void main(String[] args) {
        Colaborador m = new colaborador("otro");
        Colaborador m1 = new colaborador("uno");
        Colaborador m2 = new colaborador("hola");
        IColaboradoresRepository repositorio = (IColaboradoresRepository) ServiceLocator.get("colaboradorrRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Colaborador> colaborador1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Colaborador> colaborador2 = repositorio.buscar(2L);

        List<Colaborador> lista = repositorio.buscarTodos();

    }*/
}
