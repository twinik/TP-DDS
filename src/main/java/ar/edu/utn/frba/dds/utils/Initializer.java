package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoColaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersona;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.models.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IColocacionHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.IFormasColaboracionRespository;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.models.repositories.IModeloHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.IOfertaProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.IPermisosRepository;
import ar.edu.utn.frba.dds.models.repositories.IRolesRepository;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Inicializa datos prueba.
 */
public class Initializer {

  public static void init() {

    IUsuariosRepository usuariosRepository = ServiceLocator.get(IUsuariosRepository.class);
    IRolesRepository rolesRepository = ServiceLocator.get(IRolesRepository.class);
    IFormasColaboracionRespository formasColaboracionRespository = ServiceLocator.get(IFormasColaboracionRespository.class);
    IColaboradoresRepository colaboradoresRepository = ServiceLocator.get(IColaboradoresRepository.class);
    IHeladerasRepository heladerasRepository = ServiceLocator.get(IHeladerasRepository.class);
    IModeloHeladeraRepository modeloHeladeraRepository = ServiceLocator.get(IModeloHeladeraRepository.class);
    IOfertaProductoRepository ofertaProductoRepository = ServiceLocator.get(IOfertaProductoRepository.class);
    ITecnicosRepository tecnicosRepository = ServiceLocator.get(ITecnicosRepository.class);
    IAlertasRepository alertasRepository = ServiceLocator.get(IAlertasRepository.class);
    IColocacionHeladeraRepository colocacionHeladeraRepository = ServiceLocator.get(IColocacionHeladeraRepository.class);
    IPermisosRepository permisosRepository = ServiceLocator.get(IPermisosRepository.class);

    Usuario u1 = new Usuario("usuario@mail.com", "contra");
    Usuario u2 = new Usuario("usuuuario@mail.com", "1234");
    Usuario u3 = new Usuario("ong@mail.com", "pass");
    Permiso p1 = new Permiso("colaborar");
    Permiso p2 = new Permiso("colocar-heladeras");
    Permiso p3 = new Permiso("ofrecer-productos");
    Permiso p5 = new Permiso("alta-tecnico");
    Permiso p6 = new Permiso("alta-formulario");
    Permiso p7 = new Permiso("alta-modelo-heladera");

    permisosRepository.guardar(p5,p6,p7);

    Rol r1 = new Rol();
    r1.setNombre("colaborador");
    r1.agregarPermisos(p1);
    u1.agregarRoles(r1);
    Rol r2 = new Rol();
    r2.setNombre("persona juridica");
    r2.agregarPermisos(p2, p3);
    u2.agregarRoles(r2);
    u3.agregarRoles(r2);
    rolesRepository.guardar(r1, r2);
    usuariosRepository.guardar(u1);
    usuariosRepository.guardar(u2);
    usuariosRepository.guardar(u3);
    FormaColaboracion donacionDinero = new FormaColaboracion("DONACION_DINERO");
    FormaColaboracion donacionVianda = new FormaColaboracion("DONACION_VIANDA");
    FormaColaboracion redistribucionVianda = new FormaColaboracion("REDISTRIBUCION_VIANDA");
    FormaColaboracion registroPersona = new FormaColaboracion("REGISTRO_PERSONA");
    FormaColaboracion colocacionHeladeras = new FormaColaboracion("COLOCACION_HELADERA");

    formasColaboracionRespository.guardar(donacionVianda,donacionDinero,redistribucionVianda,registroPersona,colocacionHeladeras);

    TipoColaborador tipo = new TipoColaborador(TipoPersona.PERSONA_HUMANA, new ArrayList<>());
    tipo.getFormasPosiblesColaboracion().add(donacionDinero);
    tipo.getFormasPosiblesColaboracion().add(donacionVianda);

    MedioDeContacto wsp = new MedioDeContacto(CanalContacto.WHATSAPP, "+5491132458865");
    MedioDeContacto telegram = new MedioDeContacto(CanalContacto.TELEGRAM, "+5491132458865");
    MedioDeContacto email = new MedioDeContacto(CanalContacto.EMAIL, "jorgito@mail.com");

    MedioDeContacto emailEmpresa = new MedioDeContacto(CanalContacto.EMAIL, "mcdonalds@mail.com");

    List<MedioDeContacto> listaContacto = new ArrayList<>();
    listaContacto.add(wsp);
    listaContacto.add(email);
    listaContacto.add(telegram);
    List<MedioDeContacto> contactoEmpresa = new ArrayList<>();
    contactoEmpresa.add(emailEmpresa);

    Colaborador c1 = new Colaborador(u1, TipoDocumento.DNI, "4432653", tipo, 0f, null, new Direccion("medrano", 951, 3, "1405"), listaContacto, "jorge", "lopez", null, null, null);
    Colaborador sa = new Colaborador(u2, null, null, null, 0f, new ArrayList<>(), new Direccion("lavalle", 1500, 2, "1400"), contactoEmpresa, null, null, "comida", "MC DONALDS", TipoPersonaJuridica.EMPRESA);
    Colaborador ong = new Colaborador(u3, null, null, null, 0f, new ArrayList<>(), new Direccion("bonifacio", 30, 10, "1406"), null, null, null, "ong", "ONG RE SOLIDARIA", TipoPersonaJuridica.ONG);

    colaboradoresRepository.guardar(c1);
    colaboradoresRepository.guardar(sa);
    colaboradoresRepository.guardar(ong);

    ModeloHeladera modelo1 = new ModeloHeladera("philips", 3, 7);

    modeloHeladeraRepository.guardar(modelo1);

    Heladera h1 = new Heladera(new Ubicacion(-34.61178f, -58.417308f), new Direccion("medrano", 555, null, "1405"), "Heladera-medrano-dds", 50, LocalDate.now(), null, modelo1);
    Heladera h2 = new Heladera(new Ubicacion(-34.613466f, -58.419659f), new Direccion("lima", 800, null, "1405"), "Heladera-lima-dds", 50, LocalDate.now(), null, modelo1);
    Heladera h3 = new Heladera(new Ubicacion(-34.582345f, -58.43329f), new Direccion("rivadavia", 5000, null, "1407"), "Heladera-rivadavia-dds", 20, LocalDate.now(), null, modelo1);

    heladerasRepository.guardar(h1);
    heladerasRepository.guardar(h2);
    heladerasRepository.guardar(h3);

    ColocacionHeladeras colocoH1 = new ColocacionHeladeras(null, LocalDate.now(), h1);
    ColocacionHeladeras colocoH2 = new ColocacionHeladeras(null, LocalDate.now(), h2);
    ColocacionHeladeras colocoH3 = new ColocacionHeladeras(null, LocalDate.now(), h3);

    sa.agregarColocacionHeladera(colocoH3);
    sa.agregarColocacionHeladera(colocoH1);
    sa.agregarColocacionHeladera(colocoH2);

    colocacionHeladeraRepository.guardar(colocoH1);
    colocacionHeladeraRepository.guardar(colocoH2);
    colocacionHeladeraRepository.guardar(colocoH3);

    Alerta a1 = Alerta.of(h3, LocalDateTime.now(), null, null, TipoAlerta.FALLA_CONEXION);

    alertasRepository.guardar(a1);

    //TODO: PONER LAS FOTOS ACA

    OfertaProducto oferta1 = new OfertaProducto(ong, LocalDate.now(), new Producto("tostadora filgo", "https://whirlpoolarg.vtexassets.com/arquivos/ids/164249-800-auto?v=638146769908330000&width=800&height=auto&aspect=true"), 400f, CategoriaOferta.ARTICULOS_HOGAR);
    OfertaProducto oferta2 = new OfertaProducto(ong, LocalDate.now(), new Producto("martin fierro", "https://whirlpoolarg.vtexassets.com/arquivos/ids/164249-800-auto?v=638146769908330000&width=800&height=auto&aspect=true"), 1000f, CategoriaOferta.OTROS);

    ofertaProductoRepository.guardar(oferta1);
    ofertaProductoRepository.guardar(oferta2);


    Tecnico t1 = new Tecnico("juan", "fernandez", "34564992", TipoDocumento.DNI, new ArrayList<>(), new AreaDeCobertura(new Ubicacion(-34.61178f, -58.417308f), 20f));

    tecnicosRepository.guardar(t1);


  }

  public static void main(String[] args) {
    Initializer.init();
  }

  public static Optional<Colaborador> dameOng(){
    return ServiceLocator.get(IColaboradoresRepository.class).buscarTodos().stream().filter( c -> c.getRazonSocial() !=null).findFirst();
  }

  public static Optional<Colaborador> dameColaborador(){
    return ServiceLocator.get(IColaboradoresRepository.class).buscarTodos().stream().filter( c -> c.getRazonSocial() ==null).findFirst();
  }

}