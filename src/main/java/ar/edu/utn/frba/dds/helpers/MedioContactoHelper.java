package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import java.util.List;
import java.util.Optional;


public class MedioContactoHelper {

  /**
   * @param contactos lista de contactos
   * @param canal     el canal que se quiere buscar
   * @return devuelve el valor del contacto de la lista de contactos segun el canal
   */
  public static String getValorContacto(List<MedioDeContacto> contactos, CanalContacto canal) {
    Optional<String> valor = contactos.stream().filter(medio -> medio.getCanal().equals(canal)).
        map(MedioDeContacto::getContacto).findFirst();
    return valor.orElse(null);
  }
}
