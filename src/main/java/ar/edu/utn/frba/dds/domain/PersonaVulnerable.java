package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * PersonaVulnerable class permite representar una persona vulnerable.
 */
@Getter
@Setter
@Entity
@Table(name = "persona_vulnerable")
@NoArgsConstructor
public class PersonaVulnerable extends EntidadPersistente {


    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_naciminiento")
    private Date fechaNacimiento;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "posee_domicilio")
    private boolean poseeDomicilio;

    @Column(name = "domicilio")
    private String domicilio;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(name = "nro_documento", columnDefinition = "varchar(11)")
    private String nroDocumento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
    private Colaborador colaborador;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    private List<PersonaVulnerable> tutorados;


    public PersonaVulnerable(String nombre, Date fechaNacimiento, Date fechaRegistro, boolean poseeDomicilio, String domicilio, TipoDocumento tipoDocumento, String nroDocumento, Colaborador colaborador, List<PersonaVulnerable> tutorados) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.poseeDomicilio = poseeDomicilio;
        this.domicilio = domicilio;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.colaborador = colaborador;
        this.tutorados = tutorados;
    }

    public boolean poseeMenores() {
        return tutorados.stream().anyMatch(PersonaVulnerable::esMenor);
    }

    public Integer cantidadMenores() {
        return Math.toIntExact(tutorados.stream().filter(PersonaVulnerable::esMenor).count());
    }

    public boolean esMenor() {
        return this.edad() < 18;
    }

    private Integer edad() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(this.fechaNacimiento));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        int age = (d2 - d1) / 10000;
        return age;
    }

    public void agregarTutorados(PersonaVulnerable... tutorados) {
        Collections.addAll(this.tutorados, tutorados);
    }

}