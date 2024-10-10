package ar.edu.utn.frba.dds.serviceLocator;

import ar.edu.utn.frba.dds.controllers.*;
import ar.edu.utn.frba.dds.externapi.RecomendacionDonaciones;
import ar.edu.utn.frba.dds.externapi.RecomendadorDonacionesRetrofitAdapter;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.heladeras.CalculadorHeladerasCercanas;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.repositories.*;
import ar.edu.utn.frba.dds.models.repositories.imp.*;
import ar.edu.utn.frba.dds.services.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ServiceLocator utilizado para obtener los servicios.
 */
public class ServiceLocator {

  private static final Map<Class<?>, Object> services = new HashMap<>();

  public static void add(Class<?> clase, Object service) {
    services.put(clase, service);
  }

  @SuppressWarnings("unchecked")
  public static <T> T get(Class<T> clase) {
    if (!existeService(clase)) {

      // REPOSITORIOS
      if (clase.equals(IAlertasRepository.class)) add(clase, new AlertasRepository());
      else if (clase.equals(IAltaPersonaVulnerableRepository.class)) add(clase, new AltaPersonaVulnerableRepository());
      else if (clase.equals(IAperturasHeladeraRepository.class)) add(clase, new AperturasHeladeraRepository());
      else if (clase.equals(ICampoRepository.class)) add(clase, new CampoRepository());
      else if (clase.equals(ICanjeProductoRepository.class)) add(clase, new CanjeProductoRepository());
      else if (clase.equals(IColaboradoresRepository.class)) add(clase, new ColaboradoresRepository());
      else if (clase.equals(IColocacionHeladeraRepository.class)) add(clase, new ColocacionHeladeraRepository());
      else if (clase.equals(IDonacionDineroRepository.class)) add(clase, new DonacionDineroRepository());
      else if (clase.equals(IDonacionesViandaRepository.class)) add(clase, new DonacionesViandaRepository());
      else if (clase.equals(IFallasTecnicasRepository.class)) add(clase, new FallasTecnicasRepository());
      else if (clase.equals(IFormasColaboracionRespository.class)) add(clase, new FormasColaboracionRespository());
      else if (clase.equals(IFormularioRepository.class)) add(clase, new FormularioRepository());
      else if (clase.equals(IHeladerasRepository.class)) add(clase, new HeladeraRepository());
      else if (clase.equals(IMedioContactoRepository.class)) add(clase, new MedioContactoRepository());
      else if (clase.equals(IModeloHeladeraRepository.class)) add(clase, new ModeloHeladeraRepository());
      else if (clase.equals(IMotivoRedistribucionRepository.class)) add(clase, new MotivoRedistribucionRepository());
      else if (clase.equals(IOfertaProductoRepository.class)) add(clase, new OfertaProductoRepository());
      else if (clase.equals(IOpcionRepository.class)) add(clase, new OpcionRepository());
      else if (clase.equals(IPermisosRepository.class)) add(clase, new PermisosRepository());
      else if (clase.equals(IPersonaVulnerableRepository.class)) add(clase, new PersonaVulnerableRepository());
      else if (clase.equals(IPosiblesCodigosTarjetaRepository.class))
        add(clase, new PosiblesCodigosTarjetaRepository());
      else if (clase.equals(IProductoRepository.class)) add(clase, new ProductoRepository());
      else if (clase.equals(IRedistribucionesViandaRepository.class))
        add(clase, new RedistribucionesViandaRepository());
      else if (clase.equals(IRegistrosTemperaturaRepository.class)) add(clase, new RegistrosTemperaturaRepository());
      else if (clase.equals(IReportesRepository.class)) add(clase, new ReportesRepository());
      else if (clase.equals(IRespuestasCampoRepository.class)) add(clase, new RespuestasCampoRepository());
      else if (clase.equals(IRespuestasFormularioRepository.class)) add(clase, new RespuestasFormularioRepository());
      else if (clase.equals(IRolesRepository.class)) add(clase, new RolesRepository());
      else if (clase.equals(ISensorMovimientoRepository.class)) add(clase, new SensoresMovimientoRepository());
      else if (clase.equals(ISensorTemperaturaRepository.class)) add(clase, new SensoresTemperaturaRepository());
      else if (clase.equals(ISolicitudesAperturaHeladeraRepository.class))
        add(clase, new SolicitudesAperturaHeladeraRepository());
      else if (clase.equals(ISuscripcionesRepository.class)) add(clase, new SuscripcionesRepository());
      else if (clase.equals(ITarjetasColaboradorRepository.class)) add(clase, new TarjetasColaboradorRepository());
      else if (clase.equals(ITarjetasRepository.class)) add(clase, new TarjetaRepository());
      else if (clase.equals(ITecnicosRepository.class)) add(clase, new TecnicosRepository());
      else if (clase.equals(IUsosTarjetaRepository.class)) add(clase, new UsosTarjetaRepository());
      else if (clase.equals(IUsuariosRepository.class)) add(clase, new UsuariosRepository());
      else if (clase.equals(IViandasRepository.class)) add(clase, new ViandasRepository());

      // SERVICIOS
      else if (clase.equals(AlertasService.class)) add(clase, new AlertasService(get(IAlertasRepository.class)));
      else if (clase.equals(AltaPersonaVulnerableService.class))
        add(clase, new AltaPersonaVulnerableService(get(IPersonaVulnerableRepository.class), get(IAltaPersonaVulnerableRepository.class), get(ColaboradoresService.class), get(ICalculadorPuntos.class), get(TarjetasService.class)));
      else if (clase.equals(CalculadorHeladerasCercanas.class)) {
        try {
          add(clase, new CalculadorHeladerasCercanas(get(IHeladerasRepository.class), Integer.parseInt(new ConfigReader("config.properties").getProperty("LIMITE_HELADERAS_CERCANAS"))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else if (clase.equals(ICalculadorPuntos.class)) add(clase, new CalculadorPuntos());
      else if (clase.equals(CargaMasivaService.class))
        add(clase, new CargaMasivaService(get(FileUploadService.class), get(IColaboradoresRepository.class), get(IFormasColaboracionRespository.class), get(ICalculadorPuntos.class)));
      else if (clase.equals(ColaboradoresService.class))
        add(clase, new ColaboradoresService(get(IColaboradoresRepository.class), get(MedioContactoService.class), get(FormaColaboracionService.class), get(RolesService.class)));
      else if (clase.equals(ColocacionHeladerasService.class))
        add(clase, new ColocacionHeladerasService(get(IColocacionHeladeraRepository.class), get(ColaboradoresService.class), get(ModelosService.class), get(CalculadorHeladerasCercanas.class), get(IHeladerasRepository.class)));
      else if (clase.equals(DonacionDineroService.class))
        add(clase, new DonacionDineroService(get(IDonacionDineroRepository.class), get(ColaboradoresService.class), get(ICalculadorPuntos.class)));
      else if (clase.equals(FallasTecnicasService.class))
        add(clase, new FallasTecnicasService(get(IFallasTecnicasRepository.class), get(ColaboradoresService.class), get(HeladerasService.class)));
      else if (clase.equals(FileUploadService.class)) add(clase, new FileUploadService());
      else if (clase.equals(FormaColaboracionService.class))
        add(clase, new FormaColaboracionService(get(IFormasColaboracionRespository.class)));
      else if (clase.equals(FormulariosService.class))
        add(clase, new FormulariosService(get(IFormularioRepository.class)));
      else if (clase.equals(HeladerasService.class)) add(clase, new HeladerasService(get(IHeladerasRepository.class)));
      else if (clase.equals(MedioContactoService.class)) add(clase, new MedioContactoService());
      else if (clase.equals(ModelosService.class)) add(clase, new ModelosService(get(IModeloHeladeraRepository.class)));
      else if (clase.equals(OfertasProductoService.class))
        add(clase, new OfertasProductoService(get(IOfertaProductoRepository.class), get(ColaboradoresService.class)));
      else if (clase.equals(PosiblesCodigosService.class)) {
        add(clase, new PosiblesCodigosService(get(IPosiblesCodigosTarjetaRepository.class)));
      } else if (clase.equals(RecomendadorHeladeras.class)) add(clase, new RecomendadorHeladeras());
      else if (clase.equals(ReportesFactory.class))
        add(clase, new ReportesFactory(get(IViandasRepository.class), get(IDonacionesViandaRepository.class), get(IRedistribucionesViandaRepository.class), get(IFallasTecnicasRepository.class), get(IAlertasRepository.class)));
      else if (clase.equals(RespuestaFormularioService.class))
        add(clase, new RespuestaFormularioService(get(IRespuestasFormularioRepository.class)));
      else if (clase.equals(RolesService.class)) add(clase, new RolesService(get(IRolesRepository.class)));
      else if (clase.equals(SuscripcionesServices.class))
        add(clase, new SuscripcionesServices(get(ISuscripcionesRepository.class)));
      else if (clase.equals(TarjetasService.class)) add(clase, new TarjetasService(get(ITarjetasRepository.class)));
      else if (clase.equals(TecnicosHelper.class)) add(clase, new TecnicosHelper(get(ITecnicosRepository.class)));
      else if (clase.equals(TecnicosService.class))
        add(clase, new TecnicosService(get(ITecnicosRepository.class), get(MedioContactoService.class)));
      else if (clase.equals(TipoDocumentoMapper.class)) add(clase, new TipoDocumentoMapper());
      else if (clase.equals(UsuarioService.class))
        add(clase, new UsuarioService(get(IUsuariosRepository.class), get(ColaboradoresService.class)));

      // CONTROLADORES
      else if (clase.equals(AlertasController.class)) add(clase, new AlertasController(get(AlertasService.class)));
      else if (clase.equals(AltaPersonaVulnerableController.class))
        add(clase, new AltaPersonaVulnerableController(get(AltaPersonaVulnerableService.class)));
      else if (clase.equals(CargaMasivaController.class))
        add(clase, new CargaMasivaController(get(CargaMasivaService.class)));
      else if (clase.equals(ColocacionHeladerasController.class))
        add(clase, new ColocacionHeladerasController(get(ColocacionHeladerasService.class), get(ModelosService.class)));
      else if (clase.equals(DonacionDineroController.class))
        add(clase, new DonacionDineroController(get(DonacionDineroService.class)));
      else if (clase.equals(FallasTecnicasController.class))
        add(clase, new FallasTecnicasController(get(FallasTecnicasService.class), get(FileUploadService.class)));
      else if (clase.equals(FormulariosController.class))
        add(clase, new FormulariosController(get(FormulariosService.class)));
      else if (clase.equals(LoginController.class)) add(clase, new LoginController(get(UsuarioService.class)));
      else if (clase.equals(LogoutController.class)) add(clase, new LogoutController());
      else if (clase.equals(ModelosHeladeraController.class))
        add(clase, new ModelosHeladeraController(get(ModelosService.class)));
      else if (clase.equals(OfertasProductoController.class))
        add(clase, new OfertasProductoController(get(OfertasProductoService.class), get(FileUploadService.class)));
      else if (clase.equals(RegistroController.class))
        add(clase, new RegistroController(get(UsuarioService.class), get(ColaboradoresService.class), get(FormaColaboracionService.class)));
      else if (clase.equals(RespuestaFormularioController.class))
        add(clase, new RespuestaFormularioController(get(RespuestaFormularioService.class), get(FormulariosService.class)));
      else if (clase.equals(PosiblesCodigosTarjetasController.class))
        add(clase, new PosiblesCodigosTarjetasController(get(PosiblesCodigosService.class)));
      else if (clase.equals(TecnicosController.class)) add(clase, new TecnicosController(get(TecnicosService.class)));
      else if (clase.equals(RecomendacionesController.class)) {
        try {
          add(clase, new RecomendacionesController(new RecomendacionDonaciones(RecomendadorDonacionesRetrofitAdapter.getInstance())));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else if (clase.equals(SuscripcionesController.class)) add(clase, new SuscripcionesController());
      else throw new IllegalArgumentException("No hay servicio provisto para esa clase");
    }
    return (T) services.get(clase);
  }


//  public static <T> T get(Class<T> clase) {
//    if (!existeService(clase)) {
//      if (clase.equals(AlertasService.class))
//        add(clase, new AlertasService(get(IAlertasRepository.class)));
//      else if (clase.equals(IAlertasRepository.class))
//        add(clase, new AlertasRepository());
//      else if (clase.equals(IAltaPersonaVulnerableRepository.class))
//        add(clase, new AltaPersonaVulnerableRepository());
//      else if (clase.equals(IAperturasHeladeraRepository.class))
//        add(clase, new AperturasHeladeraRepository());
//      else if (clase.equals(CalculadorHeladerasCercanas.class)) {
//        try {
//          add(clase, new CalculadorHeladerasCercanas(get(IHeladerasRepository.class), Integer.parseInt(new ConfigReader("config.properties").getProperty("LIMITE_HELADERAS_CERCANAS"))));
//        } catch (IOException e) {
//          throw new RuntimeException(e);
//        }
//      } else if (clase.equals(ICalculadorPuntos.class))
//        add(clase, new CalculadorPuntos());
//      else if (clase.equals(ICampoRepository.class))
//        add(clase, new CampoRepository());
//      else if (clase.equals(ICanjeProductoRepository.class))
//        add(clase, new CanjeProductoRepository());
//      else if (clase.equals(IColaboradoresRepository.class))
//        add(clase, new ColaboradoresRepository());
//      else if (clase.equals(ColaboradoresService.class))
//        add(clase, new ColaboradoresService(get(IColaboradoresRepository.class)));
//      else if (clase.equals(ColocacionHeladerasService.class))
//        add(clase, new ColocacionHeladerasService(get(IColocacionHeladeraRepository.class),
//            get(ColaboradoresService.class),
//            get(ModelosService.class),
//            get(CalculadorHeladerasCercanas.class),
//            get(IHeladerasRepository.class)));
//      else if (clase.equals(IColocacionHeladeraRepository.class))
//        add(clase, new ColocacionHeladeraRepository());
//      else if (clase.equals(DonacionDineroService.class))
//        add(clase, new DonacionDineroService(get(IDonacionDineroRepository.class), get(ColaboradoresService.class), get(ICalculadorPuntos.class)));
//      else if (clase.equals(IDonacionDineroRepository.class))
//        add(clase, new DonacionDineroRepository());
//      else if (clase.equals(IDonacionesViandaRepository.class))
//        add(clase, new DonacionesViandaRepository());
//      else if (clase.equals(FallasTecnicasService.class))
//        add(clase, new FallasTecnicasService(get(IFallasTecnicasRepository.class), get(ColaboradoresService.class), get(HeladerasService.class)));
//      else if (clase.equals(IFallasTecnicasRepository.class))
//        add(clase, new FallasTecnicasRepository());
//      else if (clase.equals(FileUploadService.class))
//        add(clase, new FileUploadService());
//      else if (clase.equals(IFormasColaboracionRespository.class))
//        add(clase, new FormasColaboracionRespository());
//      else if (clase.equals(IFormularioRepository.class))
//        add(clase, new FormularioRepository());
//      else if (clase.equals(IHeladerasRepository.class))
//        add(clase, new HeladeraRepository());
//      else if (clase.equals(HeladerasService.class))
//        add(clase, new HeladerasService(get(IHeladerasRepository.class)));
//      else if (clase.equals(IMedioContactoRepository.class))
//        add(clase, new MedioContactoRepository());
//      else if (clase.equals(IModeloHeladeraRepository.class))
//        add(clase, new ModeloHeladeraRepository());
//      else if (clase.equals(ModelosService.class))
//        add(clase, new ModelosService(get(IModeloHeladeraRepository.class)));
//      else if (clase.equals(IMotivoRedistribucionRepository.class))
//        add(clase, new MotivoRedistribucionRepository());
//      else if (clase.equals(IOfertaProductoRepository.class))
//        add(clase, new OfertaProductoRepository());
//      else if (clase.equals(OfertasProductoService.class))
//        add(clase, new OfertasProductoService(get(IOfertaProductoRepository.class), get(ColaboradoresService.class)));
//      else if (clase.equals(IOpcionRepository.class))
//        add(clase, new OpcionRepository());
//      else if (clase.equals(IPermisosRepository.class))
//        add(clase, new PermisosRepository());
//      else if (clase.equals(IPersonaVulnerableRepository.class))
//        add(clase, new PersonaVulnerableRepository());
//      else if (clase.equals(IProductoRepository.class))
//        add(clase, new ProductoRepository());
//      else if (clase.equals(RecomendadorHeladeras.class))
//        add(clase, new RecomendadorHeladeras());
//      else if (clase.equals(IRedistribucionesViandaRepository.class))
//        add(clase, new RedistribucionesViandaRepository());
//      else if (clase.equals(IRegistrosTemperaturaRepository.class))
//        add(clase, new RegistrosTemperaturaRepository());
//      else if (clase.equals(ReportesFactory.class))
//        add(clase, new ReportesFactory(get(IViandasRepository.class),
//            get(IDonacionesViandaRepository.class),
//            get(IRedistribucionesViandaRepository.class),
//            get(IFallasTecnicasRepository.class),
//            get(IAlertasRepository.class)));
//      else if (clase.equals(IReportesRepository.class))
//        add(clase, new ReportesRepository());
//      else if (clase.equals(IRespuestasCampoRepository.class))
//        add(clase, new RespuestasCampoRepository());
//      else if (clase.equals(IRespuestasFormularioRepository.class))
//        add(clase, new RespuestasFormularioRepository());
//      else if (clase.equals(IRolesRepository.class))
//        add(clase, new RolesRepository());
//      else if (clase.equals(ISensorMovimientoRepository.class))
//        add(clase, new SensoresMovimientoRepository());
//      else if (clase.equals(ISensorTemperaturaRepository.class))
//        add(clase, new SensoresTemperaturaRepository());
//      else if (clase.equals(ISolicitudesAperturaHeladeraRepository.class))
//        add(clase, new SolicitudesAperturaHeladeraRepository());
//      else if (clase.equals(ISuscripcionesRepository.class))
//        add(clase, new SuscripcionesRepository());
//      else if (clase.equals(SuscripcionesServices.class))
//        add(clase, new SuscripcionesServices(get(ISuscripcionesRepository.class)));
//      else if (clase.equals(ITarjetasColaboradorRepository.class))
//        add(clase, new TarjetasColaboradorRepository());
//      else if (clase.equals(ITarjetasRepository.class))
//        add(clase, new TarjetaRepository());
//      else if (clase.equals(ITecnicosRepository.class))
//        add(clase, new TecnicosRepository());
//      else if (clase.equals(IUsosTarjetaRepository.class))
//        add(clase, new UsosTarjetaRepository());
//      else if (clase.equals(UsuarioService.class))
//        add(UsuarioService.class, new UsuarioService(get(IUsuariosRepository.class), get(ColaboradoresService.class)));
//      else if (clase.equals(IUsuariosRepository.class))
//        add(clase, new UsuariosRepository());
//      else if (clase.equals(IViandasRepository.class))
//        add(clase, new ViandasRepository());
//      else if (clase.equals(TecnicosHelper.class))
//        add(clase, new TecnicosHelper(get(ITecnicosRepository.class)));
//
//
//        // CONTROLLERS
//      else if (clase.equals(ColocacionHeladerasController.class))
//        add(clase, new ColocacionHeladerasController(get(ColocacionHeladerasService.class), get(ModelosService.class)));
//      else if (clase.equals(DonacionDineroController.class))
//        add(clase, new DonacionDineroController(get(DonacionDineroService.class)));
//      else if (clase.equals(FallasTecnicasController.class))
//        add(clase, new FallasTecnicasController(get(FallasTecnicasService.class), get(FileUploadService.class)));
//      else if (clase.equals(LoginController.class))
//        add(clase, new LoginController(get(UsuarioService.class)));
//      else if (clase.equals(LogoutController.class))
//        add(clase, new LogoutController());
//      else if (clase.equals(RegistroController.class))
//        add(clase, new RegistroController(get(UsuarioService.class), get(ColaboradoresService.class)));
//      else if (clase.equals(SuscripcionesController.class))
//        add(clase, new SuscripcionesController());
//      else if (clase.equals(AlertasController.class))
//        add(clase, new AlertasController(get(AlertasService.class)));
//      else if (clase.equals(OfertasProductoController.class))
//        add(clase, new OfertasProductoController(get(OfertasProductoService.class), get(FileUploadService.class)));
//      else throw new IllegalArgumentException("No hay servicio provisto para esa clase");
//
//    }
//    return (T) services.get(clase);
//  }

  private static boolean existeService(Class<?> clase) {
    return services.containsKey(clase);
  }
}
