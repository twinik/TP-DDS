package ar.edu.utn.frba.dds.models.domain.permisos;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PermisosTest {
    @Test
    @DisplayName("el usuario entiende los permisos borrar y editar")
    public void testVerificarSiUsuarioTienePermisos() {
        Usuario u1 = new Usuario("fddf", "fdfd");
        Rol admin = new Rol();
        Permiso editar = new Permiso("edicion", "editar");
        Permiso borrar = new Permiso("edicion", "borrar");
        admin.agregarPermisos(editar, borrar);
        u1.agregarRoles(admin);
        Permiso visualizar = new Permiso("ver cositas", "visualizar");
        Rol veedor = new Rol();
        veedor.agregarPermisos(visualizar);
        Usuario u2 = new Usuario();
        u2.agregarRoles(veedor, admin);

        Assertions.assertTrue(u1.tenesPermiso(editar));
        Assertions.assertTrue(u1.tenesPermiso(borrar));
        Assertions.assertFalse(u1.tenesPermiso(visualizar));
        Assertions.assertTrue(u2.tenesPermiso(visualizar));
    }
}
