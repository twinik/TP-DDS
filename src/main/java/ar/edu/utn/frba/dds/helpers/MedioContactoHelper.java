package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import java.util.List;
import java.util.Optional;

// TODO: TESTEAR
public class MedioContactoHelper {
  public static String getValorContacto(List<MedioDeContacto> contactos, CanalContacto canal){
    Optional<String> valor = contactos.stream().filter(medio -> medio.getCanal().equals(canal)).
        map(MedioDeContacto::getContacto).findFirst();
    return valor.orElse(null);
  }
}
