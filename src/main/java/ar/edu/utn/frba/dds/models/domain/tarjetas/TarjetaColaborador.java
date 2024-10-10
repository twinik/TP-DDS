package ar.edu.utn.frba.dds.models.domain.tarjetas;

import ar.edu.utn.frba.dds.helpers.GeneradorDeCodigosHelper;
import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import ar.edu.utn.frba.dds.models.domain.excepciones.NoTieneDireccionException;
import ar.edu.utn.frba.dds.models.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeExcepcionDireccionTarjetaFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * representa una tarjeta que usa el colaborador para acceder a las heladeras
 */
@Entity
@Table(name = "tarjeta_colaborador")
@Getter
@Setter
@NoArgsConstructor
public class TarjetaColaborador extends EntidadPersistente {

    @Column(name = "codigo", unique = true)
    private String codigo;

    @OneToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", unique = true)
    private Colaborador colaborador;

    @Column(name = "tarjeta_activa")
    private boolean tarjetaActiva;

    @Column(name = "fecha_alta", columnDefinition = "DATE")
    private LocalDate fechaAlta;

    @Column(name = "fecha_baja", columnDefinition = "DATE")
    private LocalDate fechaBaja;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_id", referencedColumnName = "id")
    private List<AperturaHeladera> usos;

    public TarjetaColaborador(String codigo, Colaborador colaborador, boolean activa, LocalDate fechaAlta, LocalDate fechaBaja, List<AperturaHeladera> usos) {
        if(!GeneradorDeCodigosHelper.esCodigoValido(codigo, 11)) throw new CodigoInvalidoException("El codigo no es valido");
        this.codigo = codigo;
        this.colaborador = colaborador;
        this.tarjetaActiva = activa;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.usos = usos;
    }

    public static TarjetaColaborador of(Colaborador colaborador, String codigo) throws NoTieneDireccionException {
        if (colaborador.getDireccion() == null)
            throw new NoTieneDireccionException(MensajeExcepcionDireccionTarjetaFactory.generarMensaje());
        return new TarjetaColaborador(codigo, colaborador, true, LocalDate.now(), null, new ArrayList<>());
    }

    public static TarjetaColaborador of(Colaborador colaborador, String codigo, LocalDate fechaAlta) throws NoTieneDireccionException {
        if (colaborador.getDireccion() == null)
            throw new NoTieneDireccionException(MensajeExcepcionDireccionTarjetaFactory.generarMensaje());
        return new TarjetaColaborador(codigo, colaborador, true, fechaAlta, null, new ArrayList<>());
    }

}