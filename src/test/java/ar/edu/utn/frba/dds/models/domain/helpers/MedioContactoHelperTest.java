package ar.edu.utn.frba.dds.models.domain.helpers;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.helpers.MedioContactoHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MedioContactoHelperTest {

    @Test
    @DisplayName("obtener valor de un contacto")
    void testObtenerValorContacto() {
        Colaborador colaborador = new Colaborador();
        colaborador.agregarMedioContacto(new MedioDeContacto(CanalContacto.EMAIL, "hola@mail"), new MedioDeContacto(CanalContacto.TELEGRAM, "UNID"));
        Assertions.assertEquals(MedioContactoHelper.getValorContacto(colaborador.getMedioContacto(), CanalContacto.EMAIL), "hola@mail");
        Assertions.assertEquals(MedioContactoHelper.getValorContacto(colaborador.getMedioContacto(), CanalContacto.TELEGRAM), "UNID");
        Assertions.assertNull(MedioContactoHelper.getValorContacto(colaborador.getMedioContacto(), CanalContacto.WHATSAPP));
    }
}
