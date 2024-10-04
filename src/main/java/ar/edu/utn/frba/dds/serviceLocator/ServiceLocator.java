package ar.edu.utn.frba.dds.serviceLocator;

import ar.edu.utn.frba.dds.controllers.IncidentesController;
import ar.edu.utn.frba.dds.controllers.LoginController;
import ar.edu.utn.frba.dds.controllers.OfertasProductoController;
import ar.edu.utn.frba.dds.controllers.RegistroController;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.models.repositories.*;
import ar.edu.utn.frba.dds.models.repositories.imp.*;
import ar.edu.utn.frba.dds.services.AlertasService;
import ar.edu.utn.frba.dds.services.FallasTecnicasService;
import ar.edu.utn.frba.dds.services.HeladerasService;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.services.UsuarioService;
import java.util.HashMap;
import java.util.Map;

/**
 * ServiceLocator utilizado para obtener repositorios
 */
public class ServiceLocator {

  private static final Map<Class<?>, Object> services = new HashMap<>();

  public static void add(Class<?> clase, Object service) {
    services.put(clase, service);
  }

  @SuppressWarnings("unchecked")
  public static <T> T get(Class<T> clase) {
    if (!existeService(clase)) {
      if (clase.equals(AlertasService.class))
        add(clase, new AlertasService(get(IAlertasRepository.class)));
      else if (clase.equals(IAlertasRepository.class))
        add(clase, new AlertasRepository());
      else if (clase.equals(IAltaPersonaVulnerableRepository.class))
        add(clase, new AltaPersonaVulnerableRepository());
      else if (clase.equals(IAperturasHeladeraRepository.class))
        add(clase, new AperturasHeladeraRepository());
      else if (clase.equals(ICalculadorPuntos.class))
        add(clase, new CalculadorPuntos());
      else if (clase.equals(ICampoRepository.class))
        add(clase, new CampoRepository());
      else if (clase.equals(ICanjeProductoRepository.class))
        add(clase, new CanjeProductoRepository());
      else if (clase.equals(IColaboradoresRepository.class))
        add(clase, new ColaboradoresRepository());
      else if (clase.equals(IColocacionHeladeraRepository.class))
        add(clase, new ColocacionHeladeraRepository());
      else if (clase.equals(IDonacionDineroRepository.class))
        add(clase, new DonacionDineroRepository());
      else if (clase.equals(IDonacionesViandaRepository.class))
        add(clase, new DonacionesViandaRepository());
      else if (clase.equals(FallasTecnicasService.class))
        add(clase, new FallasTecnicasService(get(IFallasTecnicasRepository.class)));
      else if (clase.equals(IFallasTecnicasRepository.class))
        add(clase, new FallasTecnicasRepository());
      else if (clase.equals(IFormasColaboracionRespository.class))
        add(clase, new FormasColaboracionRespository());
      else if (clase.equals(IFormularioRepository.class))
        add(clase, new FormularioRepository());
      else if (clase.equals(IHeladerasRepository.class))
        add(clase, new HeladeraRepository());
      else if (clase.equals(HeladerasService.class))
        add(clase, new HeladerasService(get(IHeladerasRepository.class)));
      else if (clase.equals(IMedioContactoRepository.class))
        add(clase, new MedioContactoRepository());
      else if (clase.equals(IModeloHeladeraRepository.class))
        add(clase, new ModeloHeladeraRepository());
      else if (clase.equals(IMotivoRedistribucionRepository.class))
        add(clase, new MotivoRedistribucionRepository());
      else if (clase.equals(IOfertaProductoRepository.class))
        add(clase, new OfertaProductoRepository());
      else if (clase.equals(OfertasProductoService.class))
        add(clase, new OfertasProductoService(get(IOfertaProductoRepository.class), get(IColaboradoresRepository.class)));
      else if (clase.equals(IOpcionRepository.class))
        add(clase, new OpcionRepository());
      else if (clase.equals(IPermisosRepository.class))
        add(clase, new PermisosRepository());
      else if (clase.equals(IPersonaVulnerableRepository.class))
        add(clase, new PersonaVulnerableRepository());
      else if (clase.equals(IProductoRepository.class))
        add(clase, new ProductoRepository());
      else if (clase.equals(RecomendadorHeladeras.class))
        add(clase, new RecomendadorHeladeras());
      else if (clase.equals(IRedistribucionesViandaRepository.class))
        add(clase, new RedistribucionesViandaRepository());
      else if (clase.equals(IRegistrosTemperaturaRepository.class))
        add(clase, new RegistrosTemperaturaRepository());
      else if (clase.equals(ReportesFactory.class))
        add(clase, new ReportesFactory(get(IViandasRepository.class),
            get(IDonacionesViandaRepository.class),
            get(IRedistribucionesViandaRepository.class),
            get(IFallasTecnicasRepository.class),
            get(IAlertasRepository.class)));
      else if (clase.equals(IReportesRepository.class))
        add(clase, new ReportesRepository());
      else if (clase.equals(IRespuestasCampoRepository.class))
        add(clase, new RespuestasCampoRepository());
      else if (clase.equals(IRespuestasFormularioRepository.class))
        add(clase, new RespuestasFormularioRepository());
      else if (clase.equals(IRolesRepository.class))
        add(clase, new RolesRepository());
      else if (clase.equals(ISensorMovimientoRepository.class))
        add(clase, new SensoresMovimientoRepository());
      else if (clase.equals(ISensorTemperaturaRepository.class))
        add(clase, new SensoresTemperaturaRepository());
      else if (clase.equals(ISolicitudesAperturaHeladeraRepository.class))
        add(clase, new SolicitudesAperturaHeladeraRepository());
      else if (clase.equals(ISuscripcionesRepository.class))
        add(clase, new SuscripcionesRepository());
      else if (clase.equals(ITarjetasColaboradorRepository.class))
        add(clase, new TarjetasColaboradorRepository());
      else if (clase.equals(ITarjetasRepository.class))
        add(clase, new TarjetaRepository());
      else if (clase.equals(ITecnicosRepository.class))
        add(clase, new TecnicosRepository());
      else if (clase.equals(IUsosTarjetaRepository.class))
        add(clase, new UsosTarjetaRepository());
      else if (clase.equals(UsuarioService.class))
        add(UsuarioService.class, new UsuarioService(get(IUsuariosRepository.class)));
      else if (clase.equals(IUsuariosRepository.class))
        add(clase, new UsuariosRepository());
      else if (clase.equals(IViandasRepository.class))
        add(clase, new ViandasRepository());
      else if (clase.equals(IVisitasTecnicoRepository.class))
        add(clase, new VisitasTecnicoRepository());


        // CONTROLLERS
      else if (clase.equals(LoginController.class))
        add(LoginController.class, new LoginController(get(UsuarioService.class)));
      else if (clase.equals(RegistroController.class))
        add(RegistroController.class, new RegistroController(get(UsuarioService.class)));
      else if (clase.equals(IncidentesController.class))
        add(clase, new IncidentesController(get(AlertasService.class), get(FallasTecnicasService.class)));
      else if (clase.equals(OfertasProductoController.class))
        add(clase, new OfertasProductoController(get(OfertasProductoService.class)));
      else throw new IllegalArgumentException("No hay servicio provisto para esa clase");


//
//
//
//
//
//
//                case IAlertasRepository.class.getName() -> add("alertasRepository", new AlertasRepository());
//                case "altaPersonaVulnerableRepository" ->
//                        add("altaPersonaVulnerableRepository", new AltaPersonaVulnerableRepository());
//                case "aperturasHeladeraRepository" ->
//                        add("aperturasHeladeraRepository", new AperturasHeladeraRepository());
//                case "calculadorPuntos" -> add("calculadorPuntos", new CalculadorPuntos());
//                case "campoRepository" -> add("campoRepository", new CampoRepository());
//                case "canjesRepository" -> add("canjesRepository", new CanjeProductoRepository());
//                case "colaboradoresRepository" -> add("colaboradoresRepository", new ColaboradoresRepository());
//                case "colocacionHeladeraRepository" ->
//                        add("colocacionHeladeraRepository", new ColocacionHeladeraRepository());
//                case "donacionDineroRepository" -> add("donacionDineroRepository", new DonacionDineroRepository());
//                case "donacionesViandaRepository" ->
//                        add("donacionesViandaRepository", new DonacionesViandaRepository());
//                case "fallasTecnicasRepository" -> add("fallasTecnicasRepository", new FallasTecnicasRepository());
//                case "formasColaboracionRepository" ->
//                        add("formasColaboracionRepository", new FormasColaboracionRespository());
//                case "formulariosRepository" -> add("formulariosRepository", new FormularioRepository());
//                case "heladerasRepository" -> add("heladerasRepository", new HeladeraRepository());
//                case "mediosDeContactoRepository" -> add("mediosDeContactoRepository", new MedioContactoRepository());
//                case "modelosHeladeraRepository" -> add("modelosHeladeraRepository", new ModeloHeladeraRepository());
//                case "motivosRedistribucionRepository" ->
//                        add("motivosRedistribucionRepository", new MotivoRedistribucionRepository());
//                case "ofertasProductoRepository" -> add("ofertasProductoRepository", new OfertaProductoRepository());
//                case "opcionesRepository" -> add("opcionesRepository", new OpcionRepository());
//                case "personasVulnerablesRepository" ->
//                        add("personasVulnerablesRepository", new PersonaVulnerableRepository());
//                case "productosRepository" -> add("productosRepository", new ProductoRepository());
//                case "recomendadorHeladeras" -> add("recomendadorHeladeras", new RecomendadorHeladeras());
//                case "redistribucionesViandaRepository" ->
//                        add("redistribucionesViandaRepository", new RedistribucionesViandaRepository());
//                case "registrosTemperaturaRepository" ->
//                        add("registrosTemperaturaRepository", new RegistrosTemperaturaRepository());
//                case "reportesFactory" ->
//                        add("reportesFactory", new ReportesFactory(get("viandasRepository", IViandasRepository.class),
//                                get("donacionesViandaRepository", IDonacionesViandaRepository.class),
//                                get("redistribucionesViandaRepository", IRedistribucionesViandaRepository.class),
//                                get("fallasTecnicasRepository", IFallasTecnicasRepository.class),
//                                get("alertasRepository", IAlertasRepository.class)));
//                case "reportesRepository" -> add("reportesRepository", new ReportesRepository());
//                case "respuestasCampoRepository" -> add("respuestasCampoRepository", new RespuestasCampoRepository());
//                case "respuestasFormularioRepository" ->
//                        add("respuestasFormularioRepository", new RespuestasFormularioRepository());
//                case "sensoresMovimientoRepository" ->
//                        add("sensoresMovimientoRepository", new SensoresMovimientoRepository());
//                case "sensoresTemperaturaRepository" ->
//                        add("sensoresTemperaturaRepository", new SensoresTemperaturaRepository());
//                case "solicitudesAperturaHeladeraRepository" ->
//                        add("solicitudesAperturaHeladeraRepository", new SolcitudesAperturaHeladeraRepository());
//                case "suscripcionesRepository" -> add("suscripcionesRepository", new SuscripcionesRepository());
//                case "tarjetasColaboradorRepository" ->
//                        add("tarjetasColaboradorRepository", new TarjetasColaboradorRepository());
//                case "tarjetasRepository" -> add("tarjetasRepository", new TarjetaRepository());
//                case "tecnicosRepository" -> add("tecnicosRepository", new TecnicosRepository());
//                case "usosTarjetaRepository" -> add("usosTarjetaRepository", new UsosTarjetaRepository());
//                case "usuariosRepository" -> add("usuariosRepository", new UsuariosRepository());
//                case "viandasRepository" -> add("viandasRepository", new ViandasRepository());
//                case "visitasTecnicoRepository" -> add("visitasTecnicoRepository", new VisitasTecnicoRepository());
//                default -> throw new IllegalArgumentException("nombre invalido");

    }
    return (T) services.get(clase);
  }

  private static boolean existeService(Class<?> clase) {
    return services.containsKey(clase);
  }
}
