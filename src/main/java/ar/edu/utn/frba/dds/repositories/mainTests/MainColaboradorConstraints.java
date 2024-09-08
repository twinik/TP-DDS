package ar.edu.utn.frba.dds.repositories.mainTests;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;

public class MainColaboradorConstraints {

    public static void main(String[] args) {
        IColaboradoresRepository repo = ServiceLocator.get("colaboradoresRepository", IColaboradoresRepository.class);

        Colaborador c1 = new Colaborador();
        Colaborador c2 = new Colaborador();
        Usuario u1 = new Usuario("email@email.com", "clave");
        Usuario u2 = new Usuario("email2@email.com", "clave");

        c1.setUsuario(u1);
        c2.setUsuario(u2);

        c1.setNombre("juancito");
        c1.setTipoDocumento(TipoDocumento.DNI);
        c1.setDocumento("12345678");

        c2.setNombre("jorgito");
        c2.setTipoDocumento(TipoDocumento.DNI);
        c2.setDocumento("12345678");

        repo.guardar(c1);
        repo.guardar(c2);
        // Deberia fallar y solo queda c1 y u1 en la base
    }
}