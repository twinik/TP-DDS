package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import ar.edu.utn.frba.dds.domain.notifications.Contactable;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.helpers.MedioContactoHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Colaborador class permite representar un colaborador.
 */

@Entity
@Table(name = "colaborador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador extends EntidadPersistente implements Contactable {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false, unique = true)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(columnDefinition = "varchar(11)")
    private String documento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_colaborador_id", referencedColumnName = "id", unique = true)
    private TipoColaborador tipoColaborador;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "respuesta_formulario_id", referencedColumnName = "id",unique = true)
    private RespuestaFormulario respuestas;

    @Column(name = "puntosGanados")
    private Float puntosGanados = 0f;

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColocacionHeladeras> heladerasColocadas = new ArrayList<>();

    @Embedded
    private Direccion direccion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
    private List<MedioDeContacto> medioContacto = new ArrayList<>();

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "rubro")
    private String rubro;

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "tipo_persona_juridica")
    @Enumerated(EnumType.STRING)
    private TipoPersonaJuridica tipoPersonaJuridica;

    public void sumarPuntos(Float puntos) {
        this.puntosGanados += puntos;
    }

    public void restarPuntos(Float puntos) {
        this.puntosGanados -= puntos;
    }

    public void agregarColocacionHeladera(ColocacionHeladeras colocacion) {
        this.heladerasColocadas.add(colocacion);
    }

    public void completarCampo(RespuestaACampo respuesta) {
        this.respuestas.agregarRespuestasACampo(respuesta);
    }

    public String getNombreYapellido() {
        return String.format("%s %s", this.nombre, this.apellido);
    }

    public void agregarMedioContacto(MedioDeContacto... medio) {
        this.medioContacto.addAll(Arrays.stream(medio).toList());
    }


    @Override
    public String email() {
        return MedioContactoHelper.getValorContacto(this.medioContacto, CanalContacto.EMAIL);
    }

    @Override
    public String telefonoCompleto() {
        return MedioContactoHelper.getValorContacto(this.medioContacto, CanalContacto.WHATSAPP);
    }

    @Override
    public String telegramId() {
        return MedioContactoHelper.getValorContacto(this.medioContacto, CanalContacto.TELEGRAM);
    }
}

//el colaborador a lo largo del tiempo podria tener distintas tarjetas
//podria tener una lista de tarjetas con el dia que se dio de alta y de baja