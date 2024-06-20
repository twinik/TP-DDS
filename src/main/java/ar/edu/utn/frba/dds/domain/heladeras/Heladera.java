package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Heladera class permite representar una heladera.
 */
@Getter
@Setter
@AllArgsConstructor
public class Heladera {
  private Ubicacion ubicacion;
  private Direccion direccion;
  private boolean activa = true;
  private String nombre;
  private Integer capacidadViandas;
  private LocalDate fechaPuestaFuncionamiento;
  private List<Vianda> viandas;
  private ModeloHeladera modelo;
  private List<RegistroTemperatura> registroTemperaturas = new ArrayList<>();
  private List<SolicitudAperturaHeladera> solicitudesApertura = new ArrayList<>();
  private List<Suscripcion> suscripciones = new ArrayList<>();

  public Heladera(LocalDate fecha) {
    this.fechaPuestaFuncionamiento = fecha;
  }

  public Heladera(Ubicacion ubicacion, Direccion direccion, String nombre, Integer capacidadViandas,
                  LocalDate fechaPuestaFuncionamiento, List<Vianda> viandas,
                  ModeloHeladera modelo) {
    this.ubicacion = ubicacion;
    this.direccion = direccion;
    this.nombre = nombre;
    this.capacidadViandas = capacidadViandas;
    this.fechaPuestaFuncionamiento = fechaPuestaFuncionamiento;
    this.viandas = viandas;
    this.modelo = modelo;
  }

  public void agregarVianda(Vianda vianda) {
    this.viandas.add(vianda);
    avisarObservers();
  }

  public void quitarVianda(Vianda vianda) {
    this.viandas.remove(vianda);
    avisarObservers();
  }

  public void agregarSuscripcion(Suscripcion... suscripciones){
    this.suscripciones.addAll(Arrays.stream(suscripciones).toList());
  }

  /**
   * Metodo que registra la temperatura de la heladera.
   */
  public void registrarTemperatura(float temperatura) {
    registroTemperaturas.add(new RegistroTemperatura(LocalDateTime.now(), temperatura));
  }

  public boolean temperaturaEsAdecuada() {
    Float temp = this.registroTemperaturas.get(registroTemperaturas.size() - 1).temperaturaRegistrada;
    return (temp < this.modelo.getTempMax() && temp > this.modelo.getTempMin());
  }

  public float getUltimaTemperaturaRegistrada() {
    // asumo que van en orden la lista, si no hay que comparar las fechas
    return this.registroTemperaturas.get(this.registroTemperaturas.size() - 1).temperaturaRegistrada;
  }

  public void inhabilitar() {
    this.activa = false;
    avisarObservers();
  }

  //un metodo llamado heladerasCercanas que devuelve una lista de heladeras cercanas a la heladera pasado por parametro


  private void avisarObservers() {
    this.suscripciones.stream().parallel().forEach(s -> s.avisarEvento(this));
  }

}