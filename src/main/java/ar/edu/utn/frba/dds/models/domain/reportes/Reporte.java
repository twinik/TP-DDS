package ar.edu.utn.frba.dds.models.domain.reportes;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

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

  public abstract String getTipo();
}