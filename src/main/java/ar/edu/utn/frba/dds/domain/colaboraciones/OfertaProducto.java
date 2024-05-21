package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.TipoPersona;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * oferta que hacen las personas juridicas.
 */
@Getter
@AllArgsConstructor
public class OfertaProducto extends Colaboracion {
  private Producto producto;
  private Float puntosNecesarios;
  private CategoriaOferta categoria;

  public void efectuar() {}

  public boolean puedeSerCanjeadoPor(Colaborador colaborador){
    if (colaborador.getTipoColaborador().getTipo().equals(TipoPersona.PERSONA_JURIDICA)) {
      Float sumaMesesActivas = (float) colaborador.getHeladerasColocadas().stream().mapToDouble(ColocacionHeladeras::getMesesActiva).sum();
      Float puntosPorHeladerasColocadas = colaborador.getHeladerasColocadas().size() * sumaMesesActivas * 5;

      if (colaborador.getPuntosGanados() + puntosPorHeladerasColocadas >= this.puntosNecesarios) {
        colaborador.sumarPuntos(puntosPorHeladerasColocadas);
      }
    }
    return colaborador.getPuntosGanados() >= this.puntosNecesarios;
  }

}