package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Clase: RegistroTemperatura: guarda la hora y la temperatura registrada en ese instante
 */
@Getter
@Entity
@Table(name = "registro_temperatura")
@NoArgsConstructor
public class RegistroTemperatura extends EntidadPersistente {
    @Column(name = "fecha_hora", columnDefinition = "TIMESTAMP", nullable = false)
    LocalDateTime fechaHora;
    @Column(name = "temp_registrada", nullable = false)
    float temperaturaRegistrada;

    public RegistroTemperatura(LocalDateTime fechaHora, float temperaturaRegistrada) {
        this.fechaHora = fechaHora;
        this.temperaturaRegistrada = temperaturaRegistrada;
    }
}
