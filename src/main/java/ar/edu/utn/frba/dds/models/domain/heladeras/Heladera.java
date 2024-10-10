package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Heladera class permite representar una heladera.
 */
@Entity
@Table(name = "heladera")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Heladera extends EntidadPersistente {

    @Embedded
    private Ubicacion ubicacion;

    @Embedded
    private Direccion direccion;

    @Column(name = "heladera_activa")
    private boolean heladeraActiva = true;

    @Column(name = "nombre", unique = true)
    private String nombre;

    @Column(name = "capacidad_viandas")
    private Integer capacidadViandas;

    @Column(name = "fecha_puesta_funcionamiento", columnDefinition = "DATE")
    private LocalDate fechaPuestaFuncionamiento;

    @OneToMany(mappedBy = "heladera", fetch = FetchType.LAZY)
    private List<Vianda> viandas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelo_id", referencedColumnName = "id")
    private ModeloHeladera modelo;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private List<RegistroTemperatura> registroTemperaturas = new ArrayList<>();

    @OneToMany(mappedBy = "heladera", fetch = FetchType.LAZY)
    private List<SolicitudAperturaHeladera> solicitudesApertura = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private List<Suscripcion> suscripciones = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cercania_heladera", inverseJoinColumns = @JoinColumn(name = "heladera2_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "heldera1_id", referencedColumnName = "id"))
    private List<Heladera> heladerasCercanas = new ArrayList<>();

    @Column(name = "cantidad_viandas")
    private String cantidadViandas;

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
        vianda.setHeladera(this);
        avisarObservers();
    }

    public void quitarVianda(Vianda vianda) {
        this.viandas.remove(vianda);
        avisarObservers();
    }

    // TODO cuando llegue el momento ver como funciona esto
    public void agregarHeladeraCercana(Heladera... heladeras) {
        this.heladerasCercanas.addAll(Arrays.stream(heladeras).toList());
    }


    public void agregarSuscripcion(Suscripcion... suscripciones) {
        this.suscripciones.addAll(Arrays.stream(suscripciones).toList());
    }

    public void agregarSoliApertura(SolicitudAperturaHeladera soli) {
        this.solicitudesApertura.add(soli);
        soli.setHeladera(this);
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
        this.heladeraActiva = false;
        avisarObservers();
    }

    public int getCuposLibresViandas() {
        return this.capacidadViandas - this.viandas.size();
    }

    //un metodo llamado heladerasCercanas que devuelve una lista de heladeras cercanas a la heladera pasado por parametro


    private void avisarObservers() {
        this.suscripciones.stream().parallel().forEach(s -> s.avisarEvento(this));
    }

}