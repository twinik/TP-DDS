package ar.edu.utn.frba.dds.domain.helpers.factories;

import ar.edu.utn.frba.dds.domain.helpers.ValidadorClaves;

/**
 * ValidadorFactory interface permite crear un validador de claves.
 */
public interface ValidadorFactory {
  ValidadorClaves crearValidador();
}
