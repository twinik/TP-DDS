package ar.edu.utn.frba.dds.models.domain.tarjetas;

import ar.edu.utn.frba.dds.helpers.GeneradorDeCodigosHelper;
import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "posible_codigo_tarjeta")
public class PosibleCodigoTarjeta extends EntidadPersistente {
    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;
    @Column(name = "ocuapdo")
    private boolean ocupado = false;

    public PosibleCodigoTarjeta(String codigo) {
        if (!GeneradorDeCodigosHelper.esCodigoValido(codigo, 11))
            throw new CodigoInvalidoException("El codigo no es valido");
        this.codigo = codigo;
    }

    public void ocupar() {
        this.ocupado = true;
    }

}
