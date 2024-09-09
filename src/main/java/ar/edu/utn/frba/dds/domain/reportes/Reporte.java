package ar.edu.utn.frba.dds.domain.reportes;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Representa la interfaz de un reporte
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reporte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Reporte extends EntidadPersistente {
    @Column(name = "ruta_archivo")
    protected String rutaArchivo;
    public abstract void generarPDF();
}