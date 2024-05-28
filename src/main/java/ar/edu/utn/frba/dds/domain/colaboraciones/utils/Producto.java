package ar.edu.utn.frba.dds.domain.colaboraciones.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Producto class representa un producto.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
  private String nombre;
  private String urlFoto;
}