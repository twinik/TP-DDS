package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.helpers.GeneradorDeCodigosHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.*;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.TipoCampo;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.models.domain.reportes.ReporteFallasHeladera;
import ar.edu.utn.frba.dds.models.domain.tarjetas.PosibleCodigoTarjeta;
import ar.edu.utn.frba.dds.models.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.models.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.utils.*;
import ar.edu.utn.frba.dds.models.repositories.*;
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
    IReportesRepository reportesRepository = ServiceLocator.get(IReportesRepository.class);

    Usuario u1 = new Usuario("usuario@mail.com", PasswordHasher.hashPassword("contra"));
    Usuario u2 = new Usuario("usuuuario@mail.com", PasswordHasher.hashPassword("1234"));
    Usuario u3 = new Usuario("ong@mail.com", PasswordHasher.hashPassword("pass"));
    Usuario u4 = new Usuario("admin@mail.com", PasswordHasher.hashPassword("admin"));
    Usuario u5 = new Usuario("admin2@mail.com", PasswordHasher.hashPassword("admin2"));
    Permiso p1 = new Permiso("Donar dinero", "donar-dinero");
    Permiso p2 = new Permiso("Colocar heladeras", "colocar-heladeras");
    Permiso p3 = new Permiso("Ofrecer productos", "ofrecer-productos");
    Permiso p5 = new Permiso("Alta tecnico", "alta-tecnico");
    Permiso p6 = new Permiso("Alta formulario", "alta-formulario");
    Permiso p7 = new Permiso("Alta modelo heladera", "alta-modelo-heladera");
    Permiso p8 = new Permiso("Canjear productos", "canjear-productos");
    Permiso p9 = new Permiso("Donar viandas", "donar-viandas");
    Permiso p10 = new Permiso("Redistribuir viandas", "redistribuir-viandas");
    Permiso p11 = new Permiso("Redistribuir viandas", "alta-vulnerable");
    Permiso p12 = new Permiso("Alta codigo de tarjetas", "alta-cod-tarjeta");
    Permiso p13 = new Permiso("Colaborador Base", "colaborador-base");

    permisosRepository.guardar(p1, p2, p3, p5, p6, p7, p8, p9, p10, p11, p12, p13);

    Rol r1 = new Rol();
    r1.setNombre("colaborador");
    r1.agregarPermisos(p1, p8, p10, p11, p9, p13);
    u1.agregarRoles(r1);
    Rol r2 = new Rol();
    r2.setNombre("persona juridica");
    r2.agregarPermisos(p2, p3, p8, p13);
    u2.agregarRoles(r2);
    u3.agregarRoles(r2);

    Rol r3 = new Rol();
    r3.setNombre("admin");
    r3.agregarPermisos(p5, p6, p7, p12);
    rolesRepository.guardar(r1, r2, r3);
    u4.agregarRoles(r3);
    Rol r4 = new Rol();
    r4.setNombre("admin2");
    r4.agregarPermisos(p5);
    rolesRepository.guardar(r4);
    u5.agregarRoles(r4);
    usuariosRepository.guardar(u1);
    usuariosRepository.guardar(u2);
    usuariosRepository.guardar(u3);
    usuariosRepository.guardar(u4);
    usuariosRepository.guardar(u5);
    FormaColaboracion donacionDinero = new FormaColaboracion("DONACION_DINERO", "Donación de dinero");
    FormaColaboracion donacionVianda = new FormaColaboracion("DONACION_VIANDA", "Donación de viandas");
    FormaColaboracion redistribucionVianda = new FormaColaboracion("REDISTRIBUCION_VIANDA", "Redistribución de viandas");
    FormaColaboracion registroPersona = new FormaColaboracion("REGISTRO_PERSONA", "Alta persona vulnerable");
    FormaColaboracion colocacionHeladeras = new FormaColaboracion("COLOCACION_HELADERA", "Hacerse cargo de heladeras");
    FormaColaboracion ofrecerProductos = new FormaColaboracion("OFRECER_PRODUCTOS", "Ofrecer productos");

    formasColaboracionRespository.guardar(donacionVianda, donacionDinero, redistribucionVianda, registroPersona, colocacionHeladeras, ofrecerProductos);

    TipoColaborador tipo = new TipoColaborador(TipoPersona.PERSONA_HUMANA, new ArrayList<>());
    tipo.getFormasPosiblesColaboracion().add(donacionDinero);
    tipo.getFormasPosiblesColaboracion().add(donacionVianda);

    TipoColaborador tipo2 = new TipoColaborador(TipoPersona.PERSONA_JURIDICA, new ArrayList<>());
    TipoColaborador tipo3 = new TipoColaborador(TipoPersona.PERSONA_JURIDICA, new ArrayList<>());

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

    Colaborador c1 = new Colaborador(u1, TipoDocumento.DNI, "4432653", tipo, 500f, null, new Direccion("medrano", 951, 3, "1405"), listaContacto, "jorge", "lopez", LocalDate.of(1999, 10, 10), null, null, null, true);
    Colaborador sa = new Colaborador(u2, null, null, tipo2, 0f, new ArrayList<>(), new Direccion("lavalle", 1500, 2, "1400"), contactoEmpresa, null, null, null, "comida", "MC DONALDS", TipoPersonaJuridica.EMPRESA, true);
    Colaborador ong = new Colaborador(u3, null, null, tipo3, 0f, new ArrayList<>(), new Direccion("bonifacio", 30, 10, "1406"), null, null, null, null, "ong", "ONG RE SOLIDARIA", TipoPersonaJuridica.ONG, true);

    colaboradoresRepository.guardar(c1);
    colaboradoresRepository.guardar(sa);
    colaboradoresRepository.guardar(ong);

    TarjetaColaborador tarjetita = TarjetaColaborador.of(c1, GeneradorDeCodigosHelper.generarAlfanumericoUnico(11));
    ServiceLocator.get(ITarjetasColaboradorRepository.class).guardar(tarjetita);

    ModeloHeladera modelo1 = new ModeloHeladera("philips-500", 3, 7);
    ModeloHeladera modelo2 = new ModeloHeladera("filgo-AX3", 2, 8);

    modeloHeladeraRepository.guardar(modelo1);
    modeloHeladeraRepository.guardar(modelo2);

    Heladera h1 = new Heladera(new Ubicacion(-34.61178f, -58.417308f), new Direccion("medrano", 555, null, "1405"), "Heladera-medrano-dds", 50, LocalDate.now(), 4, modelo1);
    h1.setHeladeraActiva(false);
    Heladera h2 = new Heladera(new Ubicacion(-34.613466f, -58.419659f), new Direccion("lima", 800, null, "1405"), "Heladera-lima-dds", 50, LocalDate.now(), 0, modelo1);
    Heladera h3 = new Heladera(new Ubicacion(-34.582345f, -58.43329f), new Direccion("rivadavia", 5000, null, "1407"), "Heladera-rivadavia-dds", 20, LocalDate.now(), 0, modelo1);

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

    OfertaProducto oferta1 = new OfertaProducto(ong, LocalDate.now(), new Producto("Tostadora Liliana", "/img/AT900_web_01.jpg"), 400f, CategoriaOferta.ARTICULOS_HOGAR);
    OfertaProducto oferta2 = new OfertaProducto(ong, LocalDate.now(), new Producto("Heladera Whirpool", "/img/164249-800-auto.png"), 1000f, CategoriaOferta.OTROS);
    OfertaProducto oferta3 = new OfertaProducto(ong, LocalDate.now(), new Producto("Licuadora Philips", "https://tccommercear.vtexassets.com/arquivos/ids/157998/HR2129-00_01.jpg.png?v=638327389196600000"), 500f, CategoriaOferta.ARTICULOS_HOGAR);
    OfertaProducto oferta4 = new OfertaProducto(ong, LocalDate.now(), new Producto("Cafetera Oster", "https://osterar.vtexassets.com/arquivos/ids/156927/BVSTEM5501B-1.jpg?v=638084445706070000"), 300f, CategoriaOferta.GASTRONOMIA);
    OfertaProducto oferta5 = new OfertaProducto(ong, LocalDate.now(), new Producto("Televisor Samsung 55\"", "https://samsungarsmb.vtexassets.com/arquivos/ids/161893-800-auto?v=638059845861700000&width=800&height=auto&aspect=true"), 1200f, CategoriaOferta.ELECTRONICA);
    OfertaProducto oferta6 = new OfertaProducto(ong, LocalDate.now(), new Producto("Parrilla eléctrica Tefal", "https://http2.mlstatic.com/D_NQ_NP_991452-MLC53213384867_012023-O.webp"), 600f, CategoriaOferta.ARTICULOS_HOGAR);
    OfertaProducto oferta7 = new OfertaProducto(ong, LocalDate.now(), new Producto("Aspiradora Bosch", "https://http2.mlstatic.com/D_NQ_NP_640979-MLA54003717348_022023-O.webp"), 700f, CategoriaOferta.ARTICULOS_HOGAR);
    OfertaProducto oferta8 = new OfertaProducto(ong, LocalDate.now(), new Producto("Celular iPhone 13", "https://www.cordobadigital.net/wp-content/uploads/2024/04/iPhone-13-128gb-Blue.png"), 1500f, CategoriaOferta.ELECTRONICA);
    OfertaProducto oferta9 = new OfertaProducto(ong, LocalDate.now(), new Producto("Consola PlayStation 5", "https://arsonyb2c.vtexassets.com/arquivos/ids/362074-800-800?v=638162215101070000&width=800&height=800&aspect=true"), 2000f, CategoriaOferta.ELECTRONICA);
    OfertaProducto oferta10 = new OfertaProducto(ong, LocalDate.now(), new Producto("Smartwatch Garmin", "https://res.garmin.com/transform/image/upload/b_rgb:FFFFFF,c_pad,dpr_2.0,f_auto,h_400,q_auto,w_400/c_pad,h_400,w_400/v1/Product_Images/en/products/010-02383-00/v/cf-xl-5a1beab7-1947-4a99-977f-5cbb6fbd488e?pgw=1"), 800f, CategoriaOferta.ELECTRONICA);

    ofertaProductoRepository.guardar(oferta1);
    ofertaProductoRepository.guardar(oferta2);
    ofertaProductoRepository.guardar(oferta3);
    ofertaProductoRepository.guardar(oferta4);
    ofertaProductoRepository.guardar(oferta5);
    ofertaProductoRepository.guardar(oferta6);
    ofertaProductoRepository.guardar(oferta7);
    ofertaProductoRepository.guardar(oferta8);
    ofertaProductoRepository.guardar(oferta9);
    ofertaProductoRepository.guardar(oferta10);

    Tecnico t1 = new Tecnico("juan", "fernandez", "34564992", TipoDocumento.DNI, new ArrayList<>(), new AreaDeCobertura(new Ubicacion(-34.61178f, -58.417308f), 20f));

    tecnicosRepository.guardar(t1);

    ReporteFallasHeladera reporteEjemplo = new ReporteFallasHeladera();
    reporteEjemplo.setRutaArchivo("/reportes/un-reporte.pdf");

    reportesRepository.guardar(reporteEjemplo);

    Formulario f = new Formulario();
    f.setNombre("formulario 1");
    f.setAutor(u5);
    f.agregarCampos(new Campo(TipoCampo.LIBRE, "Nos gustaria saber quien sos y que te gustaria lograr en la ONG", true),
        new Campo(TipoCampo.LIBRE, "¿Es la primera vez que participas en una ONG?¿Como llegaste a nosotros?", false));
    Campo compuesto = new Campo(TipoCampo.CHOICE, "¿Crees que tener conocimiento en el area de Diseño de Sistemas es importante para nuestro entorno?", true);
    compuesto.agregarOpciones(new Opcion("si"), new Opcion("no"));
    f.agregarCampos(compuesto);
    Campo multiple = new Campo(TipoCampo.MULTIPLE_CHOICE, "Las personas mostradas son nustros top 4 colaboradores. Marque aquellos que conozca", true);
    multiple.agregarOpciones(new Opcion("uri"), new Opcion("manu"), new Opcion("tobi"), new Opcion("thomi"));
    f.agregarCampos(multiple);
    ServiceLocator.get(IFormularioRepository.class).guardar(f);

    PosibleCodigoTarjeta codigo1 = new PosibleCodigoTarjeta(GeneradorDeCodigosHelper.generarAlfanumericoUnico(11));
    PosibleCodigoTarjeta codigo2 = new PosibleCodigoTarjeta(GeneradorDeCodigosHelper.generarAlfanumericoUnico(11));

    PosibleCodigoTarjeta codigo3 = new PosibleCodigoTarjeta(GeneradorDeCodigosHelper.generarAlfanumericoUnico(11));

    PosibleCodigoTarjeta codigo4 = new PosibleCodigoTarjeta(GeneradorDeCodigosHelper.generarAlfanumericoUnico(11));

    IPosiblesCodigosTarjetaRepository posiblesCodigosTarjetaRepository = ServiceLocator.get(IPosiblesCodigosTarjetaRepository.class);
    posiblesCodigosTarjetaRepository.guardar(codigo1);
    posiblesCodigosTarjetaRepository.guardar(codigo2);
    posiblesCodigosTarjetaRepository.guardar(codigo3);
    posiblesCodigosTarjetaRepository.guardar(codigo4);


    MotivoRedistribucionVianda mot1 = new MotivoRedistribucionVianda("Redistribuir viandas por capacidad");
    MotivoRedistribucionVianda mot2 = new MotivoRedistribucionVianda("Redistribuir viandas de heladera inahbilitada");
    MotivoRedistribucionVianda mot3 = new MotivoRedistribucionVianda("Otro");

    IMotivoRedistribucionRepository redistribucionRepository = ServiceLocator.get(IMotivoRedistribucionRepository.class);
    redistribucionRepository.guardar(mot1);
    redistribucionRepository.guardar(mot2);
    redistribucionRepository.guardar(mot3);


  }

  public static void main(String[] args) {
    Initializer.init();
  }

  public static Optional<Colaborador> dameOng() {
    return ServiceLocator.get(IColaboradoresRepository.class).buscarTodos().stream().filter(c -> c.getRazonSocial() != null).findFirst();
  }

  public static Optional<Colaborador> dameColaborador() {
    return ServiceLocator.get(IColaboradoresRepository.class).buscarTodos().stream().filter(c -> c.getRazonSocial() == null).findFirst();
  }

}