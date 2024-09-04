package ar.edu.utn.frba.dds.repositories.mainTests;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CanjeProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.repositories.ICanjeProductoRepository;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.IOfertaProductoRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainCanje {

    public static void main(String[] args) {
        IOfertaProductoRepository ofertasRepo = ServiceLocator.get("ofertasProductoRepository", IOfertaProductoRepository.class);
        IColaboradoresRepository colabRepo = ServiceLocator.get("colaboradoresRepository", IColaboradoresRepository.class);
        ICanjeProductoRepository repo = ServiceLocator.get("canjesRepository", ICanjeProductoRepository.class);
        Colaborador c = new Colaborador();
        c.setRazonSocial("empresita");
        c.setUsuario(new Usuario("fdkfdf", "fkdmmfd"));
        Colaborador comprador = new Colaborador();
        comprador.setNombre("juancito");
        comprador.setPuntosGanados(500f);
        comprador.setUsuario(new Usuario("fdfs", "fkdmmfd"));

        colabRepo.guardar(c);
        colabRepo.guardar(comprador);

        OfertaProducto of = new OfertaProducto();
        of.setColaborador(c);
        of.setFechaCreacion(LocalDate.now());
        of.setPuntosNecesarios(200f);
        of.setProducto(new Producto("prod", "fdfdf"));

        ofertasRepo.guardar(of);

        CanjeProducto canje = new CanjeProducto(comprador, of, LocalDateTime.now(), 200f);

        comprador.restarPuntos(canje.getPuntosGastados());

        repo.guardar(canje);


        //CanjeProducto
    }

}
