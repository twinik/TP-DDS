package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Heladera class permite representar una heladera.
 */
@Entity
@Table(name = "heladera")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Heladera {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
  private Ubicacion ubicacion;

  @OneToOne
  @JoinColumn(name = "direccion_id", referencedColumnName = "id")
  private Direccion direccion;

  @Column(name = "activa")
  private boolean activa = true;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "capacidad_viandas")
  private Integer capacidadViandas;

  @Column(name = "fecha_puesta_funcionamiento", columnDefinition = "DATE")
  private LocalDate fechaPuestaFuncionamiento;

  @OneToMany
  @JoinColumn(name = "heladera_id")
  private List<Vianda> viandas;

  @OneToOne
  @JoinColumn(name = "modelo_id", referencedColumnName = "id")
  private ModeloHeladera modelo;

  // TODO persistimos RegistroTemperatura? Segun enunciado no es necesario
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "heladera_id",referencedColumnName = "id")
  private List<RegistroTemperatura> registroTemperaturas = new ArrayList<>();

  @OneToMany(mappedBy = "heladera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<SolicitudAperturaHeladera> solicitudesApertura = new ArrayList<>();

  @OneToMany
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private List<Suscripcion> suscripciones = new ArrayList<>();

  // TODO: DEJAR PARA PENSAR UN POQUITO
  @Transient
  private List<Heladera> heladerasCercanas = new ArrayList<>();

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

  public void agregarHeladeraCercana(Heladera... heladeras){
      this.heladerasCercanas.addAll(Arrays.stream(heladeras).toList());
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

  public int getCuposLibresViandas(){
    return this.capacidadViandas - this.viandas.size();
  }

  //un metodo llamado heladerasCercanas que devuelve una lista de heladeras cercanas a la heladera pasado por parametro


  private void avisarObservers() {
    this.suscripciones.stream().parallel().forEach(s -> s.avisarEvento(this));
  }

}