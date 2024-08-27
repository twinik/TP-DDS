package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlertasRepository implements IAlertasRepository {

  private List<Alerta> alertas;

  public AlertasRepository() {
    alertas = new ArrayList<>();
  }


  @Override
  public Optional<Alerta> buscar(long id) {
    return this.alertas.stream().filter(a -> a.getId() == id).findFirst();
  }

  @Override
  public List<Alerta> buscarTodos() {
    return this.alertas;
  }

  @Override
  public void guardar(Alerta alerta) {
    this.alertas.add(alerta);
  }

  @Override
  public void actualizar(Alerta alerta) {

  }

  @Override
  public void eliminar(Alerta alerta) {

  }
}
/*public class AlertasRepository implements IAlertasRepository, WithSimplePersistenceUnit {

  private List<Alerta> alertas;

  public AlertasRepository() {
    alertas = new ArrayList<>();
  }


  @Override
  public Optional<Alerta> buscar(long id) { //Alerta no tiene ID, ver que se hace
   return Optional.ofNullable(entityManager().find(Alerta.class,id));
  }

  @Override
  public List<Alerta> buscarTodos() {
    return entityManager().createQuery("from Alerta",Alerta.class).getResultList();
  }

  @Override
  public void guardar(Alerta alerta) {
    withTransaction(() -> entityManager().persist(alerta));
  }

  public void guardar(Alerta ...alerta) {

    withTransaction(() -> {
      for (Alerta tipoAlerta : alerta){
        entityManager().persist(tipoAlerta);
      }
    });
  }

  @Override
  public void actualizar(Alerta alerta) {
    withTransaction(() -> entityManager().merge(alerta));
  }

  @Override
  public void eliminar(Alerta alerta) {
    //alerta.setActivo(false);
    withTransaction(() -> entityManager().merge(alerta));
  }

  public static void main(String[] args) {
    Alerta m = new Alerta(TipoAlerta.TEMPERATURA);
    Alerta m1 = new Alerta(TipoAlerta.FRAUDE);
    Alerta m2 = new Alerta(TipoAlerta.FALLA_CONEXION);
    IAlertasRepository repositorio = (IAlertasRepository) ServiceLocator.get("alertasRepository");
    repositorio.guardar(m);
    repositorio.guardar(m1);
    repositorio.guardar(m2);

    repositorio.eliminar(m1);
    try {
      Thread.sleep(60 * 1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    repositorio.actualizar(m2);

    Optional<Alerta> alerta1 = repositorio.buscar(1L); //Alerta no tiene ID, ver que se hace
    //System.out.println(hidratado.get().getMotivo());
    Optional<Alerta> alerta2 = repositorio.buscar(2L); //Alerta no tiene ID, ver que se hace

    List<Alerta> lista = repositorio.buscarTodos();

  }

}*/