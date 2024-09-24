package ar.edu.utn.frba.dds.models.domain.colaboraciones;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * OfertaProducto class representa una colaboracion de un colaborador.
 * Consiste en ofrecer un producto a cambio de puntos.
 */
@Entity
@Table(name = "oferta_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfertaProducto extends EntidadPersistente {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
    private Colaborador colaborador;

    @Column(name = "fecha_creacion", columnDefinition = "DATE", nullable = false)
    private LocalDate fechaCreacion;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false)
    private Producto producto;

    @Column(name = "puntos_necesarios", nullable = false)
    private Float puntosNecesarios;

    @Enumerated(EnumType.STRING)
    private CategoriaOferta categoria;

    /**
     * Metodo puedeSerCanjeadoPor que se encarga de verificar.
     * si el colaborador puede canjear el producto.
     */
    public boolean puedeSerCanjeadoPor(Colaborador colaborador) {
        return colaborador.getPuntosGanados() >= this.puntosNecesarios;
    }

}